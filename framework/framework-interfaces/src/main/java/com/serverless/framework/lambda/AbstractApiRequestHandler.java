package com.serverless.framework.lambda;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.framework.models.ErrorMessage;
import com.serverless.framework.models.GatewayResponse;

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
	protected static Map<String, String> JSON_CONTENT = Collections.singletonMap("Content-Type",
			"application/json");

	protected Map<String, String> defaultHeader;

	protected ObjectMapper objectMapper = new ObjectMapper();
	protected BodyType body;

	protected AbstractApiRequestHandler() {
		defaultHeader = new HashMap<>();
		defaultHeader.put("Content-Type", "application/json");
		defaultHeader.put("access-control-allow-headers", "Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token");
		defaultHeader.put("access-control-allow-methods", "POST,GET,OPTIONS,PUT");
		defaultHeader.put("access-control-allow-origin", "*");
	}
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
		getHeadersMap(event, logger);

		body = getBody(event, logger);

		try {
			execute(event, outputStream, context);
		}catch(Exception e) {
			logger.log("execute method throws an exception." + e.toString());
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


	protected Map<String, String> getHeadersMap(JsonNode event, LambdaLogger logger) {
		return getMap(event, "headers", logger);
	}
	protected Map<String, String> getQueryStringMap(JsonNode event, LambdaLogger logger) {
		return getMap(event, "queryStringParameters", logger);
	}
	protected Map<String, String> getPathParameters(JsonNode event, LambdaLogger logger) {
		return getMap(event, "pathParameters", logger);
	}
	private Map<String, String> getMap(JsonNode event, String value, LambdaLogger logger) {
		Map<String, String> map = new HashMap<>();
		if(event != null) {
			try {
				logger.log("getting map of " + value);
				JsonNode pathParametersNode = event.findValue(value);
				if(value == null) {
					logger.log("could not find value " + value);
					return map;
				}
				map = objectMapper.convertValue(pathParametersNode, Map.class);
				if(map == null) {
					logger.log("No information: map is null");
					map = new HashMap<>();
				}
			} catch (Exception e) {
				logger.log("Exception while getting map for " + value + " the exception is " + e.getMessage());
			}
		}

		return map;
	}
	private BodyType getBody(JsonNode event,  LambdaLogger logger) {
		final BodyType bodyObject;
		try {
			logger.log("get body");
			JsonNode updateOrderRequestBody = event.findValue("body");
			if (updateOrderRequestBody == null) {
				return null;
			}

			bodyObject = objectMapper.readValue(
					updateOrderRequestBody.asText(), getBodyClass());
		} catch (Exception e) {
			logger.log("Exception while getting body. The exception is " + e.getMessage());
			return null;
		}

		return bodyObject;
	}
	protected void writeErrorResponse(ObjectMapper objectMapper,
			OutputStream outputStream,
			String details){
		try {
			objectMapper.writeValue(outputStream,
					new GatewayResponse<>(objectMapper.writeValueAsString(new ErrorMessage(details, SC_BAD_REQUEST)),
							defaultHeader, SC_BAD_REQUEST));
		}catch(IOException e) {

		}
	}

	private Class<BodyType> getBodyClass() {
		return (Class<BodyType>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
