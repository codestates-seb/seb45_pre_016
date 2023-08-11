package com.codestates.server.answer.mapper;

import com.codestates.server.answer.dto.AnswerPatchDto;
import com.codestates.server.answer.dto.AnswerPostDto;
import com.codestates.server.answer.dto.AnswerResponseDto;
import com.codestates.server.answer.entity.Answer;
import com.codestates.server.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface AnswerMapper {
    // AnswerPostDto -> Answer
    default Answer answerPostDtoToAnswer(AnswerPostDto answerPostDto, User user, Question question){
        Answer answer = new Answer();
        answer.setUser(user);
        answer.setContent(answerPostDto.getContent());
        answer.setQuestion(question);

        return answer;
    }


    // AnswerPatchDto -> Answer
    default Answer answerPatchDtoToAnswer(AnswerPatchDto requestBody, User user, Question question){
        Answer answer = new Answer(question, requestBody.getContent(), user);
        answer.setAnswerId(requestBody.getAnswerId());
        return answer;
    }


    // Answer -> AnswerResponseDto
    default AnswerResponseDto answerToAnswerResponseDto(Answer answer) {
        AnswerResponseDto answerResponseDto = new AnswerResponseDto();
        answerResponseDto.setQuestionId(answer.getQuestion().getQuestionId());
        answerResponseDto.setAnswerId(answer.getAnswerId());
        answerResponseDto.setContent(answer.getContent());
        answerResponseDto.setCreatedAt(answer.getCreatedAt());
        answerResponseDto.setModifiedAt(answer.getModifiedAt());
        answerResponseDto.setUserId(answer.getUser().getUserId());

        return answerResponseDto;
    }

    List<AnswerResponseDto> answersToAnswerResponseDto(List<Answer> answers);
}
