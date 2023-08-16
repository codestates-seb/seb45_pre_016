package com.codestates.server.answer.controller;


import com.codestates.server.answer.dto.AnswerPatchDto;
import com.codestates.server.answer.dto.AnswerPostDto;
import com.codestates.server.answer.dto.AnswerResponseDto;
import com.codestates.server.answer.entity.Answer;
import com.codestates.server.answer.mapper.AnswerMapper;
import com.codestates.server.answer.service.AnswerService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

//자세한 내용은 수정 예정.
@RestController
@RequestMapping("/question/{question-id}/answer")
@Validated
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;

    @GetMapping
    public ResponseEntity getAnswer(@PathVariable("question-id") @Positive long questionId) {
        List<Answer> answers = answerService.getAnswer(questionId);
        List<AnswerResponseDto> responses = answerMapper.answerListToAnswerResponseDto(answers);
        return new ResponseEntity(responses, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity postAnswer(@RequestBody @Valid AnswerPostDto post,
                                     @PathVariable("question-id") @Positive long questionId) {
        Answer postAnswer = answerMapper.answerPostDtoToAnswer(post);
        List<Answer> answerList = answerService.createAnswer(postAnswer, questionId);
        return new ResponseEntity(answerMapper.answerListToAnswerResponseDto(answerList), HttpStatus.CREATED);
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("answer-id") @Positive long answerId,
                                      @PathVariable("question-id") @Positive long questionId,
                                      @RequestBody @Valid AnswerPatchDto patch) {
        Answer answer = answerMapper.answerPatchDtoToAnswer(patch);
        answer.setAnswerId(answerId);
        List<Answer> answerList = answerService.updateAnswer(answer, questionId);
        return new ResponseEntity(answerMapper.answerListToAnswerResponseDto(answerList), HttpStatus.OK);
    }

    @DeleteMapping("{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") @Positive long answerId,
                                       @PathVariable("question-id") @Positive long questionId) {
        List<Answer> answerList = answerService.deleteAnswer(questionId, answerId);
        return new ResponseEntity<>(answerMapper.answerListToAnswerResponseDto(answerList), HttpStatus.OK);
    }
}
