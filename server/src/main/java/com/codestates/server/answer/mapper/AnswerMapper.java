package com.codestates.server.answer.mapper;

import com.codestates.server.answer.dto.AnswerPatchDto;
import com.codestates.server.answer.dto.AnswerPostDto;
import com.codestates.server.answer.dto.AnswerResponseDto;
import com.codestates.server.answer.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer answerPatchDtoToAnswer(AnswerPatchDto answerPatchDto);

    default Answer answerPostDtoToAnswer(AnswerPostDto answerPostDto){
        if (answerPostDto == null) {
            return null;
        } else {
            Answer answer = new Answer();
            answer.setContent(answerPostDto.getContent());
            return answer;
        }
    }

    default AnswerResponseDto answerToAnswerResponseDto(Answer answer){
        if (answer == null) {
            return null;
        } else {
            AnswerResponseDto answerResponseDto = new AnswerResponseDto();
            answerResponseDto.setUserId(answer.getUser().getUserId());
            answerResponseDto.setAnswerId(answer.getAnswerId());
            answerResponseDto.setContent(answer.getContent());
            answerResponseDto.setModified_At(answer.getModifiedAt());
            return answerResponseDto;
        }
    }

    List<AnswerResponseDto> answersListToAnswerResponseDto(List<Answer> answer);
}
