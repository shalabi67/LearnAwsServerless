package com.learn.framework.dynamodb.no_sort_key.examples;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.framework.dynamodb.no_sort_key.models.Book;
import com.learn.framework.dynamodb.no_sort_key.repositories.BookRepository;
import com.learn.framework.dynamodb.sort_key.movies.models.Movie;
import com.learn.framework.dynamodb.sort_key.movies.repositories.MoviesRepository;
import com.serverless.framework.dynamodb.factories.DynamodbClientFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AddBooksExample {
    public static void main(String[] args) {
        ClassLoader classLoader = new AddBooksExample().getClass().getClassLoader();

        File file = new File(classLoader.getResource("books.json").getFile());
        try {
            List<Book> list = new ObjectMapper().readValue(file, new TypeReference<List<Book>>(){});
            BookRepository booksRepository = new BookRepository(new DynamodbClientFactory());
            int i = 0;
            for(Book book : list) {
                booksRepository.save(book);
                System.out.println("Book Added = " + ++i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
