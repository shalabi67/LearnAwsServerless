package com.lab.supermission.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.fasterxml.jackson.databind.JsonNode;
import com.lab.supermission.models.SuperMission;
import com.lab.supermission.repositories.SuperMissionRepository;
import com.serverless.framework.dynamodb.factories.DynamodbClientFactory;
import com.serverless.framework.lambda.AbstractApiRequestHandler;
import com.serverless.framework.models.GatewayResponse;

import java.io.OutputStream;
import java.util.List;

import static org.apache.http.HttpStatus.SC_METHOD_FAILURE;
import static org.apache.http.HttpStatus.SC_OK;

public class GettingSuperMissions extends AbstractApiRequestHandler<SuperMission> {
    private static SuperMissionRepository superMissionRepository = new SuperMissionRepository(new DynamodbClientFactory());

    @Override
    protected void execute(JsonNode jsonNode, OutputStream outputStream, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("This is my lambda function");

        try {
            List<SuperMission> superMissions = superMissionRepository.findAll(new SuperMission());

            objectMapper.writeValue(outputStream, new GatewayResponse<>(
                    objectMapper.writeValueAsString(superMissions),
                    defaultHeader,
                    SC_OK));
        } catch (Exception e) {
            logger.log(e.getMessage());
            try {
                objectMapper.writeValue(outputStream, new GatewayResponse<>(
                        "Exception: " + e.getMessage(),
                        defaultHeader,
                        SC_METHOD_FAILURE));
            } catch (Exception e1) {

            }
        }
    }
}
