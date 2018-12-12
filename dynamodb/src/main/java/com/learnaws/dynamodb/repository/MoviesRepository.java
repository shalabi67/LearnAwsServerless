package com.learnaws.dynamodb.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.learnaws.dynamodb.model.Movie;

import java.util.List;

public class MoviesRepository {
    private static AmazonDynamoDB dynamoDb = AmazonDynamoDBClientBuilder.standard().build();

    public Movie save(Movie movie) {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDb);
        mapper.save(movie);
        return movie;
    }

    public Movie find(Integer year, String title) {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDb);
        return mapper.load(Movie.class, year, title);
    }

    public void delete(Movie movie) {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDb);
        mapper.delete(movie);
    }

    public Movie conditionalSave(Movie movie, DynamoDBSaveExpression saveExpression) {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDb);
        mapper.save(movie, saveExpression);
        return movie;
    }

    public List<Movie> query(DynamoDBQueryExpression<Movie> queryExpression) {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDb);
        return mapper.query(Movie.class, queryExpression);
    }
}
