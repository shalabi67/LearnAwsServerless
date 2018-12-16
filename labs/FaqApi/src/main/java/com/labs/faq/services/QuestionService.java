package com.labs.faq.services;

import com.labs.faq.models.Question;
import com.labs.faq.repositories.QuestionRepository;

public class QuestionService {
    private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question save(Question question) {
        if(question.getQuestion() == null || question.getQuestion().isEmpty()) {
            return new Question();
        }
        return questionRepository.save(question);
    }
}
