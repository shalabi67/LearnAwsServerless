package com.learn.framework.dynamodb.sort_key.forum.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.serverless.framework.dynamodb.repository.BaseModule;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public class Forum extends BaseModule {
    private static final String TABLE_NAME = "learn-thread";
    public static final String NAME = "Name";
    public static final String SUBJECT = "Subject";
    public static final String CATEGORY = "Category";
    public static final String THREADS = "Threads";
    public static final String MESSAGES = "Messages";
    public static final String VIEWS = "Views";

    @JsonProperty(THREADS)
    private Integer threads;
    @JsonProperty(MESSAGES)
    private Integer messages;
    @JsonProperty(VIEWS)
    private Integer views;

    @JsonProperty(NAME)
    private String name;
    @JsonProperty(SUBJECT)
    private String subject;
    @JsonProperty(CATEGORY)
    private String category;


    public Forum() {
    }
    public Forum(String name) { ;
        this.name = name;
    }



    @Override
    protected DynamodbAttributes createKey() {
        DynamodbAttributes key = new DynamodbAttributes();
        key.putNumber(NAME, name);

        return key;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public void read(Map<String, AttributeValue> map) {
        DynamodbAttributes attributes = new DynamodbAttributes(map);
        threads = attributes.getInteger(THREADS);
        messages = attributes.getInteger(MESSAGES);
        views = attributes.getInteger(VIEWS);

        name = attributes.getString(NAME);
        category = attributes.getString(CATEGORY);
        subject = attributes.getString(SUBJECT);
    }

    @Override
    public Map<String, AttributeValue> save() {
        DynamodbAttributes attributes = new DynamodbAttributes();
        attributes.putNumber(THREADS, threads);
        attributes.putNumber(MESSAGES, messages);
        attributes.putNumber(VIEWS, views);

        attributes.putString(NAME, name);
        attributes.putString(CATEGORY, category);
        attributes.putString(SUBJECT, subject);

        return attributes.getAttributesMap();
    }

    public Integer getThreads() {
        return threads;
    }

    public void setThreads(Integer threads) {
        this.threads = threads;
    }

    public Integer getMessages() {
        return messages;
    }

    public void setMessages(Integer messages) {
        this.messages = messages;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
