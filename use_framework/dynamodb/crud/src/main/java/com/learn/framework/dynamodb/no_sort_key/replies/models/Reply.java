package com.learn.framework.dynamodb.no_sort_key.replies.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn.framework.dynamodb.sort_key.movies.models.MovieInformation;
import com.serverless.framework.dynamodb.repository.BaseModule;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public class Reply extends BaseModule {
    private static final String TABLE_NAME = "learn-reply";
    private static final String ID = "Id";
    private static final String REPLY_DATE_TIME = "ReplyDateTime";
    private static final String POSTED_BY = "PostedBy";
    private static final String MESSAGE = "Message";

    @JsonProperty(ID)
    private String id;
    @JsonProperty(MESSAGE)
    private String message;
    @JsonProperty(POSTED_BY)
    private String postedBy;
    @JsonProperty(REPLY_DATE_TIME)
    private String replyDateTime;


    public Reply() {
    }
    public Reply(String id, String replyDateTime) { ;
        this.id = id;
        this.message = replyDateTime;
    }



    @Override
    protected DynamodbAttributes createKey() {
        DynamodbAttributes key = new DynamodbAttributes();
        key.putString(ID, id);
        key.putString(REPLY_DATE_TIME, replyDateTime);


        return key;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public void read(Map<String, AttributeValue> map) {
        DynamodbAttributes attributes = new DynamodbAttributes(map);
        id = attributes.getString(ID);
        replyDateTime = attributes.getString(REPLY_DATE_TIME);
        message = attributes.getString(MESSAGE);
        postedBy = attributes.getString(POSTED_BY);

    }

    @Override
    public Map<String, AttributeValue> save() {
        DynamodbAttributes attributes = new DynamodbAttributes();
        attributes.putString(ID, id);
        attributes.putString(REPLY_DATE_TIME, replyDateTime);
        attributes.putString(MESSAGE, message);
        attributes.putString(POSTED_BY, postedBy);

        return attributes.getAttributesMap();
    }

}
