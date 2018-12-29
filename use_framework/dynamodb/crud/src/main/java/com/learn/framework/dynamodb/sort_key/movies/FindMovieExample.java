package com.learn.framework.dynamodb.sort_key.movies;

import com.learn.framework.dynamodb.sort_key.movies.models.Movie;
import com.learn.framework.dynamodb.sort_key.movies.repositories.MoviesRepository;
import com.serverless.framework.dynamodb.factories.DynamodbClientFactory;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;

public class FindMovieExample {
    public static void main(String[] args) {
        MoviesRepository moviesRepository = new MoviesRepository(new DynamodbClientFactory());
        DynamodbAttributes dynamodbAttributes = new DynamodbAttributes();
        dynamodbAttributes.putNumber(Movie.YEAR, "2011");
        dynamodbAttributes.putString(Movie.TITLE, "Bridesmaids");
        Movie movie = moviesRepository.find(dynamodbAttributes, new Movie());
        if(movie == null) {
            System.out.println("Could not find movie.");
        } else {
            System.out.println("movie found.");
        }
    }
}
