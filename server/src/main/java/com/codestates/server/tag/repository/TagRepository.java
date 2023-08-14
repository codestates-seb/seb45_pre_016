package com.codestates.server.tag.repository;

import com.codestates.server.answer.entity.Answer;
import com.codestates.server.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
//    List<Tag> findAllByName(String name);
    @Query("SELECT t FROM Tag t WHERE t.tagId IN (SELECT qt.tag.tagId FROM QuestionTag qt WHERE qt.question.questionId = :questionId)")
    List<Tag> findAllByQuestionId(Long questionId);

}
