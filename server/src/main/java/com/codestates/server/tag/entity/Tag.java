package com.codestates.server.tag.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Tag {

    @Id
    @Column(name = "tag_id")
    private long tagId;

    @Column(name = "tag_name", nullable = false, unique = true)
    private String tagName;

    // ⏹️ Question 매핑 예정
    // private List<Question> questions = new ArrayList<>();
}
