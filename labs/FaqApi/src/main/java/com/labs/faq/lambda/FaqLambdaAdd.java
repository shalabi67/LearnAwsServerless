package com.labs.faq.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.labs.faq.factories.SingletonFactory;
import com.labs.faq.models.Question;
import com.labs.faq.services.QuestionService;

import static com.labs.faq.factories.SingletonFactory.initSingletonFactory;

public class FaqLambdaAdd implements RequestHandler<Question, Question> {
    public FaqLambdaAdd() {
        initSingletonFactory();
    }
    @Override
    public Question handleRequest(Question question, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log(question.toString());
        QuestionService questionService = SingletonFactory.getQuestionService();

        Question newQuestion =  questionService.save(question);
        logger.log(newQuestion.toString());
        return newQuestion;
    }
}
