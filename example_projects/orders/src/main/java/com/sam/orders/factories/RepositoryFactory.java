package com.sam.orders.factories;

import com.sam.orders.repository.OrderDao;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;

import java.net.URI;
import java.util.Optional;

public class RepositoryFactory {
	private static DynamoDbClient dynamoDbClient = null;
	private static OrderDao orderDao = null;

	public static DynamoDbClient getDynamoDbClient() {
		if(dynamoDbClient == null) {
			final String endpoint = System.getenv("ENDPOINT_OVERRIDE");

			DynamoDbClientBuilder builder = DynamoDbClient.builder();
			builder.httpClient(ApacheHttpClient.builder().build());
			if (endpoint != null && !endpoint.isEmpty()) {
				builder.endpointOverride(URI.create(endpoint));
			}

			dynamoDbClient = builder.build();
		}
		return dynamoDbClient;
	}
	public static OrderDao getOrdersRepository() {
		if(orderDao == null) {
			orderDao = new OrderDao(getDynamoDbClient(), getTableName(), 10);
		}

		return orderDao;
	}


	private  static String getTableName() {
		return Optional.ofNullable(System.getenv("TABLE_NAME")).orElse("orders");
	}
}
