package com.learnaws.dynamodb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnaws.dynamodb.model.Movie;
import com.learnaws.dynamodb.repository.MoviesRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UpdateMoviesExample {
    public static void main(String[] args) {
        ClassLoader classLoader = new UpdateMoviesExample().getClass().getClassLoader();
        File file = new File(classLoader.getResource("moviedata.json").getFile());
        try {
            List<Movie> list = new ObjectMapper().readValue(file, new TypeReference<List<Movie>>(){});
            Movie movie = list.get(0);
            movie.setTitle(movie.getTitle() + " updating 123");

            MoviesRepository moviesRepository = new MoviesRepository();
            moviesRepository.save(movie);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
