package com.learnaws.dynamodb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnaws.dynamodb.model.Movie;
import com.learnaws.dynamodb.repository.MoviesRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AddMoviesExample {
    public static void main(String[] args) {
        ClassLoader classLoader = new AddMoviesExample().getClass().getClassLoader();
        File file = new File(classLoader.getResource("moviedata.json").getFile());
        try {
            List<Movie> list = new ObjectMapper().readValue(file, new TypeReference<List<Movie>>(){});
            MoviesRepository moviesRepository = new MoviesRepository();
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
