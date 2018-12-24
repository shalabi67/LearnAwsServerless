package com.serverless.framework.dynamodb.factories;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;

public class DynamodbClientFactory implements IDynamoDbClientFactory {

	@Override
	public DynamoDbClient create() {
		 DynamoDbClientBuilder builder = DynamoDbClient.builder();
		 return builder.build();
	}
}
