package com.serverless.framework.dynamodb.repository;

import com.serverless.framework.dynamodb.factories.IDynamoDbClientFactory;
import com.serverless.framework.factories.BeansFactory;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
						.conditionExpression("attribute_not_exists(orderId)")
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
					Model newModel;
					try {
						newModel = (Model)model.getClass().newInstance();
					} catch (InstantiationException e) {
						return null;
					} catch (IllegalAccessException e) {
						return null;
					}
					newModel.read(map);
					return newModel;
				})
				.collect(Collectors.toList());

		return list;
	}
}
