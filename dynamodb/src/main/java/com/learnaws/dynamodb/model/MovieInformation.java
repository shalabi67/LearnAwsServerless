package com.learnaws.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

@DynamoDBDocument
public class MovieInformation {
    private Set<String> directors;
    @JsonProperty("release_date")
    private String releaseDate;
    private Float rating;
    private Set<String> genres;
    @JsonProperty("image_url")
    private String imageUrl;
    private Integer rank;
    @JsonProperty("running_time_secs")
    private Integer runningTimeSeconds;
    private Set<String> actors;
    private String plot;

    public Set<String> getDirectors() {
        return directors;
    }

    public void setDirectors(Set<String> directors) {
        this.directors = directors;
    }


    @DynamoDBAttribute(attributeName="release_date")
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String release_date) {
        this.releaseDate = release_date;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    @DynamoDBAttribute(attributeName="image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    @DynamoDBAttribute(attributeName="image_url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @DynamoDBAttribute(attributeName="running_time_secs")
    public Integer getRunningTimeSeconds() {
        return runningTimeSeconds;
    }

    @DynamoDBAttribute(attributeName="running_time_secs")
    public void setRunningTimeSeconds(Integer runningTimeSeconds) {
        this.runningTimeSeconds = runningTimeSeconds;
    }

    public Set<String> getActors() {
        return actors;
    }

    public void setActors(Set<String> actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }
}