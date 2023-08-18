package com.codestates.server.answer.controller;


import com.codestates.server.answer.dto.AnswerPatchDto;
import com.codestates.server.answer.dto.AnswerPostDto;
import com.codestates.server.answer.dto.AnswerResponseDto;
import com.codestates.server.answer.entity.Answer;
import com.codestates.server.answer.mapper.AnswerMapper;
import com.codestates.server.answer.service.AnswerService;
import com.codestates.server.question.entity.Question;
import com.codestates.server.question.service.QuestionService;
import com.codestates.server.question.uri.UriCreator;
import com.codestates.server.user.entity.User;
import com.codestates.server.user.service.UserService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/questions/{question-id}/answers")
@RequiredArgsConstructor
@Slf4j
public class AnswerController {
    private final AnswerService answerService;

    private final AnswerMapper mapper;

    @PostMapping
    public ResponseEntity createAnswer(
            @RequestBody @Valid AnswerPostDto answerPostDto,
            @PathVariable("question-id") Long questionId) {

        log.info(" questionId = {}", questionId);

        Answer createdAnswer = answerService.createAnswer(
                mapper.answerPostDtoToAnswer(answerPostDto), questionId, answerPostDto.getUserId());

        URI location = UriCreator.createUri("/questions/"+questionId, createdAnswer.getAnswerId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity<AnswerResponseDto> updateAnswer(
            @PathVariable("answer-id") @Positive long answerId,
            @PathVariable("question-id") @Positive long questionId,
            @RequestBody @Valid AnswerPatchDto answerPatchDto) {
        answerPatchDto.setAnswerId(answerId);
        Answer updatedAnswer = answerService.updateAnswer(
                mapper.answerPatchDtoToAnswer(answerPatchDto), questionId);
        AnswerResponseDto response = mapper.answerToAnswerResponseDto(updatedAnswer);

        return ResponseEntity.ok(response);
    }

//    @GetMapping
//    public ResponseEntity<List<AnswerResponseDto>> getAnswers(
//            @PathVariable("question-id") @Positive long questionId) {
//        List<Answer> answers = answerService.getAnswers(questionId);
//        List<AnswerResponseDto> response = mapper.answersListToAnswerResponseDto(answers);
//
//        return ResponseEntity.ok(response);
//    }

    @DeleteMapping("/{answer-id}")
    public ResponseEntity<Void> deleteAnswer(
            @PathVariable("answer-id") @Positive long answerId,
            @PathVariable("question-id") @Positive long questionId) {
        answerService.deleteAnswer(questionId, answerId);

        return ResponseEntity.noContent().build();
    }
}