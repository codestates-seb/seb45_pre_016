package com.codestates.server.question.mapper;

import com.codestates.server.question.dto.QuestionPatchDto;
import com.codestates.server.question.dto.QuestionPostDto;
import com.codestates.server.question.dto.QuestionResponseDto;
import com.codestates.server.question.entity.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    Question questionPostDtoToQuestion(QuestionPostDto questionPostDto);

    Question questionPatchDtoToQuestion(QuestionPatchDto questionPatchDto);

    QuestionResponseDto questionToQuestionResponseDto(Question question);
}
