package com.codestates.server.answer.service;

import com.codestates.server.answer.entity.Answer;
import com.codestates.server.answer.repository.AnswerRepository;
import com.codestates.server.question.entity.Question;
import com.codestates.server.question.service.QuestionService;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

//예외처리 추가 예정.
//@Service
//public class AnswerService {
//
//    private final AnswerRepository answerRepository;
//    private final QuestionService questionService;
//
//    public AnswerService(AnswerRepository answerRepository, QuestionService questionService) {
//        this.answerRepository = answerRepository;
//        this.questionService = questionService;
//    }
//
//    public List<Answer> createAnswer(Answer answer, Long questionId) {
//        Question question = questionService.findQuestion(questionId);
//
//        answer.setQuestion(question);
//        answerRepository.save(answer);
//
//        return getAnswersByQuestionId(questionId);
//    }
//
//    public List<Answer> updateAnswer(Answer answer, long questionId) {
//        Question question = questionService.findQuestion(questionId);
//        Answer existingAnswer = findAnswerById(answer.getAnswerId());
//
//        if (existingAnswer != null) {
//            existingAnswer.setContent(answer.getContent());
//            answerRepository.save(existingAnswer);
//            return getAnswersByQuestionId(questionId);
//        }
//
//        return Collections.emptyList(); // 또는 예외 처리
//    }
//
//    public List<Answer> getAnswersByQuestionId(long questionId) {
//        return answerRepository.findByQuestionId(questionId);
//    }
//
//    public List<Answer> deleteAnswer(long questionId, long answerId) {
//        Question question = questionService.findQuestion(questionId);
//        Answer answer = findAnswerById(answerId);
//
//        if (answer != null) {
//            answerRepository.deleteById(answerId);
//            return getAnswersByQuestionId(questionId);
//        }
//
//        return Collections.emptyList(); // 또는 예외 처리
//    }
//
//    public Answer findAnswerById(long answerId) {
//        return answerRepository.findById(answerId).orElse(null);
//    }
//}
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionService questionService;

    public AnswerService(AnswerRepository answerRepository, QuestionService questionService) {
        this.answerRepository = answerRepository;
        this.questionService = questionService;
    }

    public Answer createAnswer(Answer answer, Long questionsId) {
        Question question = questionService.findQuestion(questionsId);

        answer.setQuestion(question);
        answerRepository.save(answer);

        return answer;
    }

    public Answer updateAnswer(Answer answer, long questionsId) {
        Question question = questionService.findQuestion(questionsId);
        Answer existingAnswer = findAnswerById(answer.getAnswerId());

        if (existingAnswer != null) {
            existingAnswer.setContent(answer.getContent());
            answerRepository.save(existingAnswer);
            return existingAnswer;
        }

        throw new EntityNotFoundException("답변이 검색되지 않습니다.");
    }


    public List<Answer> getAnswersByQuestionsId(long questionsId) {
        return answerRepository.findByQuestionId(questionsId);
    }

    public void deleteAnswer(long questionsId, long answerId) {
        answerRepository.deleteById(answerId);
    }

    public Answer findAnswerById(long answerId) {
        return answerRepository.findById(answerId).orElse(null);
    }
}


