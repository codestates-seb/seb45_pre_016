package com.codestates.server.question.mapper;

import com.codestates.server.answer.dto.AnswerResponseDto;
import com.codestates.server.answer.entity.Answer;
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
        List<Tag> tags = new ArrayList<>();
        List<QuestionTag> questionTags = new ArrayList<>();

        for (String tagName : tagNames) {
            Tag tag = new Tag(tagName); //태그name 넣은상태로 생성
            QuestionTag questionTag = new QuestionTag(); //questiontag 만들어서 question 넣어주고
            questionTag.setQuestion(question);
            questionTag.setTag(tag);
            questionTags.add(questionTag);
            tags.add(tag);
        }
        question.addQuestionTags(questionTags);

        user.setUserId(questionPostDto.getUserId());

        question.setUser(user);
        question.setTitle( questionPostDto.getTitle() );
        question.setContent( questionPostDto.getContent() );
        question.setViews( (long) questionPostDto.getViews() );

        return question;
    }

    Question questionPatchDtoToQuestion(QuestionPatchDto questionPatchDto);

    default QuestionResponseDto questionToQuestionResponseDto(Question question, List<Tag> tags, List<AnswerResponseDto> answers){
        if ( question == null ) {
            return null;
        }

        QuestionResponseDto questionResponseDto = new QuestionResponseDto();

        questionResponseDto.setQuestionId( question.getQuestionId() );
        questionResponseDto.setTitle( question.getTitle() );
        questionResponseDto.setContent( question.getContent() );
        questionResponseDto.setViews( question.getViews() );
        questionResponseDto.setModified_At( question.getModified_At() );

        questionResponseDto.setTags(tags);
        questionResponseDto.setAnswers(answers);

        return questionResponseDto;
    };
}
