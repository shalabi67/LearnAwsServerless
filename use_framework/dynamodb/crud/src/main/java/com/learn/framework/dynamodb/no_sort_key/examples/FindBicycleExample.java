package com.learn.framework.dynamodb.no_sort_key.examples;

import com.learn.framework.dynamodb.no_sort_key.models.Bicycle;
import com.learn.framework.dynamodb.no_sort_key.models.ProductCatalog;
import com.learn.framework.dynamodb.no_sort_key.repositories.BicycleRepository;
import com.learn.framework.dynamodb.sort_key.movies.models.Movie;
import com.learn.framework.dynamodb.sort_key.movies.repositories.MoviesRepository;
import com.serverless.framework.dynamodb.factories.DynamodbClientFactory;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;

public class FindBicycleExample  {
    public static void main(String[] args) {
        BicycleRepository bicycleRepository = new BicycleRepository(new DynamodbClientFactory());
        DynamodbAttributes dynamodbAttributes = new DynamodbAttributes();
        dynamodbAttributes.putNumber(ProductCatalog.ID, 123L);
        Bicycle bicycle = bicycleRepository.find(dynamodbAttributes, new Bicycle());
        if(bicycle == null) {
            System.out.println("Could not find movie.");
        } else {
            System.out.println("movie found.");
        }



        //a simpler way
        bicycle = bicycleRepository.find(new Bicycle(123L));
        if(bicycle == null) {
            System.out.println("Could not find movie.");
        } else {
            System.out.println("movie found.");
        }
    }
}
