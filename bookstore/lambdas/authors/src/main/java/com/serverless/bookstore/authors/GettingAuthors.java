package com.serverless.bookstore.authors;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.bookstore.datalyer.builders.AuthorBuilder;
import com.serverless.bookstore.datalyer.modules.Author;
import com.serverless.bookstore.datalyer.repositories.AuthorsRepository;

import java.util.ArrayList;
import java.util.List;

public class GettingAuthors implements RequestHandler<Void, List<Author>> {

	public List<Author> handleRequest(Void request, Context context){
		/*
		Author author = AuthorBuilder.create("Mohammad", "Shalabi", "123");
		*/
		AuthorsRepository authorsRepository = new AuthorsRepository();

		/*
		Author author = authorsRepository.getAuthor(1L);

		List<Author> authors = new ArrayList<>();
		authors.add(author);
		return authors;
		*/
		return authorsRepository.getAuthors();
	}

}
