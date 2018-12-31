package com.learn.framework.dynamodb.sort_key.movies;

import com.learn.framework.dynamodb.sort_key.movies.models.Movie;
import com.learn.framework.dynamodb.sort_key.movies.repositories.MoviesRepository;
import com.serverless.framework.dynamodb.factories.DynamodbClientFactory;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;

public class FindMovieExample {
    public static void main(String[] args) {
        MoviesRepository moviesRepository = new MoviesRepository(new DynamodbClientFactory());
        DynamodbAttributes dynamodbAttributes = new DynamodbAttributes();
        dynamodbAttributes.putNumber(Movie.YEAR, 1944L);
        dynamodbAttributes.putString(Movie.TITLE, "Lifeboat");
        Movie movie = moviesRepository.find(dynamodbAttributes, new Movie());
        if(movie == null) {
            System.out.println("Could not find movie.");
        } else {
            System.out.println("movie found.");
        }



        //a simpler way
        movie = moviesRepository.find(new Movie(1944L, "Lifeboat"));
        if(movie == null) {
            System.out.println("Could not find movie.");
        } else {
            System.out.println("movie found.");
        }
    }
}
