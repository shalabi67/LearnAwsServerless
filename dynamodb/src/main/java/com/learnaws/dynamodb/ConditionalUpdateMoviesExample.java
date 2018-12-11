package com.learnaws.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalOperator;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnaws.dynamodb.model.Movie;
import com.learnaws.dynamodb.repository.MoviesRepository;
import software.amazon.awssdk.utils.ImmutableMap;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ConditionalUpdateMoviesExample {
    public static void main(String[] args) {
        MoviesRepository moviesRepository = new MoviesRepository();
        Movie movie = moviesRepository.find(1925, "Bronenosets Potyomkin");
        if(movie == null) {
            System.out.println("move not found, Year=" + 1925 + " Title=Bronenosets Potyomkin");
            return;
        }

        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedAttributes =
                ImmutableMap.<String, ExpectedAttributeValue>builder()
                        .put("year",  new ExpectedAttributeValue()
                                .withValue(new AttributeValue().withN("1925")))
                        //.put("title", new ExpectedAttributeValue(true))
                        .build();
        saveExpression.setExpected(expectedAttributes);
        //saveExpression.setConditionalOperator(ConditionalOperator.AND);

        movie.getMovieInformation().setRank(1);
        moviesRepository.conditionalSave(movie, saveExpression);
    }
}
