package com.learn.framework.dynamodb.sort_key.movies.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.serverless.framework.dynamodb.factories.types.DynamoTypeEnum;
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
    public static final String RATING = "rating";
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
        directors = attributes.getList(DIRECTORS, DynamoTypeEnum.string);
        releaseDate = attributes.getString(RELEASE_DATE);
        rating = attributes.getFloat(RATING);
        genres = attributes.getList(GENRES, DynamoTypeEnum.string);
        imageUrl = attributes.getString(IMAGE_URL);
        rank = attributes.getInteger(RANK);
        runningTimeSeconds = attributes.getInteger(RUNNING_TIME_SECS);
        actors = attributes.getList(ACTORS, DynamoTypeEnum.string);
        plot = attributes.getString(PLOT);
    }


    @Override
    public Map<String, AttributeValue> save() {
        DynamodbAttributes attributes = new DynamodbAttributes();
        attributes.putList(DIRECTORS, directors, DynamoTypeEnum.string);
        attributes.putString(RELEASE_DATE, releaseDate);
        attributes.putNumber(RATING, rating);
        attributes.putList(GENRES, genres, DynamoTypeEnum.string);
        attributes.putString(IMAGE_URL, imageUrl);
        attributes.putNumber(RANK, rank);
        attributes.putNumber(RUNNING_TIME_SECS, runningTimeSeconds);
        attributes.putList(ACTORS, actors, DynamoTypeEnum.string);
        attributes.putString(PLOT, plot);

        return attributes.getAttributesMap();
    }


    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getRunningTimeSeconds() {
        return runningTimeSeconds;
    }

    public void setRunningTimeSeconds(Integer runningTimeSeconds) {
        this.runningTimeSeconds = runningTimeSeconds;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }
}
