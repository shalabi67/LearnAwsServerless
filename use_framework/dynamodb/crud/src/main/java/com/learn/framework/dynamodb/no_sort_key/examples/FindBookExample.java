package com.learn.framework.dynamodb.no_sort_key.examples;

import com.learn.framework.dynamodb.no_sort_key.models.Bicycle;
import com.learn.framework.dynamodb.no_sort_key.models.Book;
import com.learn.framework.dynamodb.no_sort_key.models.ProductCatalog;
import com.learn.framework.dynamodb.no_sort_key.repositories.BicycleRepository;
import com.learn.framework.dynamodb.no_sort_key.repositories.BookRepository;
import com.serverless.framework.dynamodb.factories.DynamodbClientFactory;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;

public class FindBookExample {
    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository(new DynamodbClientFactory());
        /*
        DynamodbAttributes dynamodbAttributes = new DynamodbAttributes();
        dynamodbAttributes.putNumber(ProductCatalog.ID, 123L);
        Bicycle bicycle = bicycleRepository.find(dynamodbAttributes, new Bicycle());
        if(bicycle == null) {
            System.out.println("Could not find movie.");
        } else {
            System.out.println("movie found.");
        }
        */



        //a simpler way
        Book book = bookRepository.find(new Book(103L));
        if(book == null) {
            System.out.println("Could not find movie.");
        } else {
            System.out.println("movie found.");
        }
    }
}
