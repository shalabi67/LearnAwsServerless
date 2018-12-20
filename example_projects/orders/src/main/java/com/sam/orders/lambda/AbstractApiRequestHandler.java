package com.sam.orders.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sam.orders.modules.ErrorMessage;
import com.sam.orders.modules.GatewayResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;

/**
 * this is the RequestStreamHandler abstract class for lambda functions called by Api Gateway.
 * @param <BodyType> body input object ( used in post, and PUT method calls).
 */
public abstract class AbstractApiRequestHandler<BodyType> implements RequestStreamHandler {
	private static Map<String, String> JSON_CONTENT = Collections.singletonMap("Content-Type",
			"application/json");

	ObjectMapper objectMapper = new ObjectMapper();
	BodyType body;

	/*
	Map<String, String> pathParametersMap;
	Map<String, String> queryStringMap;
	Map<String, String> headersMap;
	*/

	@Override
	public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context)
			throws IOException {
		LambdaLogger logger = context.getLogger();
		final JsonNode event;

		try {
			event = objectMapper.readTree(inputStream);
		} catch (JsonMappingException e) {
			logger.log("Could not read input request as JSON object.");
			writeErrorResponse(objectMapper, outputStream, e.getMessage());
			return;
		}
		if (event == null) {
			logger.log("Could not read input request as JSON object.");
			writeErrorResponse(objectMapper, outputStream, "event was null");
			return;
		}

		/*
		pathParametersMap = getPathParameters(event);
		queryStringMap = getQueryStringMap(event);
		headersMap = getHeadersMap(event);
		*/
		body = getBody(event);

		try {
			execute(event, outputStream, context);
		}catch(Exception e) {
			logger.log("execute method throws an exception.");
			writeErrorResponse(objectMapper, outputStream, e.getMessage());
		}
	}

	/**
	 * This is the main execution path. from this methoda you can get extra request data by calling
	 * pathParametersMap = getPathParameters(event);
	 * queryStringMap = getQueryStringMap(event);
	 * headersMap = getHeadersMap(event);
	 * @param event this is the JSOnNode for the request coming from input stream.
	 * @param outputStream we use this stream to return our response.
	 * @param context
	 * @return
	 */
	protected abstract void execute(JsonNode event, OutputStream outputStream, Context context);


	protected Map<String, String> getHeadersMap(JsonNode event) {
		return getMap(event, "headers");
	}
	protected Map<String, String> getQueryStringMap(JsonNode event) {
		return getMap(event, "queryStringParameters");
	}
	protected Map<String, String> getPathParameters(JsonNode event) {
		return getMap(event, "pathParameters");
	}
	private Map<String, String> getMap(JsonNode event, String value) {
		JsonNode pathParametersNode = event.findValue(value);
		Map<String, String> map = new HashMap<>();
		try {
			map = objectMapper.readValue(pathParametersNode.asText(), map.getClass());
		} catch (IOException e) {

		}

		return map;
	}
	private BodyType getBody(JsonNode event) {
		JsonNode updateOrderRequestBody = event.findValue("body");
		if (updateOrderRequestBody == null) {
			return null;
		}

		final BodyType bodyObject;
		try {
			bodyObject = objectMapper.readValue(
					updateOrderRequestBody.asText(), getBodyClass());
		} catch (Exception e) {
			return null;
		}

		return bodyObject;
	}
	private void writeErrorResponse(ObjectMapper objectMapper,
			OutputStream outputStream,
			String details) throws IOException {
		objectMapper.writeValue(outputStream, new GatewayResponse<>(
				objectMapper.writeValueAsString(new ErrorMessage(details, SC_BAD_REQUEST)),
				JSON_CONTENT, SC_BAD_REQUEST));
	}

	private Class<BodyType> getBodyClass() {
		return (Class<BodyType>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
