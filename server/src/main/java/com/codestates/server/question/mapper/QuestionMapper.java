package com.codestates.server.question.mapper;

import com.codestates.server.question.dto.QuestionPatchDto;
import com.codestates.server.question.dto.QuestionPostDto;
import com.codestates.server.question.dto.QuestionResponseDto;
import com.codestates.server.question.entity.Question;
import com.codestates.server.question.entity.QuestionTag;
import com.codestates.server.tag.entity.Tag;
import com.codestates.server.user.entity.User;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    default Question questionPostDtoToQuestion(QuestionPostDto questionPostDto){
        if ( questionPostDto == null ) {
            return null;
        }
        Question question = new Question();
        User user = new User();
/**
 * Question - tag 연관에서 필요한데... tag 부분 건들면 안될것같아서 일단 작성하다가 멈추고 주석처리 해놓습니다 !
 */
        List<String> tagNames = questionPostDto.getTagNames();
//        List<Tag> tags = new ArrayList<>();
//
//        for (String tagName : tagNames) {
//            Tag tag = new Tag();
//            tag.setTagName(tagName);
//            tags.add(tag);
//        }

        for (String tagName : tagNames) {
            QuestionTag questionTag = new QuestionTag();
            questionTag.setQuestion(question);
        }

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
