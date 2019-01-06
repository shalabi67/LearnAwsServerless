package com.learn.framework.dynamodb.no_sort_key.product_catalog.examples;

import com.learn.framework.dynamodb.no_sort_key.product_catalog.models.Book;
import com.learn.framework.dynamodb.no_sort_key.product_catalog.repositories.BookRepository;
import com.serverless.framework.dynamodb.factories.DynamodbClientFactory;

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
