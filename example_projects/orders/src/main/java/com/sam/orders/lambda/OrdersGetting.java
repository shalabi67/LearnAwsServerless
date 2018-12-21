package com.sam.orders.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.fasterxml.jackson.databind.JsonNode;
import com.sam.orders.factories.RepositoryFactory;
import com.sam.orders.modules.GatewayResponse;
import com.sam.orders.modules.OrderPage;
import com.sam.orders.repository.OrderDao;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Map;

import static org.apache.http.HttpStatus.SC_OK;

public class OrdersGetting extends AbstractApiRequestHandler<Void> {
	private static OrderDao orderDao = RepositoryFactory.getOrdersRepository();


	@Override protected void execute(JsonNode event, OutputStream outputStream, Context context) {
		LambdaLogger logger = context.getLogger();
		logger.log("starting execute");
		try {
			Map<String, String> queryString = getQueryStringMap(event, logger);
			logger.log("Number of query string items " + queryString.size());
			String exclusiveStartKeyQueryParameter = queryString.getOrDefault("exclusive_start_key", "");
			OrderPage page = orderDao.getOrders(exclusiveStartKeyQueryParameter);
			logger.log("number of orders= " + page.getOrders().size());

			objectMapper.writeValue(outputStream, new GatewayResponse<>(
					objectMapper.writeValueAsString(
							new OrderPage(page.getOrders(), page.getLastEvaluatedKey())),
					JSON_CONTENT, SC_OK));
		} catch (Exception e) {
			context.getLogger().log(e.getMessage());
			writeErrorResponse(objectMapper, outputStream, e.getMessage());
		}
	}
}
