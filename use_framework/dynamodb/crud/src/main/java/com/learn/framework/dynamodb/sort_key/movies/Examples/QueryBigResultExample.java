package com.learn.framework.dynamodb.sort_key.movies.Examples;

import com.learn.framework.dynamodb.sort_key.movies.models.Movie;
import com.learn.framework.dynamodb.sort_key.movies.repositories.MoviesRepository;
import com.serverless.framework.dynamodb.factories.DynamodbClientFactory;
import com.serverless.framework.dynamodb.query.Query;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryBigResultExample {
    public static void main(String[] args) {
        MoviesRepository moviesRepository = new MoviesRepository(new DynamodbClientFactory());


        Map<String, String> map = new HashMap<>();
        map.put("#y","year");

        DynamodbAttributes attributes = new DynamodbAttributes();
        attributes.putNumber(":yyyy", 1993);

        Movie movie = new Movie();
        movie.setQuery(new Query("#y = :yyyy", null, attributes,map));
        List<Movie> movies = moviesRepository.findBy(movie);
/*
--key-condition-expression "#y = :yyyy" \
    --expression-attribute-names '{"#y":"year"}' \
    --expression-attribute-values '{":yyyy":{"N":"1993"}}'
 */
    }
}
