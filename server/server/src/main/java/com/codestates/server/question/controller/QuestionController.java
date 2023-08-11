package com.codestates.server.question.controller;

import com.codestates.server.question.dto.QuestionPostDto;
import com.codestates.server.question.dto.QuestionResponseDto;
import com.codestates.server.question.dto.Response;
import com.codestates.server.question.entity.Question;
import com.codestates.server.question.mapper.QuestionMapper;
import com.codestates.server.question.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper mapper;

    public QuestionController(QuestionService questionService, QuestionMapper mapper) {
        this.questionService = questionService;
        this.mapper = mapper;
    }

    @PostMapping("/ask")
    public ResponseEntity<Response<?>> postQuestion(@RequestBody QuestionPostDto questionPostDto){
        Question question = questionService.createQuestion(mapper.questionPostDtoToQuestion(questionPostDto));
        Response<QuestionResponseDto> questionResponseDtoResponse = new Response<>(questionPostDto.getUserId(), mapper.questionToQuestionResponseDto(question));
        return new ResponseEntity<>(questionResponseDtoResponse, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{question-id}")
    public String patchQuestion(@PathVariable("question-id") Long questionsId){

        return "patch" + questionsId.toString();
    }

    @GetMapping("/{question-id}")
    public ResponseEntity<QuestionResponseDto> getQuestion(@PathVariable("question-id") Long questionsId){
        Question question = questionService.findQuestion(questionsId);

        return new ResponseEntity<>(mapper.questionToQuestionResponseDto(question),HttpStatus.OK);
    }

    @GetMapping
    public String getQuestions(){
        return "get all";
    }

    @DeleteMapping("/{question-id}")
    public String deleteQuestion(@PathVariable("question-id") Long questionId){

        return "delete" + questionId.toString();
    }
}
