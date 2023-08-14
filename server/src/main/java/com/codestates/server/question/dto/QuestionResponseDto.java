package com.codestates.server.question.dto;


import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.LifecycleState;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class QuestionResponseDto {

    private Long questionId;

    private String title;

    private String content;

    private Long views;

    //태그 필요

    private LocalDateTime modified_At;

}
