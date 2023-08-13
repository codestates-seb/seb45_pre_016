//package com.codestates.server.answer.controller;
//
//
//import com.codestates.server.answer.dto.AnswerPatchDto;
//import com.codestates.server.answer.dto.AnswerPostDto;
//import com.codestates.server.answer.dto.AnswerResponseDto;
//import com.codestates.server.answer.entity.Answer;
//import com.codestates.server.answer.mapper.AnswerMapper;
//import com.codestates.server.answer.repository.AnswerRepository;
//import com.codestates.server.answer.service.AnswerService;
//import com.codestates.server.user.entity.User;
//import com.codestates.server.user.repository.UserRepository;
//import com.sun.istack.NotNull;
//import lombok.RequiredArgsConstructor;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import javax.validation.constraints.Positive;
//import java.util.List;
//
//// 임시. 자세한 내용은 수정 예정.
//@Validated
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/questions/{question-id}/answers")
//public class AnswerController {
//    private final AnswerService answerService;
//    private final AnswerMapper mapper;
//
//    @PostMapping
//    public ResponseEntity postAnswer(@Valid @RequestBody AnswerPostDto answerPostDto) {
//        User user = UserRepository.findById(answerPostDto.getUserId())
//                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
//        Answer newAnswer = answerService.createAnswer(
//                mapper.answerPostDtoToAnswer(answerPostDto, user, null));
//
//        return new ResponseEntity<>(mapper.answerToAnswerResponseDto(newAnswer), HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{answer_id}")
//    public ResponseEntity getAnswer(@PathVariable("answer_id") @Positive long answerId) {
//        Answer answer = AnswerRepository.findById(answerId)
//                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
//
//        return new ResponseEntity<>(mapper.answerToAnswerResponseDto(answer), HttpStatus.OK);
//    }
//
//
//    @PatchMapping("/{answer_id}")
//    public ResponseEntity patchAnswer(@PathVariable("answer_id") @Positive @NotNull long answerId,
//                                      @Valid @RequestBody AnswerPatchDto requestBody) {
//        Answer findAnswer = AnswerRepository.findById(answerId)
//                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
//        Answer updatedAnswer = answerService.updateAnswer(findAnswer);
//
//        return new ResponseEntity<>(mapper.answerToAnswerResponseDto(updatedAnswer), HttpStatus.OK);
//    }
//
//
//    @DeleteMapping("/{answer_id}")
//    public ResponseEntity deleteAnswer(@PathVariable("answer_id") @Positive long answerId) {
//        Answer findAnswer = AnswerRepository.findById(answerId)
//                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
//        answerService.deleteAnswer(answerId);
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//}
