package com.serverless.framework.dynamodb.repository;

import com.serverless.framework.dynamodb.factories.ClassFactory;
import com.serverless.framework.dynamodb.factories.IDynamoDbClientFactory;
import com.serverless.framework.dynamodb.factories.RequestBuilder;
import com.serverless.framework.factories.BeansFactory;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class DynamoDbRepository<Model extends  BaseModule> {
	private static String BEAN_NAME = "DYNAMODB_CLIENT";
	protected DynamoDbClient dynamoDbClient;
	public DynamoDbRepository(IDynamoDbClientFactory dynamoDbClientFactory) {
		BeansFactory beansFactory = new BeansFactory();
		if(beansFactory.get(BEAN_NAME) == null) {
			dynamoDbClient = dynamoDbClientFactory.create();
			beansFactory.put(BEAN_NAME, dynamoDbClient);
		}else {
			dynamoDbClient = beansFactory.get(BEAN_NAME);
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

		RequestBuilder.build(scanBuilder, model);

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

	public Model find(Model model) {
		return find(model.getKey(), model);
	}

	public Model find(DynamodbAttributes keyAttributes, Model model) {
		GetItemRequest.Builder builder =  GetItemRequest
				.builder()
				.tableName(model.getTableName())
				.key(keyAttributes.getAttributesMap());
		RequestBuilder.build(builder, model);

		GetItemRequest itemRequest = builder
				.build();
		GetItemResponse response = dynamoDbClient.getItem(itemRequest);
		if(response == null) {
			return null;
		}

		Model newModel = getModel(model);
		newModel.read(response.item());

		return newModel;
	}

    /**
     * this method will be used if you want to update all model fields
     * @param model
     */
    public void updateAll(Model model) {
        update(model.getKey(), new DynamodbAttributes(model, true), model);
    }

    /**
     * Use this method if you want to update specific fields.
     * @param updateAttributes
     * @param model
     */
    public void update(DynamodbAttributes updateAttributes, Model model) {
        update(model.getKey(), updateAttributes, model);
    }
	public void update(DynamodbAttributes keyAttributes, DynamodbAttributes updateAttributes, Model model) {
		UpdateItemRequest.Builder builder = UpdateItemRequest.builder()
				.tableName(model.getTableName())
				.key(keyAttributes.getAttributesMap())
				.attributeUpdates(updateAttributes.getUpdateAttributes());
        UpdateItemRequest updateRequest = builder.build();
		dynamoDbClient.updateItem(updateRequest);
	}

	public void update(UpdateItemRequest.Builder itemUpdateBuilder) {
		UpdateItemRequest updateRequest = itemUpdateBuilder.build();
		dynamoDbClient.updateItem(updateRequest);
	}
	public UpdateItemRequest.Builder getItemUpdateBuilder(Model model) {
		return UpdateItemRequest.builder()
				.tableName(model.getTableName())
				.key(model.getKey().getAttributesMap());
				//.attributeUpdates(new DynamodbAttributes(model, true).getUpdateAttributes());
	}
	public void update(Model model) {
		UpdateItemRequest.Builder builder = getItemUpdateBuilder(model);
		RequestBuilder.build(builder, model);
		update(builder);
	}

	private Model getModel(Model model) {
		return ClassFactory.createModel(model);
	}
}