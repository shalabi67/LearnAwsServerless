package com.learn.framework.dynamodb.sort_key.movies.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.serverless.framework.dynamodb.repository.BaseModule;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public class Movie extends BaseModule {
    private static final String TABLE_NAME = "learn-movies";
    public static final String YEAR = "year";
    public static final String TITLE = "title";
    public static final String INFO = "info";
    private Long year;
    private String title;
    @JsonProperty("info")
    private MovieInformation movieInformation;

    public Movie() {
        tableName = TABLE_NAME;
    }
    public Movie(Long year, String title) {
        tableName = TABLE_NAME;
        this.setTitle(title);
        this.setYear(year);
    }

    @Override
    protected DynamodbAttributes createKey() {
        DynamodbAttributes key = new DynamodbAttributes();
        key.putNumber(YEAR, year);
        key.putString(TITLE, title);

        return key;
    }

    @Override
    public void read(Map<String, AttributeValue> map) {
        DynamodbAttributes attributes = new DynamodbAttributes(map);
        year = attributes.getLong(YEAR);
        title = attributes.getString(TITLE);
        movieInformation = attributes.getObject(INFO, new MovieInformation());
    }

    @Override
    public Map<String, AttributeValue> save() {
        DynamodbAttributes attributes = new DynamodbAttributes();
        attributes.putNumber(YEAR, year);
        attributes.putString(TITLE, title);
        attributes.putObject(INFO, movieInformation);

        return attributes.getAttributesMap();
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MovieInformation getMovieInformation() {
        return movieInformation;
    }

    public void setMovieInformation(MovieInformation movieInformation) {
        this.movieInformation = movieInformation;
    }
}
