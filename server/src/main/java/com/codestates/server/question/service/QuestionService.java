package com.codestates.server.question.service;

import com.codestates.server.auth.utils.AuthUserUtils;
import com.codestates.server.exception.BusinessLogicException;
import com.codestates.server.exception.ExceptionCode;
import com.codestates.server.question.entity.Question;
import com.codestates.server.question.repository.QuestionRepository;
import com.codestates.server.user.entity.User;
import com.codestates.server.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public Question createQuestion(Question question, Long userId){

        User user = userRepository.findByEmail(AuthUserUtils.getAuthUser().getName())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

        if(user.getUserId().equals(userId)){
            question.setViews(0L);
            return questionRepository.save(question);
        }else {
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_USER);
        }
    }

    public Question updateQuestion(Question question, Long userId){

        Question findedQuestion = questionRepository.findById(question.getQuestionId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        long savedUserId = findedQuestion.getUser().getUserId();

        if(userId == savedUserId){
            findedQuestion.setTitle(question.getTitle());
            findedQuestion.setContent(question.getContent());
            findedQuestion.setModifiedAt(LocalDateTime.now());
            BeanUtils.copyProperties(findedQuestion,question,"question_id");  //questionId 빼고 복사
            return questionRepository.save(question);
        }else {
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_USER);
        }
    }

    public Question findQuestion(Long questionsId) {
        Question question = questionRepository.findById(questionsId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
        viewCountUp(question);
        questionRepository.save(question);

        return question;
    }

    public List<Question> findAllQuestions(){  //페이지네이션?
        return questionRepository.findAll();
    }

    public void deleteQuestion(Long questionId, Long userId){
        Question findedQuestion = questionRepository.findById(questionId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        long savedUserId = findedQuestion.getUser().getUserId();

        if(savedUserId == userId){
            questionRepository.delete(findedQuestion);
        }else {
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_USER);
        }
    }

    //조회수 증가(get Question)
    private static void viewCountUp(Question question) {
        Long view = question.getViews();
        question.setViews(++view);
    }
}
