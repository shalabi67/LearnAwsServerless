package com.learnaws.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.learnaws.dynamodb.model.Movie;
import com.learnaws.dynamodb.repository.MoviesRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryMoviesExample {
    public static void main(String[] args) {
        Map<String,String> expressionAttributesNames = new HashMap<>();
        expressionAttributesNames.put("#year","year");
        expressionAttributesNames.put("#title","title");

        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":yearParam", new AttributeValue().withN("1925"));
        eav.put(":titleParam", new AttributeValue().withS("The Big Parade"));

        DynamoDBQueryExpression<Movie> queryExpression = new DynamoDBQueryExpression<Movie>()
                .withKeyConditionExpression("#year = :yearParam and #title = :titleParam")
                .withExpressionAttributeNames(expressionAttributesNames)
                .withExpressionAttributeValues(eav);

        MoviesRepository moviesRepository = new MoviesRepository();
        List<Movie> movies = moviesRepository.query(queryExpression);
        for(Movie movie1 : movies){
            System.out.println("Year=" + movie1.getYear());
        }
    }
}
