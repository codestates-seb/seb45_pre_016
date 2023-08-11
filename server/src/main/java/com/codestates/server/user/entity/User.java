package com.codestates.server.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;    // Long으로 쓸 지? long으로 쓸 지?

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String password;

    private String image;

    @Column(name = "가입일")
    private LocalDateTime createdAt = LocalDateTime.now();

    // ⏹️ 추후 추가 예정 (매핑 필요)
    // totalUserQuestions

    // ⏹️ 추후 추가 예정 (매핑 필요)
    // totalUserAnswers

}
