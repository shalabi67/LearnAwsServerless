package com.learn.framework.dynamodb.no_sort_key.movies;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.framework.dynamodb.no_sort_key.movies.models.Movie;
import com.learn.framework.dynamodb.no_sort_key.movies.repositories.MoviesRepository;
import com.serverless.framework.dynamodb.factories.DynamodbClientFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AddMoviesExample {
    public static void main(String[] args) {
        ClassLoader classLoader = new AddMoviesExample().getClass().getClassLoader();
        File file = new File(classLoader.getResource("movies.json").getFile());
        try {
            List<Movie> list = new ObjectMapper().readValue(file, new TypeReference<List<Movie>>(){});
            MoviesRepository moviesRepository = new MoviesRepository(new DynamodbClientFactory());
            int i = 0;
            for(Movie movie : list) {
                moviesRepository.save(movie);
                System.out.println("Movies Added = " + ++i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
