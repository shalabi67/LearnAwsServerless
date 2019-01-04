package com.learn.framework.dynamodb.projection.repositories;

import com.learn.framework.dynamodb.projection.model.ProductProjection;
import com.serverless.framework.dynamodb.factories.IDynamoDbClientFactory;
import com.serverless.framework.dynamodb.repository.DynamoDbRepository;

public class ProductProjectionRepository extends DynamoDbRepository<ProductProjection> {
    public ProductProjectionRepository(IDynamoDbClientFactory dynamoDbClientFactory) {
        super(dynamoDbClientFactory);
    }
}
