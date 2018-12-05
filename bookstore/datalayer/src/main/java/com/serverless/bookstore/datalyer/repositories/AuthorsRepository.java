package com.serverless.bookstore.datalyer.repositories;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.serverless.bookstore.datalyer.modules.Author;

import java.util.List;

public class AuthorsRepository {
	private AmazonDynamoDB dynamoDb;

	public AuthorsRepository() {
		initDynamoDbClient();
	}

	public Author getAuthor(Long authorId) {
		DynamoDBMapper mapper = new DynamoDBMapper(dynamoDb);
		Author author = mapper.load(Author.class, authorId);

		return author;

		/*
		Author author = new Author();
		author.setAuthorId(authorId);

		DynamoDBQueryExpression<Author> queryExpression = new DynamoDBQueryExpression<Author>()
				.withHashKeyValues(author);

		List<Author> authors = mapper.query(Author.class, queryExpression);
		if(authors.size() > 1) {
			return authors.get(0);
		}

		return null;
		*/
	}
	public List<Author> getAuthors() {
		DynamoDBMapper mapper = new DynamoDBMapper(dynamoDb);
		List<Author> authors = mapper.scan(Author.class, new DynamoDBScanExpression());

		return authors;
	}

	private void initDynamoDbClient() {
		//dynamoDb = AmazonDynamoDBClientBuilder.standard().build();
		dynamoDb = AmazonDynamoDBClientBuilder.defaultClient();
	}
}
