package com.codestates.server.question.mapper;

import com.codestates.server.question.dto.QuestionPatchDto;
import com.codestates.server.question.dto.QuestionPostDto;
import com.codestates.server.question.dto.QuestionResponseDto;
import com.codestates.server.question.entity.Question;
import com.codestates.server.user.entity.User;
import org.mapstruct.Mapper;

import java.lang.reflect.Member;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    default Question questionPostDtoToQuestion(QuestionPostDto questionPostDto){
        if ( questionPostDto == null ) {
            return null;
        }
        Question question = new Question();
        User user = new User();
        user.setUserId(questionPostDto.getUserId());

        question.setUser(user);
        question.setTitle( questionPostDto.getTitle() );
        question.setContent( questionPostDto.getContent() );
        question.setViews( (long) questionPostDto.getViews() );

        return question;
    }

    Question questionPatchDtoToQuestion(QuestionPatchDto questionPatchDto);

    QuestionResponseDto questionToQuestionResponseDto(Question question);
}
