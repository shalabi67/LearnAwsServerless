package com.lab.supermission.repositories;

import com.lab.supermission.models.SuperMission;
import com.serverless.framework.dynamodb.factories.IDynamoDbClientFactory;
import com.serverless.framework.dynamodb.repository.DynamoDbRepository;

public class SuperMissionRepository extends DynamoDbRepository<SuperMission> {
    public SuperMissionRepository(IDynamoDbClientFactory dynamoDbClientFactory) {
        super(dynamoDbClientFactory);
    }
}
