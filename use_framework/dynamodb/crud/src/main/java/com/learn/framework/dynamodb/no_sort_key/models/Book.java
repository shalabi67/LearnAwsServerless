package com.learn.framework.dynamodb.no_sort_key.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.serverless.framework.dynamodb.factories.types.DynamoTypeEnum;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;

import java.util.List;

public class Book extends ProductCatalog {
    private static final String ISBN = "ISBN";
    private static final String AUTHORS = "Authors";
    private static final String DIMENSIONS = "Dimensions";
    private static final String PAGE_COUNT = "PageCount";
    private static final String IN_PUBLICATION = "InPublication";


    @JsonProperty(ISBN)
    private String isbn;
    @JsonProperty(AUTHORS)
    private List<String> authors;
    @JsonProperty(DIMENSIONS)
    private String dimensions;
    @JsonProperty(PAGE_COUNT)
    private Long pageCount;
    @JsonProperty(IN_PUBLICATION)
    private Boolean inPublication;

    public Book() {

    }

    public Book(Long id) {
        super(id);
    }

    @Override
    protected void readProductInformation(DynamodbAttributes attributes) {
        isbn = attributes.getString(ISBN);
        authors = attributes.getList(AUTHORS, DynamoTypeEnum.string);
        dimensions = attributes.getString(DIMENSIONS);
        pageCount = attributes.getLong(PAGE_COUNT);
        inPublication = attributes.getBoolean(IN_PUBLICATION);
    }

    @Override
    protected DynamodbAttributes saveProductInformation() {
        DynamodbAttributes attributes = new DynamodbAttributes();
        attributes.putString(ISBN, isbn);
        attributes.putList(AUTHORS, authors, DynamoTypeEnum.string);
        attributes.putString(DIMENSIONS, dimensions);
        attributes.putNumber(PAGE_COUNT, pageCount);
        attributes.putBoolean(IN_PUBLICATION, inPublication);

        return attributes;
    }
}