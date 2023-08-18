package com.codestates.server.answer.service;

import com.codestates.server.answer.entity.Answer;
import com.codestates.server.answer.repository.AnswerRepository;
import com.codestates.server.question.entity.Question;
import com.codestates.server.question.service.QuestionService;
import com.codestates.server.user.entity.User;
import com.codestates.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionService questionService;

    public AnswerService(AnswerRepository answerRepository, QuestionService questionService) {
        this.answerRepository = answerRepository;
        this.questionService = questionService;
    }

    public Answer createAnswer(Answer answer, Long questionId) {
        Question question = questionService.findQuestion(questionId);
        answer.setQuestion(question);
        answerRepository.save(answer);

        return answer;
    }

    public Answer updateAnswer(Answer answer, long questionId) {
        Question question = questionService.findQuestion(questionId);
        Answer existingAnswer = findAnswerById(answer.getAnswerId());

        if (existingAnswer != null) {
            existingAnswer.setContent(answer.getContent());
            answerRepository.save(existingAnswer);
            return existingAnswer;
        }

        throw new EntityNotFoundException("답변이 검색되지 않습니다.");
    }

//    public List<Answer> getAnswers(long questionId) {
//        return answerRepository.findByQuestionId(questionId);
//    }

    public void deleteAnswer(long questionId, long answerId) {
        Answer existingAnswer = findAnswerById(answerId);

        if (existingAnswer != null) {
            if (existingAnswer.getQuestion().getQuestionId() == questionId) {
                answerRepository.deleteById(answerId);
            } else {
                throw new IllegalArgumentException("답변이 해당 질문에 연결되어 있지 않습니다.");
            }
        } else {
            throw new EntityNotFoundException("답변이 검색되지 않습니다.");
        }
    }

    public Answer findAnswerById(long answerId) {
        return answerRepository.findById(answerId).orElse(null);
    }
}