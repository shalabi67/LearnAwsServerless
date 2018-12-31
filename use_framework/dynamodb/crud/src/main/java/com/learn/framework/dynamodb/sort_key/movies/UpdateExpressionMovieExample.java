package com.learn.framework.dynamodb.sort_key.movies;

import com.learn.framework.dynamodb.sort_key.movies.models.Movie;
import com.learn.framework.dynamodb.sort_key.movies.repositories.MoviesRepository;
import com.serverless.framework.dynamodb.factories.DynamodbClientFactory;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateExpressionMovieExample {
    /**
     *
        https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Expressions.UpdateExpressions.html
     */
    public static void main(String[] args) {
        MoviesRepository moviesRepository = new MoviesRepository(new DynamodbClientFactory());
        Movie movie = moviesRepository.find(new Movie(1L, "Rush"));

        //removeFirstActor(moviesRepository, movie);
        //removePlotAttribute(moviesRepository, movie);
        //changeFirstActor(moviesRepository, movie);
        //addNewColorsAttribute(moviesRepository, movie);
        //addNewColors(moviesRepository, movie);
        addNewActor(moviesRepository, movie);

    }

    private static void addNewActor(MoviesRepository moviesRepository, Movie movie) {
        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":actor", AttributeValue.builder().s("add actor 2").build());


        /**
         * notice how we used 400 here just to do adding since 400 is out of range.
         */
        UpdateItemRequest.Builder builder = moviesRepository.getItemUpdateBuilder(movie);
        builder.updateExpression("set info.actors[400] = :actor");
        builder.expressionAttributeValues(expressionAttributeValues);
        moviesRepository.update(builder);
    }

    private static void removeFirstActor( MoviesRepository moviesRepository,  Movie movie) {
        UpdateItemRequest.Builder builder = moviesRepository.getItemUpdateBuilder(movie);
       // builder.updateExpression("remove info.plot");
        builder.updateExpression("remove info.actors[0]");
        moviesRepository.update(builder);
    }

    private static void removePlotAttribute( MoviesRepository moviesRepository,  Movie movie) {
        UpdateItemRequest.Builder builder = moviesRepository.getItemUpdateBuilder(movie);
        builder.updateExpression("remove info.plot");
        moviesRepository.update(builder);
    }

    private static void changeFirstActor( MoviesRepository moviesRepository,  Movie movie) {
        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":actor", AttributeValue.builder().s("change actor1").build());

        UpdateItemRequest.Builder builder = moviesRepository.getItemUpdateBuilder(movie);
        builder.updateExpression("set info.actors[0]=:actor");
        builder.expressionAttributeValues(expressionAttributeValues);
        moviesRepository.update(builder);
    }

    private static void addNewColorsAttribute( MoviesRepository moviesRepository,  Movie movie) {
        /*
        DynamodbAttributes expressionAttributeValues = new DynamodbAttributes();
        List<String> actors = new ArrayList<>();
        actors.add("add actor1");
        actors.add("add actor2");
        expressionAttributeValues.putList(":actor", actors);
        */

        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":actor", AttributeValue.builder().ss("add color1", "add color2").build());

        UpdateItemRequest.Builder builder = moviesRepository.getItemUpdateBuilder(movie);
        builder.updateExpression("add info.color :actor");
        builder.expressionAttributeValues(expressionAttributeValues);
        moviesRepository.update(builder);
    }
    private static void addNewColors( MoviesRepository moviesRepository,  Movie movie) {
        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":actor", AttributeValue.builder().ss("add color3", "add color4").build());

        UpdateItemRequest.Builder builder = moviesRepository.getItemUpdateBuilder(movie);
        builder.updateExpression("add info.color :actor");
        builder.expressionAttributeValues(expressionAttributeValues);
        moviesRepository.update(builder);
    }
}
