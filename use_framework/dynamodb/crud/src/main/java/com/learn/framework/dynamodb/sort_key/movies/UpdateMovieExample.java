package com.learn.framework.dynamodb.sort_key.movies;

import com.learn.framework.dynamodb.sort_key.movies.models.Movie;
import com.learn.framework.dynamodb.sort_key.movies.repositories.MoviesRepository;
import com.serverless.framework.dynamodb.factories.DynamodbClientFactory;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;

public class UpdateMovieExample {
    public static void main(String[] args) {
        MoviesRepository moviesRepository = new MoviesRepository(new DynamodbClientFactory());
        DynamodbAttributes dynamodbAttributes = new DynamodbAttributes();
        dynamodbAttributes.putNumber(Movie.YEAR, "1944");
        dynamodbAttributes.putString(Movie.TITLE, "Lifeboat");

        Movie movie = moviesRepository.find(dynamodbAttributes, new Movie());
        movie.getMovieInformation().setRating(5.0F);

        DynamodbAttributes updateAttributes = new DynamodbAttributes();
        updateAttributes.putObject(Movie.INFO, movie.getMovieInformation().save());

        moviesRepository.update(dynamodbAttributes, updateAttributes, new Movie());
    }
}
