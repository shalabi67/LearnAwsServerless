package com.serverless.framework.dynamodb.factories;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public interface IDynamoDbClientFactory {
	DynamoDbClient create();

}
