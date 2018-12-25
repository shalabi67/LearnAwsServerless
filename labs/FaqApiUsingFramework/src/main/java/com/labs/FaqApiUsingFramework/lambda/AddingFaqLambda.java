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

import static org.apache.http.HttpStatus.SC_METHOD_FAILURE;
import static org.apache.http.HttpStatus.SC_OK;

public class AddingFaqLambda extends AbstractApiRequestHandler<Question> {
    private static QuestionRepository questionRepository = new QuestionRepository(new DynamodbClientFactory());

    @Override
    protected void execute(JsonNode event, OutputStream outputStream, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("This is my adding faq function");

        try {
            Question question = questionRepository.save(body);

            objectMapper.writeValue(outputStream, new GatewayResponse<>(
                    objectMapper.writeValueAsString(question),
                    JSON_CONTENT,
                    SC_OK));
        } catch (Exception e) {
            try {
                objectMapper.writeValue(outputStream, new GatewayResponse<>(
                        e.getMessage(),
                        JSON_CONTENT,
                        SC_METHOD_FAILURE));
            } catch (IOException e1) {
            }
        }
    }
}
