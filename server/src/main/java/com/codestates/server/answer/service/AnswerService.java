package com.codestates.server.answer.service;

import com.codestates.server.answer.entity.Answer;
import com.codestates.server.answer.repository.AnswerRepository;
import com.codestates.server.question.entity.Question;
import com.codestates.server.question.service.QuestionService;
import com.codestates.server.user.entity.User;
import com.codestates.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

// 예외처리 및 코드 추가 예정.
@Service
@Transactional
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionService questionService;

    public AnswerService(AnswerRepository answerRepository, QuestionService questionService) {
        this.answerRepository = answerRepository;
        this.questionService = questionService;
    }

    public List<Answer> createAnswer(Answer answer, Long questionId) {
        Question question = questionService.findQuestion(questionId);

        answer.setQuestion(question);
        answerRepository.save(answer);

        return answerRepository.findByQuestionId(question.getQuestionId());
    }

    public List<Answer> updateAnswer(Answer answer, long questionId) {
        Question question = questionService.findQuestion(questionId);
        Answer findAnswer = findAnswerById(answer.getAnswerId());

        findAnswer.setContent(answer.getContent());
        answerRepository.save(findAnswer);
        return answerRepository.findByQuestionId(question.getQuestionId());
    }

    public List<Answer> getAnswer(long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    public List<Answer> deleteAnswer(long questionId, long answerId) {
        Question question = questionService.findQuestion(questionId);
        Answer answer = findAnswerById(answerId);

        answerRepository.deleteById(answer.getAnswerId());
        return answerRepository.findByQuestionId(question.getQuestionId());
    }

    public Answer findAnswerById(long answerId) {
        return answerRepository.findById(answerId).orElse(null);
    }
}
