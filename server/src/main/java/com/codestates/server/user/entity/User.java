package com.codestates.server.user.entity;

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

    // ⏹️ 추후 추가 예정 (매핑 필요)
    // private List<Question> questionList = new ArrayList<>();

    // ⏹️ 추후 추가 예정 (매핑 필요)
    // private List<Answer> answerList = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

}
