package com.codestates.server.question.service;

import com.codestates.server.auth.utils.AuthUserUtils;
import com.codestates.server.question.entity.Question;
import com.codestates.server.question.entity.QuestionTag;
import com.codestates.server.question.repository.QuestionRepository;
import com.codestates.server.question.repository.QuestionTagRepository;
import com.codestates.server.user.entity.User;
import com.codestates.server.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
                .orElseThrow(() -> new RuntimeException("에러~"));

        if(user.getUserId().equals(userId)){
            question.setViews(0L);
            return questionRepository.save(question);
        }else {
            throw new RuntimeException("유저가 다릅니다.");
        }


    }

    public Question updateQuestion(Question question, Long userId){
        Optional<Question> optionalQuestion = questionRepository.findById(question.getQuestionId());

        Question findedQuestion = optionalQuestion.orElseThrow();
        long savedUserId = findedQuestion.getUser().getUserId();

        if(userId == savedUserId){
            findedQuestion.setTitle(question.getTitle());
            findedQuestion.setContent(question.getContent());
            findedQuestion.setModifiedAt(LocalDateTime.now());
            BeanUtils.copyProperties(findedQuestion,question,"question_id");
            return questionRepository.save(question);
        }else {
            throw new RuntimeException("유저가 다릅니다."); //exception 수정 필요
        }
    }

    public Question findQuestion(Long questionsId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionsId);
        Question question = optionalQuestion.orElseThrow();

        viewCountUp(question);
        questionRepository.save(question);

        return question;
    }

    public List<Question> findAllQuestions(){  //findAll
        return questionRepository.findAll();
    }

    public void deleteQuestion(Long questionId, Long userId){
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question findedQuestion = optionalQuestion.orElseThrow();

        long savedUserId = findedQuestion.getUser().getUserId();

        if(savedUserId == userId){
            questionRepository.delete(findedQuestion);
        }else {
            throw new RuntimeException("유저가 다릅니다.");  //exception 수정 필요
        }

    }

    //조회수 증가(get Question)
    private static void viewCountUp(Question question) {
        Long view = question.getViews();
        question.setViews(++view);
    }
}
