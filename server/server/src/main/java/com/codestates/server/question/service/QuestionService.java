package com.codestates.server.question.service;

import com.codestates.server.question.entity.Question;
import com.codestates.server.question.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question createQuestion(Question question){

        return questionRepository.save(question);
    }

    public Question findQuestion(Long questionsId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionsId);
        Question question = optionalQuestion.orElseThrow();

        viewCountUp(question);
        questionRepository.save(question);

        return question;
    }


    private static void viewCountUp(Question question) {
        Long view = question.getViews();
        question.setViews(++view);
    }
}
