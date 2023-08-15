package com.codestates.server.answer.mapper;

import com.codestates.server.answer.dto.AnswerPatchDto;
import com.codestates.server.answer.dto.AnswerPostDto;
import com.codestates.server.answer.dto.AnswerResponseDto;
import com.codestates.server.answer.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer answerPatchDtoToAnswer(AnswerPatchDto answerPatchDto);

    Answer answerPostDtoToAnswer(AnswerPostDto answerPostDto);

    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "question.questionId", target = "questionId")
    @Mapping(source = "user.name", target = "name")
    AnswerResponseDto answerToAnswerResponseDto(Answer answer);

    List<AnswerResponseDto> answerListToAnswerResponseDto(List<Answer> answer);
}
