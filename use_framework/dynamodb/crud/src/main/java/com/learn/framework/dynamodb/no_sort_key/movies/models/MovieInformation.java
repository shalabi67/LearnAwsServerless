package com.learn.framework.dynamodb.no_sort_key.movies.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.serverless.framework.dynamodb.repository.BasicModel;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;
import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class MovieInformation implements BasicModel {
    private static final String DIRECTORS = "directors";
    private static final String RELEASE_DATE = "release_date";
    private static final String RATING = "rating";
    private static final String GENRES = "genres";
    private static final String IMAGE_URL = "image_url";
    private static final String RANK = "rank";
    private static final String RUNNING_TIME_SECS = "running_time_secs";
    private static final String ACTORS = "actors";
    private static final String PLOT = "plot";


    private List<String> directors;
    @JsonProperty(RELEASE_DATE)
    private String releaseDate;
    private Float rating;
    private List<String> genres;
    @JsonProperty(IMAGE_URL)
    private String imageUrl;
    private Integer rank;
    @JsonProperty(RUNNING_TIME_SECS)
    private Integer runningTimeSeconds;
    private List<String> actors;
    private String plot;


    @Override
    public void read(Map<String, AttributeValue> map) {
        DynamodbAttributes attributes = new DynamodbAttributes(map);
        directors = attributes.getList(DIRECTORS);
        releaseDate = attributes.getString(RELEASE_DATE);
        rating = attributes.getFloat(RATING);
        genres = attributes.getList(GENRES);
        imageUrl = attributes.getString(IMAGE_URL);
        rank = attributes.getInteger(RANK);
        runningTimeSeconds = attributes.getInteger(RUNNING_TIME_SECS);
        actors = attributes.getList(ACTORS);
        plot = attributes.getString(PLOT);
    }


    @Override
    public Map<String, AttributeValue> save() {
        DynamodbAttributes attributes = new DynamodbAttributes();
        attributes.putList(DIRECTORS, directors);
        attributes.putString(RELEASE_DATE, releaseDate);
        attributes.putNumber(RATING, rating);
        attributes.putList(GENRES, genres);
        attributes.putString(IMAGE_URL, imageUrl);
        attributes.putNumber(RANK, rank);
        attributes.putNumber(RUNNING_TIME_SECS, runningTimeSeconds);
        attributes.putList(ACTORS, actors);
        attributes.putString(PLOT, plot);

        return attributes.getAttributesMap();
    }


}
