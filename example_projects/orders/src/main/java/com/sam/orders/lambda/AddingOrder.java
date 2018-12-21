package com.sam.orders.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.fasterxml.jackson.databind.JsonNode;
import com.sam.orders.factories.RepositoryFactory;
import com.sam.orders.modules.GatewayResponse;
import com.sam.orders.modules.Order;
import com.sam.orders.modules.OrderPage;
import com.sam.orders.modules.OrderRequest;
import com.sam.orders.repository.OrderDao;

import java.io.OutputStream;
import java.util.Map;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

public class AddingOrder extends AbstractApiRequestHandler<OrderRequest> {
	private static OrderDao orderDao = RepositoryFactory.getOrdersRepository();


	@Override protected void execute(JsonNode event, OutputStream outputStream, Context context) {
		LambdaLogger logger = context.getLogger();
		logger.log("starting execute");
		try {
			if(body == null) {
				writeErrorResponse(objectMapper, outputStream, "Request is missing body. please provide and OrderRequest in your post.");
				return;
			}
			Order order = orderDao.createOrder(body);

			objectMapper.writeValue(outputStream, new GatewayResponse<>(
					objectMapper.writeValueAsString(order),
					JSON_CONTENT,
					SC_CREATED));
		} catch (Exception e) {
			context.getLogger().log(e.getMessage());
			writeErrorResponse(objectMapper, outputStream, e.getMessage());
		}
	}
}
