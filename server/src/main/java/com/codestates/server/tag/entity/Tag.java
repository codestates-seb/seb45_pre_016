package com.codestates.server.tag.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Tag {

    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tagId;

    @Column(name = "tag_name", nullable = false, unique = true)
    private String tagName;

    // ⏹️ Question 매핑 예정
    // private List<Question> questions = new ArrayList<>();
}
