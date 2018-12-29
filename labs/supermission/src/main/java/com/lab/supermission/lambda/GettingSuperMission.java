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
import java.util.Map;

import static com.lab.supermission.models.SuperMission.SUPER_HERO;
import static org.apache.http.HttpStatus.SC_METHOD_FAILURE;
import static org.apache.http.HttpStatus.SC_OK;

public class GettingSuperMission extends AbstractApiRequestHandler<SuperMission> {
    private static SuperMissionRepository superMissionRepository = new SuperMissionRepository(new DynamodbClientFactory());

    @Override
    protected void execute(JsonNode event, OutputStream outputStream, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("This is my lambda function");

        try {
            Map<String, String> pathParametersMap = getPathParameters(event, logger);
            String id = pathParametersMap.getOrDefault(SUPER_HERO, "");
            SuperMission superMission = superMissionRepository.find(id, new SuperMission());

            objectMapper.writeValue(outputStream, new GatewayResponse<>(
                    objectMapper.writeValueAsString(superMission),
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
