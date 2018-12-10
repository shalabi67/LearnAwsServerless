package com.learnaws.dynamodb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnaws.dynamodb.model.Movie;
import com.learnaws.dynamodb.repository.MoviesRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DeleteMovieExample {
    public static void main(String[] args) {
        MoviesRepository moviesRepository = new MoviesRepository();
        Movie movie = moviesRepository.find(2013, "Snitch");
        if(movie == null) {
            System.out.println("Item deleted");
            return;
        }
        moviesRepository.delete(movie);

        movie = moviesRepository.find(2013, "Snitch");
        if(movie == null) {
            System.out.println("Item deleted");
        }
    }
}
