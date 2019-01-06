package com.learn.framework.dynamodb.sort_key.thread.examples;

import com.learn.framework.dynamodb.sort_key.thread.repositories.ThreadsRepository;
import com.serverless.framework.dynamodb.factories.DynamodbClientFactory;
import com.learn.framework.dynamodb.sort_key.thread.models.Thread;
import com.serverless.framework.dynamodb.query.Query;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryExamples {
    public static void main(String[] args) {
        ThreadsRepository threadsRepository = new ThreadsRepository(new DynamodbClientFactory());

        //Query the Thread table for a particular ForumName
        //findByForumName(threadsRepository);

        //findByForumNameAndSubject(threadsRepository);
        findByForumNameAndViewsGT3(threadsRepository);
    }

    private static void findByForumNameAndSubject(ThreadsRepository threadsRepository) {
        //best way for this query since it is covered by the index
        Thread thread = new Thread("Amazon DynamoDB", "DynamoDB Thread 1");
        Thread newThread = threadsRepository.find(thread);

        //second way
        DynamodbAttributes attributes = new DynamodbAttributes();
        attributes.putString(":name", "Amazon DynamoDB");
        attributes.putString(":sub", "DynamoDB Thread 1");
        Thread thread2 = new Thread();
        thread2.setQuery(new Query("ForumName = :name and Subject = :sub", attributes));

        List<Thread> threads = threadsRepository.findBy(thread2);

        /*
        {
    ":name":{"S":"Amazon DynamoDB"},
    ":sub":{"S":"DynamoDB Thread 1"}
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

    private static void findByForumNameAndViewsGT3(ThreadsRepository threadsRepository) {
        DynamodbAttributes attributes = new DynamodbAttributes();
        attributes.putString(":fn", "Amazon DynamoDB#DynamoDB Thread 1");
        attributes.putNumber(":num", 3);

        Map<String, String> map = new HashMap<>();
        map.put("#v", "Views");

        Thread thread = new Thread();
        thread.setQuery(new Query("ForumName = :fn", "#v >= :num", attributes, map));

        List<Thread> threads = threadsRepository.findBy(thread);


        /*
        {
    ":fn":{"S":"Amazon DynamoDB#DynamoDB Thread 1"},
    ":num":{"N":"3"}
}
         */
    }
}
