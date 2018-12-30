package com.serverless.framework.dynamodb.repository;

import com.serverless.framework.dynamodb.exceptions.ModelInstantiationException;
import com.serverless.framework.dynamodb.factories.IDynamoDbClientFactory;
import com.serverless.framework.factories.BeansFactory;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.serverless.framework.dynamodb.repository.BaseModule.ID;

public abstract class DynamoDbRepository<Model extends  BaseModule> {
	private static String BEAN_NAME = "DYNAMODB_CLIENT";
	protected DynamoDbClient dynamoDbClient;
	public DynamoDbRepository(IDynamoDbClientFactory dynamoDbClientFactory) {
		BeansFactory beansFactory = new BeansFactory();
		if(beansFactory.get(BEAN_NAME) == null) {
			dynamoDbClient = dynamoDbClientFactory.create();
			beansFactory.put(BEAN_NAME, dynamoDbClient);
		}
	}
	public Model save(Model model) {
		Map<String, AttributeValue> item = model.save();

		int tries = 0;
		while (tries < 10) {
			try {
				dynamoDbClient.putItem(PutItemRequest.builder()
						.tableName(model.getTableName())
						.item(item)
						//.conditionExpression("attribute_not_exists(orderId)")
						.build());
				return model;
			} catch (ConditionalCheckFailedException e) {
				tries++;
			}
		}

		return null;
	}

	public List<Model> findAll(Model model) {
		ScanResponse result;

		ScanRequest.Builder scanBuilder = ScanRequest.builder()
				.tableName(model.getTableName());

		result = dynamoDbClient.scan(scanBuilder.build());
		List<Model> list = result.items().stream()
				.map((Map<String, AttributeValue> map) ->{
					Model newModel = getModel(model);
					newModel.read(map);
					return newModel;
				})
				.collect(Collectors.toList());

		return list;
	}

	public Model find(String id, Model model) {
		DynamodbAttributes keyAttributes = new DynamodbAttributes();
		keyAttributes.putString(model.getKeyName(), id);

		return find(keyAttributes, model);
	}

	public Model find(DynamodbAttributes keyAttributes, Model model) {
		GetItemRequest itemRequest = GetItemRequest
				.builder()
				.tableName(model.getTableName())
				.key(keyAttributes.getAttributesMap())
				.build();
		GetItemResponse response = dynamoDbClient.getItem(itemRequest);
		if(response == null) {
			return null;
		}

		Model newModel = getModel(model);
		newModel.read(response.item());

		return newModel;
	}

	public void update(DynamodbAttributes keyAttributes, DynamodbAttributes updateAttributes, Model model) {
		UpdateItemRequest updateRequest = UpdateItemRequest.builder()
				.tableName(model.getTableName())
				.key(keyAttributes.getAttributesMap())
				.attributeUpdates(updateAttributes.getUpdateAttributes())
				.build();

		dynamoDbClient.updateItem(updateRequest);
	}

	private Model getModel(Model model) {
		Model newModel;
		try {
			newModel = (Model)model.getClass().newInstance();
		} catch (Exception e) {
			throw new ModelInstantiationException("Could not instantiate model of type " + model.getClass().getTypeName(), e);
		}

		return newModel;
	}
}