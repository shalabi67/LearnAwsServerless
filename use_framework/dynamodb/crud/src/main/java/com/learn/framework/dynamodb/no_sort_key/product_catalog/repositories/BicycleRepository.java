package com.learn.framework.dynamodb.no_sort_key.product_catalog.repositories;

import com.learn.framework.dynamodb.no_sort_key.product_catalog.models.Bicycle;
import com.serverless.framework.dynamodb.factories.IDynamoDbClientFactory;
import com.serverless.framework.dynamodb.repository.DynamoDbRepository;

public class BicycleRepository extends DynamoDbRepository<Bicycle> {
    public BicycleRepository(IDynamoDbClientFactory dynamoDbClientFactory) {
        super(dynamoDbClientFactory);
    }
}
