package com.sam.orders.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.fasterxml.jackson.databind.JsonNode;
import com.sam.orders.factories.RepositoryFactory;
import com.sam.orders.modules.GatewayResponse;
import com.sam.orders.modules.Order;
import com.sam.orders.modules.OrderRequest;
import com.sam.orders.repository.OrderDao;

import java.io.OutputStream;
import java.util.Map;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

public class DeletingOrder extends AbstractApiRequestHandler<OrderRequest> {
	private static OrderDao orderDao = RepositoryFactory.getOrdersRepository();


	@Override protected void execute(JsonNode event, OutputStream outputStream, Context context) {
		LambdaLogger logger = context.getLogger();
		logger.log("starting execute");
		try {
			Map<String, String> pathMap = getPathParameters(event, logger);
			String orderId = pathMap.getOrDefault("order_id", null);
			if(orderId == null) {
				writeErrorResponse(objectMapper, outputStream, "Order id is missing.");
				return;
			}
			Order order = orderDao.deleteOrder(orderId);

			objectMapper.writeValue(outputStream, new GatewayResponse<>(
					objectMapper.writeValueAsString(order),
					JSON_CONTENT,
					SC_OK));
		} catch (Exception e) {
			context.getLogger().log(e.getMessage());
			writeErrorResponse(objectMapper, outputStream, e.getMessage());
		}
	}
}
