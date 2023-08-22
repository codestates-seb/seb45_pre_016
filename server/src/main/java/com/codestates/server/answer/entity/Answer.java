package com.codestates.server.answer.entity;

import com.codestates.server.audit.TimeStamp;
import com.codestates.server.question.entity.Question;
import com.codestates.server.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Answer extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long answerId;

    @Column(length = 10000, nullable = false)
    private String content;


    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}