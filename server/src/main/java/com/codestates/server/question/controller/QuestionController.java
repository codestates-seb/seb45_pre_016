package com.codestates.server.question.controller;

import com.codestates.server.answer.dto.AnswerResponseDto;
import com.codestates.server.answer.entity.Answer;
import com.codestates.server.answer.mapper.AnswerMapper;
import com.codestates.server.answer.service.AnswerService;
import com.codestates.server.question.dto.QuestionPatchDto;
import com.codestates.server.question.dto.QuestionPostDto;
import com.codestates.server.question.dto.QuestionResponseDto;
import com.codestates.server.question.dto.Response;
import com.codestates.server.question.entity.Question;
import com.codestates.server.question.mapper.QuestionMapper;
import com.codestates.server.question.service.QuestionService;
import com.codestates.server.question.uri.UriCreator;
import com.codestates.server.tag.entity.Tag;
import com.codestates.server.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@Slf4j
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final TagService tagService;
    private final QuestionMapper mapper;
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;

    /**
     * user 등록
     * @param questionPostDto
     * @return
     */
    @PostMapping("/ask")
    public ResponseEntity postQuestion(@RequestBody QuestionPostDto questionPostDto){

        Long userId = questionPostDto.getUserId();
        Question question = questionService.createQuestion(mapper.questionPostDtoToQuestion(questionPostDto), userId);

        URI location = UriCreator.createUri("/questions", question.getQuestionId());

        return ResponseEntity.created(location).build();
    }

    /**
     * user 수정
     * @param questionsId
     * @param questionPatchDto
     * @return
     */
    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion(@PathVariable("question-id") Long questionsId,
                                @RequestBody QuestionPatchDto questionPatchDto) {
        questionPatchDto.setQuestionId(questionsId); //question id set
        Long userId = questionPatchDto.getUserId();

        Question question = mapper.questionPatchDtoToQuestion(questionPatchDto);
        Question updatedQuestion = questionService.updateQuestion(question, userId);
        URI location = UriCreator.createUri("/questions", question.getQuestionId());
        return ResponseEntity.created(location).build();
    }

    /**
     * user 검색
     * @param questionsId
     * @return
     */
    @GetMapping("/{question-id}")
    public ResponseEntity<QuestionResponseDto> getQuestion(@PathVariable("question-id") Long questionsId){
        Question question = questionService.findQuestion(questionsId);
        List<Tag> tags = tagService.getTags(questionsId);
        List<Answer> answers = answerService.findByQuestionId(questionsId);

        List<AnswerResponseDto> answersList = answers.stream().map(answerMapper::answerToAnswerResponseDto).collect(Collectors.toList());

        QuestionResponseDto questionResponseDto = mapper.questionToQuestionResponseDto(question,tags,answersList);
        return new ResponseEntity<>(questionResponseDto,HttpStatus.OK);
    }

    /**
     * 전체 검색
     * { username : 정해영
     *   title : 제목,
     *   modifiedAt : 날짜,
     *   {tag , tag , tag , tag , tag}
     *   } -> 에러 떠서 고쳐야함...!
     //ResponseEntity<List<QuestionResponseDto>> <- 이걸로 바꿔야함
     */
    @GetMapping
    public ResponseEntity<List<Response>> getQuestions(){

        List<Question> allQuestions = questionService.findAllQuestions();
        List<Response> allResponse = new ArrayList<>();
        for(Question question : allQuestions){
            Long questionId = question.getQuestionId();
            List<Tag> tags = tagService.getTags(questionId);
            List<Answer> answers = answerService.findByQuestionId(questionId);
            List<AnswerResponseDto> answersList = answers.stream().map(answerMapper::answerToAnswerResponseDto).collect(Collectors.toList());
            QuestionResponseDto questionResponseDto = mapper.questionToQuestionResponseDto(question,tags,answersList);
            Response<QuestionResponseDto> response = new Response<>(question.getUser().getUserId(), questionResponseDto);
            allResponse.add(response);
        }
//        List<Response> questionResponseDtoList = allQuestions.stream().map(q->mapper.questionToQuestionResponseDto(q,)).collect(Collectors.toList());

        return new ResponseEntity<>(allResponse, HttpStatus.OK);
    }

    /**
     * 유저 삭제
     * @param questionId
     * @return
     */
    @DeleteMapping("/delete/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") Long questionId,
                                         @RequestBody Map<String, Long> data){
        Long userId = data.get("userId");
        questionService.deleteQuestion(questionId, userId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
