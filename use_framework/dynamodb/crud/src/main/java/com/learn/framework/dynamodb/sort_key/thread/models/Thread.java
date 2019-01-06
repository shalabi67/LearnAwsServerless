package com.learn.framework.dynamodb.sort_key.thread.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.serverless.framework.dynamodb.factories.types.DynamoTypeEnum;
import com.serverless.framework.dynamodb.repository.BaseModule;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.List;
import java.util.Map;

public class Thread extends BaseModule {
    private static final String TABLE_NAME = "learn-thread";
    private static final String FORUM_NAME = "ForumName";
    private static final String SUBJECT = "Subject";
    private static final String MESSAGE = "Message";
    private static final String VIEWS = "Views";
    private static final String LAST_POSTED_BY = "LastPostedBy";
    private static final String LAST_POSTED_DATE_TIME = "LastPostedDateTime";
    private static final String REPLIES = "Replies";
    private static final String ANSWERED = "Answered";
    private static final String TAGS = "Tags";

    @JsonProperty(ANSWERED)
    private Integer answered;
    @JsonProperty(REPLIES)
    private Integer replies;
    @JsonProperty(VIEWS)
    private Integer views;

    @JsonProperty(FORUM_NAME)
    private String forumName;
    @JsonProperty(SUBJECT)
    private String subject;
    @JsonProperty(MESSAGE)
    private String message;
    @JsonProperty(LAST_POSTED_BY)
    private String lastPostedBy;
    @JsonProperty(LAST_POSTED_DATE_TIME)
    private String lastPostedDateTime;

    @JsonProperty(TAGS)
    private List<String> tags;


    public Thread() {
    }
    public Thread(String forumName, String subject) { ;
        this.forumName = forumName;
        this.subject = subject;
    }



    @Override
    protected DynamodbAttributes createKey() {
        DynamodbAttributes key = new DynamodbAttributes();
        key.putString(FORUM_NAME, forumName);
        key.putString(SUBJECT, subject);

        return key;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public void read(Map<String, AttributeValue> map) {
        DynamodbAttributes attributes = new DynamodbAttributes(map);
        answered = attributes.getInteger(ANSWERED);
        replies = attributes.getInteger(REPLIES);
        views = attributes.getInteger(VIEWS);

        forumName = attributes.getString(FORUM_NAME);
        message = attributes.getString(MESSAGE);
        subject = attributes.getString(SUBJECT);
        lastPostedBy = attributes.getString(LAST_POSTED_BY);
        lastPostedDateTime = attributes.getString(LAST_POSTED_DATE_TIME);

        tags = attributes.getList(TAGS, DynamoTypeEnum.string);
    }

    @Override
    public Map<String, AttributeValue> save() {
        DynamodbAttributes attributes = new DynamodbAttributes();
        attributes.putNumber(ANSWERED, answered);
        attributes.putNumber(REPLIES, replies);
        attributes.putNumber(VIEWS, views);

        attributes.putString(FORUM_NAME, forumName);
        attributes.putString(MESSAGE, message);
        attributes.putString(SUBJECT, subject);
        attributes.putString(LAST_POSTED_DATE_TIME, lastPostedDateTime);
        attributes.putString(LAST_POSTED_BY, lastPostedBy);

        attributes.putList(TAGS, tags, DynamoTypeEnum.string);

        return attributes.getAttributesMap();
    }


    public Integer getAnswered() {
        return answered;
    }

    public void setAnswered(Integer answered) {
        this.answered = answered;
    }

    public Integer getReplies() {
        return replies;
    }

    public void setReplies(Integer replies) {
        this.replies = replies;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLastPostedBy() {
        return lastPostedBy;
    }

    public void setLastPostedBy(String lastPostedBy) {
        this.lastPostedBy = lastPostedBy;
    }

    public String getLastPostedDateTime() {
        return lastPostedDateTime;
    }

    public void setLastPostedDateTime(String lastPostedDateTime) {
        this.lastPostedDateTime = lastPostedDateTime;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
