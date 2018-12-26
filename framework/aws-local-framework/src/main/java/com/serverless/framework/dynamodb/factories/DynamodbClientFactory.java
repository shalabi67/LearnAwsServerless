package com.serverless.framework.dynamodb.factories;

import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;

import java.net.URI;

public class DynamodbClientFactory implements IDynamoDbClientFactory {

	@Override
	public DynamoDbClient create() {
		System.out.println("Local DynamodbClientFactory");
		final String endpoint = System.getenv("LOCAL_DYNAMODB_URL");
		 DynamoDbClientBuilder builder = DynamoDbClient.builder()
		 	.httpClientBuilder(UrlConnectionHttpClient.builder());
		builder.endpointOverride(URI.create(endpoint));
		 return builder.build();
	}
}