package com.labs.FaqApiUsingFramework.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.fasterxml.jackson.databind.JsonNode;
import com.labs.FaqApiUsingFramework.models.Question;
import com.serverless.framework.lambda.AbstractApiRequestHandler;
import com.serverless.framework.models.GatewayResponse;

import java.io.IOException;
import java.io.OutputStream;
import static org.apache.http.HttpStatus.SC_METHOD_FAILURE;
import static org.apache.http.HttpStatus.SC_OK;

public class FaqLambda extends AbstractApiRequestHandler<Question> {
    @Override
    protected void execute(JsonNode event, OutputStream outputStream, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("This is my lambda function");

        try {

            objectMapper.writeValue(outputStream, new GatewayResponse<>(
                    "Lambda function called",
                    JSON_CONTENT,
                    SC_OK));
        } catch (Exception e) {
            logger.log(e.getMessage());
            try {
                objectMapper.writeValue(outputStream, new GatewayResponse<>(
                        "Exception: " + e.getMessage(),
                        JSON_CONTENT,
                        SC_METHOD_FAILURE));
            } catch (IOException e1) {

            }
        }
    }
}
