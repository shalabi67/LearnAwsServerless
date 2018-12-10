package com.learnaws.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;

@DynamoDBTable(tableName="learn-movies")
public class Movie {


    private Integer year;
    private String title;
    @JsonProperty("info")
    @DynamoDBAttribute(attributeName="info")
    private MovieInformation movieInformation;

    @DynamoDBHashKey(attributeName="year")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @DynamoDBRangeKey
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBAttribute(attributeName="info")
    public MovieInformation getMovieInformation() {
        return movieInformation;
    }

    public void setMovieInformation(MovieInformation movieInformation) {
        this.movieInformation = movieInformation;
    }
}