package com.serverless.bookstore.datalyer.repositories;

import com.serverless.bookstore.datalyer.builders.AuthorBuilder;
import com.serverless.bookstore.datalyer.modules.Author;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AddAuthorsTestIT {
	private AuthorsRepository authorsRepository;

	@Before
	public void setup() {
		authorsRepository = new AuthorsRepository();
	}

	@Test
	public void testAddAuthors() {
		Author author = AuthorBuilder.create("firstName", "lastName", "homePhoneNumber");
		Author newAuthor = authorsRepository.addAuthor(author);
	}

}
