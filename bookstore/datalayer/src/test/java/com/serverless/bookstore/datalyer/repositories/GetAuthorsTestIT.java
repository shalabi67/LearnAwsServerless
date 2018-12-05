package com.serverless.bookstore.datalyer.repositories;

import com.serverless.bookstore.datalyer.modules.Author;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GetAuthorsTestIT {
	private AuthorsRepository authorsRepository;

	@Before
	public void setup() {
		authorsRepository = new AuthorsRepository();
	}

	@Test
	public void testGetAuthors() {
		List<Author> authors = authorsRepository.getAuthors();
	}

}
