package com.learn.framework.dynamodb.no_sort_key.replies.exampels;

import com.learn.framework.dynamodb.no_sort_key.replies.models.Reply;
import com.learn.framework.dynamodb.no_sort_key.replies.repositories.ReplyRepository;
import com.learn.framework.dynamodb.sort_key.thread.models.Thread;
import com.learn.framework.dynamodb.sort_key.thread.repositories.ThreadsRepository;
import com.serverless.framework.dynamodb.factories.DynamodbClientFactory;
import com.serverless.framework.dynamodb.query.Query;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;

import java.util.List;

public class ReplyQueryExamples {
    public static void main(String[] args) {
        ReplyRepository replyRepository = new ReplyRepository(new DynamodbClientFactory());

        findByIdAndReplyDateTimeBeginsWith(replyRepository);
    }

    private static void findByIdAndReplyDateTimeBeginsWith(ReplyRepository threadsRepository) {
        DynamodbAttributes attributes = new DynamodbAttributes();
        attributes.putString(":id", "Amazon DynamoDB#DynamoDB Thread 1");
        attributes.putString(":dt", "2015-09");

        Reply reply = new Reply();
        reply.setQuery(new Query("Id = :id and begins_with(ReplyDateTime, :dt)", attributes));

        List<Reply> threads = threadsRepository.findBy(reply);

        /*
        {
    ":id":{"S":"Amazon DynamoDB#DynamoDB Thread 1"},
    ":dt":{"S":"2015-09"}
}
         */
    }

    private static void findByForumName(ThreadsRepository threadsRepository) {
        DynamodbAttributes attributes = new DynamodbAttributes();
        attributes.putString(":name", "Amazon DynamoDB");

        Thread thread = new Thread();
        thread.setQuery(new Query("ForumName = :name", attributes));

        List<Thread> threads = threadsRepository.findBy(thread);


        /*
        {":name":{"S":"Amazon DynamoDB"}}
         */
    }
}
