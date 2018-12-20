package com.sam.orders.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.databind.JsonNode;
import com.sam.orders.modules.GatewayResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;

import static org.apache.http.HttpStatus.SC_OK;

public class OrdersGetting extends AbstractApiRequestHandler {

	@Override protected void execute(JsonNode event, OutputStream outputStream, Context context) {
		try {
			objectMapper.writeValue(outputStream,
					new GatewayResponse<>("{\"message\": \"Created orders table\"}",
							Collections.emptyMap(), SC_OK));
		} catch (IOException e) {
			context.getLogger().log(e.getMessage());
		}
	}
}
