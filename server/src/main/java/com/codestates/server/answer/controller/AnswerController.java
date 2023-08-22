package com.codestates.server.answer.controller;


import com.codestates.server.answer.dto.AnswerPatchDto;
import com.codestates.server.answer.dto.AnswerPostDto;
import com.codestates.server.answer.dto.AnswerResponseDto;
import com.codestates.server.answer.entity.Answer;
import com.codestates.server.answer.mapper.AnswerMapper;
import com.codestates.server.answer.service.AnswerService;
import com.codestates.server.exception.BusinessLogicException;
import com.codestates.server.question.uri.UriCreator;
import com.codestates.server.response.ErrorResponse;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/questions/{question-id}/answers")
@RequiredArgsConstructor
@Slf4j
public class AnswerController {
    private final AnswerService answerService;

    private final AnswerMapper mapper;

    @PostMapping
    public ResponseEntity<?> createAnswer(@Valid @RequestBody AnswerPostDto answerPostDto, @PathVariable("question-id") Long questionId) {
        try {
            log.info(" questionId = {}", questionId);

            Answer createdAnswer = answerService.createAnswer(
                    mapper.answerPostDtoToAnswer(answerPostDto), questionId, answerPostDto.getUserId());
            URI location = UriCreator.createUri("/questions/" + questionId, createdAnswer.getAnswerId());
            return ResponseEntity.created(location).build();

        } catch (BusinessLogicException e) {
            return ResponseEntity.status(e.getExceptionCode().getStatus())
                    .body(new ErrorResponse(e.getExceptionCode().getStatus(), e.getExceptionCode().getMessage()));
        }
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity<?> updateAnswer(
            @PathVariable("answer-id") @Positive long answerId,
            @PathVariable("question-id") @Positive long questionId,
            @RequestBody @Valid AnswerPatchDto answerPatchDto) {
        try {
            answerPatchDto.setAnswerId(answerId);
            Long userId = answerPatchDto.getUserId();
            Answer updatedAnswer = answerService.updateAnswer(
                    mapper.answerPatchDtoToAnswer(answerPatchDto), questionId, userId);

            return ResponseEntity.accepted().build();
        } catch (BusinessLogicException e) {
            return ResponseEntity.status(e.getExceptionCode().getStatus())
                    .body(new ErrorResponse(e.getExceptionCode().getStatus(), e.getExceptionCode().getMessage()));
        }
    }


    @DeleteMapping("/{answer-id}")
    public ResponseEntity<?> deleteAnswer(
            @PathVariable("answer-id") @Positive Long answerId,
            @PathVariable("question-id") @Positive Long questionId,
            @RequestBody Map<String, Long> data) {
        try {
            Long userId = data.get("userId");
            answerService.deleteAnswer(questionId, answerId, userId);

            return ResponseEntity.noContent().build();
        } catch (BusinessLogicException e) {
            return ResponseEntity.status(e.getExceptionCode().getStatus())
                    .body(new ErrorResponse(e.getExceptionCode().getStatus(), e.getExceptionCode().getMessage()));
        }
    }
}