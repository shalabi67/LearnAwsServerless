package com.labs.FaqApiUsingFramework.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.fasterxml.jackson.databind.JsonNode;
import com.labs.FaqApiUsingFramework.models.Question;
import com.labs.FaqApiUsingFramework.repositories.QuestionRepository;
import com.serverless.framework.dynamodb.factories.DynamodbClientFactory;
import com.serverless.framework.lambda.AbstractApiRequestHandler;
import com.serverless.framework.models.GatewayResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static org.apache.http.HttpStatus.SC_METHOD_FAILURE;
import static org.apache.http.HttpStatus.SC_OK;

public class FaqLambda extends AbstractApiRequestHandler<Question> {
    private static QuestionRepository questionRepository = new QuestionRepository(new DynamodbClientFactory());

    @Override
    protected void execute(JsonNode event, OutputStream outputStream, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("This is my lambda function");

        try {
            List<Question> questions = questionRepository.findAll(new Question());
            if(questions == null) {
                objectMapper.writeValue(outputStream, new GatewayResponse<>(
                        "findAll call failed",
                        JSON_CONTENT,
                        SC_METHOD_FAILURE));
            }
            objectMapper.writeValue(outputStream, new GatewayResponse<>(
                    objectMapper.writeValueAsString(questions),
                    JSON_CONTENT,
                    SC_OK));
        } catch (Exception e) {
            logger.log(e.getMessage());
            try {
                objectMapper.writeValue(outputStream, new GatewayResponse<>(
                        "Exception: " + e.getMessage(),
                        JSON_CONTENT,
                        SC_METHOD_FAILURE));
            } catch (Exception e1) {

            }
        }
    }
}
