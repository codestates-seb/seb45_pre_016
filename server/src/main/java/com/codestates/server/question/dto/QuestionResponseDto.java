package com.codestates.server.question.dto;


import com.codestates.server.answer.dto.AnswerResponseDto;
import com.codestates.server.answer.entity.Answer;
import com.codestates.server.question.entity.QuestionTag;
import com.codestates.server.tag.entity.Tag;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.LifecycleState;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class QuestionResponseDto {

    private Long questionId;

    private String title;

    private String content;

    private List<AnswerResponseDto> answers;

    private Long views;

    private List<Tag> tags;

    private LocalDateTime modified_At;

}
