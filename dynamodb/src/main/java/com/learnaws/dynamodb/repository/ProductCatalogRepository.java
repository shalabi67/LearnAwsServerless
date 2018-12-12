package com.learnaws.dynamodb.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.learnaws.dynamodb.model.Movie;

import java.util.List;

public class ProductCatalogRepository<T> {
    private static AmazonDynamoDB dynamoDb = AmazonDynamoDBClientBuilder.standard().build();

    public T save(T item) {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDb);
        mapper.save(item);
        return item;
    }


    public void delete(T item) {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDb);
        mapper.delete(item);
    }

    public T conditionalSave(T item, DynamoDBSaveExpression saveExpression) {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDb);
        mapper.save(item, saveExpression);
        return item;
    }

    public List<T> query(Class<T> clazz, DynamoDBQueryExpression<T> queryExpression) {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDb);
        return mapper.query(clazz, queryExpression);
    }
}
