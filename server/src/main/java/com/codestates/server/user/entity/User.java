package com.codestates.server.user.entity;

import com.codestates.server.answer.entity.Answer;
import com.codestates.server.question.entity.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
    요구사항 :
    name - 사용자명
    email - 사용자 이메일
    password - 사용자 비밀번호
    image - 사용자 프로필 이미지
 */

@NoArgsConstructor
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;    // Long으로 쓸 지? long으로 쓸 지?

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String password;

    private String image;

    @CreatedDate
    private LocalDateTime created_at;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Question> questions = new ArrayList<>();

//     questionInfo에서 가지고 올 data
    public List<UserQuestionInfo> getUserQuestionInfo() {
        List<UserQuestionInfo> userQuestionInfos = new ArrayList<>();

        for(Question question : questions) {
            UserQuestionInfo userQuestionInfo = new UserQuestionInfo();

            userQuestionInfo.setQuestionId(question.getQuestionId());
            userQuestionInfo.setTitle(question.getTitle());
//            userQuestionInfo.setContent(question.getContent());
            userQuestionInfo.setCreated_At(question.getCreated_At());

            userQuestionInfos.add(userQuestionInfo);
        }
        return userQuestionInfos;
    }


    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Answer> answers = new ArrayList<>();

    public List<UserAnswerInfo> getUserAnswerInfo() {
        List<UserAnswerInfo> userAnswerInfos = new ArrayList<>();

        for(Answer answer : answers) {
            UserAnswerInfo userAnswerInfo = new UserAnswerInfo();

            userAnswerInfo.setAnswerId(answer.getAnswerId());
            userAnswerInfo.setContent(answer.getContent());
            userAnswerInfo.setCreatedAt(answer.getCreatedAt());

            userAnswerInfos.add(userAnswerInfo);
        }
        return userAnswerInfos;
    }

    /**
     * ElementCollection 을 사용하면 테이블을 생성하지 않고 컬렉션 관리가 가능
     *
     * 회원이 가지는 역할을 나타내느 정보를 담고 있는 문자열 리스트
     * FetchType.EAGER : 옵션을 사용해서 즉시 로딩되도록 설정
     * FetchType.LAZY : 지연 로딩
     */
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    public void setQuestion(Question question) {
        questions.add(question);
        if (question.getUser() != this) {
            question.setUser(this);
        }
    }

    public void setAnswer(Answer answer) {
        answers.add(answer);
        if(answer.getUser() != this) {
            answer.setUser(this);
        }
    }

}
