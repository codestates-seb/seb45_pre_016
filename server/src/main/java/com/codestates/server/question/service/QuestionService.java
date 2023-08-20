package com.codestates.server.question.service;

import com.codestates.server.question.entity.Question;
import com.codestates.server.question.entity.QuestionTag;
import com.codestates.server.question.repository.QuestionRepository;
import com.codestates.server.question.repository.QuestionTagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question createQuestion(Question question){

        question.setViews(0L);
        return questionRepository.save(question);
    }

    public Question updateQuestion(Question question, Long userId){
        Optional<Question> optionalQuestion = questionRepository.findById(question.getQuestionId());

        Question findedQuestion = optionalQuestion.orElseThrow();
        Long savedUserId = findedQuestion.getUser().getUserId();

        log.info("user findeduser = {} , requeser = {}", savedUserId, userId);

        if(userId == savedUserId){
            findedQuestion.setTitle(question.getTitle());
            findedQuestion.setContent(question.getContent());
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
