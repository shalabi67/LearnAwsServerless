package com.serverless.bookstore.datalyer.builders;

import com.serverless.bookstore.datalyer.modules.Author;

public class AuthorBuilder implements IBuilder<Author> {
	private Author author = new Author();

	public static Author create(String firstName, String lastName, String homePhoneNumber) {
		Author author = new Author();
		author.setHomePhoneNumber(homePhoneNumber);
		author.setFirstName(firstName);
		author.setLastName(lastName);

		return author;
	}

	public AuthorBuilder(Author author) {
		this.author = author;
	}

	@Override public Author build() {
		return author;
	}

	public AuthorBuilder firstName(String firstName) {
		author.setFirstName(firstName);
		return this;
	}
	public AuthorBuilder lastName(String lastName) {
		author.setLastName(lastName);
		return this;
	}
	public AuthorBuilder homePhoneNumber(String number) {
		author.setHomePhoneNumber(number);
		return this;
	}
}
