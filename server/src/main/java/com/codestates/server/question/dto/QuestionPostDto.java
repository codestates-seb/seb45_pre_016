package com.codestates.server.question.dto;

import com.codestates.server.question.entity.QuestionTag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
public class QuestionPostDto {

    @NotBlank
    @Positive
    private Long userId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private int views;

    private List<String> tagNames;

}
