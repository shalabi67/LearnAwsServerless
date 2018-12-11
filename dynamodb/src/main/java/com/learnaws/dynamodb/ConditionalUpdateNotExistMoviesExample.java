package com.learnaws.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.learnaws.dynamodb.model.Movie;
import com.learnaws.dynamodb.repository.MoviesRepository;
import software.amazon.awssdk.utils.ImmutableMap;

import java.util.Map;

public class ConditionalUpdateNotExistMoviesExample {
    public static void main(String[] args) {
        MoviesRepository moviesRepository = new MoviesRepository();
        Movie movie = moviesRepository.find(1925, "Bronenosets Potyomkin");
        if(movie == null) {
            System.out.println("move not found, Year=" + 1925 + " Title=Bronenosets Potyomkin");
            return;
        }

        DynamoDBSaveExpression saveExpression =getSaveExpression("1949");
        movie.getMovieInformation().setRank(5);

        try {
            moviesRepository.conditionalSave(movie, saveExpression);
        }
        catch(ConditionalCheckFailedException e){
            System.out.println("This will throw an exception since 1925 is not grater than 1925 and the record will not update");
        }

        saveExpression =getSaveExpression("1919");
        movie.getMovieInformation().setRank(3);
        moviesRepository.conditionalSave(movie, saveExpression);
        System.out.println("This will update and the rank will be 3");
    }
    private static DynamoDBSaveExpression getSaveExpression(String yearValue) {
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        AttributeValue yearAttribute = new AttributeValue();
        yearAttribute.setN(yearValue);
        Map<String, ExpectedAttributeValue> expectedAttributes =
                ImmutableMap.<String, ExpectedAttributeValue>builder()
                        .put("year", new ExpectedAttributeValue(yearAttribute)
                                .withComparisonOperator(ComparisonOperator.GT))
                        //.put("title", new ExpectedAttributeValue(true))
                        .build();
        saveExpression.setExpected(expectedAttributes);

        return saveExpression;
    }
}
