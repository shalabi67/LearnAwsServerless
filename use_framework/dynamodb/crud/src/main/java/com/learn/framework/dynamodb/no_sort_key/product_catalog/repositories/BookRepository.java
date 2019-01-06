package com.learn.framework.dynamodb.no_sort_key.product_catalog.repositories;

import com.learn.framework.dynamodb.no_sort_key.product_catalog.models.Book;
import com.serverless.framework.dynamodb.factories.IDynamoDbClientFactory;
import com.serverless.framework.dynamodb.repository.DynamoDbRepository;

public class BookRepository extends DynamoDbRepository<Book> {
    public BookRepository(IDynamoDbClientFactory dynamoDbClientFactory) {
        super(dynamoDbClientFactory);
    }
}
