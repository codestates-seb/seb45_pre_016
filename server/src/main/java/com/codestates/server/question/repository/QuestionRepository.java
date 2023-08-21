package com.codestates.server.question.repository;

import com.codestates.server.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {

    @Query(value = "SELECT * FROM question WHERE user_id = :userId", nativeQuery = true)
    List<Question> findAllByUserId(long userId);
}
