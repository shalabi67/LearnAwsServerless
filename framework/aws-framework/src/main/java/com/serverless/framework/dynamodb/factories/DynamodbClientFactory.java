package com.serverless.framework.dynamodb.factories;

import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;

public class DynamodbClientFactory implements IDynamoDbClientFactory {

	@Override
	public DynamoDbClient create() {
		 DynamoDbClientBuilder builder = DynamoDbClient.builder()
		 	.httpClientBuilder(UrlConnectionHttpClient.builder());
		 return builder.build();
		//return DynamoDbClient.create();
	}
}
