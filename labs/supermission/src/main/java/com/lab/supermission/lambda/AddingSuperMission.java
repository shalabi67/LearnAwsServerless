package com.lab.supermission.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.fasterxml.jackson.databind.JsonNode;
import com.lab.supermission.models.SuperMission;
import com.lab.supermission.repositories.SuperMissionRepository;
import com.serverless.framework.dynamodb.factories.DynamodbClientFactory;
import com.serverless.framework.lambda.AbstractApiRequestHandler;
import com.serverless.framework.models.GatewayResponse;

import java.io.IOException;
import java.io.OutputStream;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_METHOD_FAILURE;

public class AddingSuperMission extends AbstractApiRequestHandler<SuperMission> {
    private static SuperMissionRepository superMissionRepository = new SuperMissionRepository(new DynamodbClientFactory());

    @Override
    protected void execute(JsonNode event, OutputStream outputStream, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("This is my adding faq function");

        try {
            SuperMission superMission = superMissionRepository.save(body);

            objectMapper.writeValue(outputStream, new GatewayResponse<>(
                   objectMapper.writeValueAsString(superMission),
                    JSON_CONTENT,
                    SC_CREATED));
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
