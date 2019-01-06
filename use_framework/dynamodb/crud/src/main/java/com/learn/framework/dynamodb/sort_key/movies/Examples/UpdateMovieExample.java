package com.learn.framework.dynamodb.sort_key.movies.Examples;

import com.learn.framework.dynamodb.sort_key.movies.models.Movie;
import com.learn.framework.dynamodb.sort_key.movies.repositories.MoviesRepository;
import com.serverless.framework.dynamodb.factories.DynamodbClientFactory;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;

public class UpdateMovieExample {
    public static void main(String[] args) {
        MoviesRepository moviesRepository = new MoviesRepository(new DynamodbClientFactory());
        DynamodbAttributes dynamodbAttributes = new DynamodbAttributes();
        dynamodbAttributes.putNumber(Movie.YEAR, 1944L);
        dynamodbAttributes.putString(Movie.TITLE, "Lifeboat");

        Movie movie = moviesRepository.find(new Movie(1944L, "Lifeboat"));
        movie.getMovieInformation().setRating(5.0F);

        DynamodbAttributes updateAttributes = new DynamodbAttributes();
        updateAttributes.putObject(Movie.INFO, movie.getMovieInformation().save());

        moviesRepository.update(dynamodbAttributes, updateAttributes, new Movie());


        //simpler way to update specific fields
        movie.getMovieInformation().setRating(6.0F);

        DynamodbAttributes updateAttributes1 = new DynamodbAttributes();
        updateAttributes1.putObject(Movie.INFO, movie.getMovieInformation().save());

        moviesRepository.update(updateAttributes1, movie);

        //if you want to update all fields
        movie.getMovieInformation().setRating(7.0F);
        moviesRepository.updateAll(movie);
    }
}
