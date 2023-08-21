package com.codestates.server.question.entity;

import com.codestates.server.answer.entity.Answer;
import com.codestates.server.audit.TimeStamp;
import com.codestates.server.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class Question extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "question_title", nullable = false)
    private String title;

    @Column(name = "question_content", nullable = false)
    private String content;

    @Column(name = "question_views")
    private Long views;

    @JsonIgnore //순환참조 발생하여 stackoverflow 에러남 > JsonIgnore 사용해서 없애준다
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    @JsonIgnore //순환참조 발생하여 stackoverflow 에러남 > JsonIgnore 사용해서 없애준다
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<QuestionTag> questionTags = new ArrayList<>();

    //태그쪽 연관 확인후에 변경 필요. 태그쪽도 매핑해야하는데 이거 한명이 해야할것같아요...하하
    public void addQuestionTags(List<QuestionTag> questionTags) {
        for (QuestionTag questionTag : questionTags) {
            questionTag.setQuestion(this);
            this.questionTags.add(questionTag);
        }
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
