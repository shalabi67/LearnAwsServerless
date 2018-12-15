package com.labs.faq.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.labs.faq.models.Question;

public class FaqLambdaAdd implements RequestHandler<Question, Question> {
    @Override
    public Question handleRequest(Question question, Context context) {
        return question;
    }
}
