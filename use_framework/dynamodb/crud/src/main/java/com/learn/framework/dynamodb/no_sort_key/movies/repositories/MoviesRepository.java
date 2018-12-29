package com.learn.framework.dynamodb.no_sort_key.movies.repositories;

import com.learn.framework.dynamodb.no_sort_key.movies.models.Movie;
import com.serverless.framework.dynamodb.factories.IDynamoDbClientFactory;
import com.serverless.framework.dynamodb.repository.DynamoDbRepository;

public class MoviesRepository extends DynamoDbRepository<Movie> {
    public MoviesRepository(IDynamoDbClientFactory dynamoDbClientFactory) {
        super(dynamoDbClientFactory);
    }
}
