package com.codestates.server.user.mapper;

import com.codestates.server.answer.entity.Answer;
import com.codestates.server.question.entity.Question;
import com.codestates.server.user.dto.UserPatchDto;
import com.codestates.server.user.dto.UserPostDto;
import com.codestates.server.user.dto.UserResponseDto;
import com.codestates.server.user.entity.User;
import com.codestates.server.user.entity.UserAnswerInfo;
import com.codestates.server.user.entity.UserQuestionInfo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-21T10:33:47+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.18 (Azul Systems, Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User userPostDtoToUser(UserPostDto userPostDto) {
        if ( userPostDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserName( userPostDto.getUserName() );
        user.setEmail( userPostDto.getEmail() );
        user.setPassword( userPostDto.getPassword() );

        return user;
    }

    @Override
    public User userPatchDtoToUser(UserPatchDto userPatchDto) {
        if ( userPatchDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( userPatchDto.getUserId() );
        user.setUserName( userPatchDto.getUserName() );

        return user;
    }

    @Override
    public UserResponseDto userToUserResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setUserId( user.getUserId() );
        userResponseDto.setUserName( user.getUserName() );
        userResponseDto.setImage("https://picsum.photos/200");
        userResponseDto.setQuestions( questionListToUserQuestionInfoList( user.getQuestions() ) );
        userResponseDto.setAnswers( answerListToUserAnswerInfoList( user.getAnswers() ) );

        return userResponseDto;
    }

    @Override
    public List<UserResponseDto> usersToUserResponseDto(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponseDto> list = new ArrayList<UserResponseDto>( users.size() );
        for ( User user : users ) {
            list.add( userToUserResponseDto( user ) );
        }

        return list;
    }

    protected UserQuestionInfo questionToUserQuestionInfo(Question question) {
        if ( question == null ) {
            return null;
        }

        UserQuestionInfo userQuestionInfo = new UserQuestionInfo();

        userQuestionInfo.setQuestionId( question.getQuestionId() );
        userQuestionInfo.setTitle( question.getTitle() );
        userQuestionInfo.setCreated_At( question.getCreated_At() );

        return userQuestionInfo;
    }

    protected List<UserQuestionInfo> questionListToUserQuestionInfoList(List<Question> list) {
        if ( list == null ) {
            return null;
        }

        List<UserQuestionInfo> list1 = new ArrayList<UserQuestionInfo>( list.size() );
        for ( Question question : list ) {
            list1.add( questionToUserQuestionInfo( question ) );
        }

        return list1;
    }

    protected UserAnswerInfo answerToUserAnswerInfo(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        UserAnswerInfo userAnswerInfo = new UserAnswerInfo();

        userAnswerInfo.setAnswerId( answer.getAnswerId() );
        userAnswerInfo.setContent( answer.getContent() );

        return userAnswerInfo;
    }

    protected List<UserAnswerInfo> answerListToUserAnswerInfoList(List<Answer> list) {
        if ( list == null ) {
            return null;
        }

        List<UserAnswerInfo> list1 = new ArrayList<UserAnswerInfo>( list.size() );
        for ( Answer answer : list ) {
            list1.add( answerToUserAnswerInfo( answer ) );
        }

        return list1;
    }
}
