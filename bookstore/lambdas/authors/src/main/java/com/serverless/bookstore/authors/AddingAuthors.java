package com.serverless.bookstore.authors;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.bookstore.datalyer.modules.Author;
import com.serverless.bookstore.datalyer.modules.Id;
import com.serverless.bookstore.datalyer.repositories.AuthorsRepository;

import java.util.List;

public class AddingAuthors implements RequestHandler<Author, Author> {
    @Override
    public Author handleRequest(Author author, Context context) {
        AuthorsRepository authorsRepository = new AuthorsRepository();
        Author newAuthor = authorsRepository.addAuthor(author);
        return newAuthor;
    }
}
