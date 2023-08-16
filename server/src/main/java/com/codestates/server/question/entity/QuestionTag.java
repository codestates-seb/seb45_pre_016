package com.codestates.server.question.entity;

import com.codestates.server.tag.entity.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class QuestionTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionTagId;

    @ManyToOne
    @JoinColumn(name = "question_Id")
    @JsonIgnore //순환참조 발생하여 stackoverflow 에러남 > JsonIgnore 사용해서 없애준다
    private Question question;


//    @ManyToOne
//    @JoinColumn(name = "tag_id")
//    private Tag tag;
}
