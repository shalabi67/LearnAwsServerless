package com.labs.faq.factories;

import com.labs.faq.repositories.QuestionRepository;
import com.labs.faq.services.QuestionService;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

public class SingletonFactory {
    private static QuestionRepository questionRepository;
    private static Object objectQuestionRepository = new Object();
    private static QuestionService questionService;
    private static Object objectQuestionService = new Object();

    public static QuestionService getQuestionService() {
        if(questionService == null) {
            synchronized (objectQuestionService) {
                if(questionService == null) {
                    questionService = new QuestionService(getQuestionRepository());
                }
            }
        }
        return questionService;
    }

    public static QuestionRepository getQuestionRepository() {
        if(questionRepository == null) {
            synchronized (objectQuestionRepository) {
                if(questionRepository == null) {
                    questionRepository = new QuestionRepository();
                }
            }
        }
        return questionRepository;
    }
    public static void initSingletonFactory() {
        getQuestionService();
    }
}
