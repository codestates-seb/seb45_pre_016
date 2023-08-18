package com.codestates.server.answer.repository;

import com.codestates.server.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query(value = "SELECT * FROM answer WHERE question_id = :questionId", nativeQuery = true)
    List<Answer> findByQuestionId(long questionId);

    @Query(value = "SELECT * FROM answer WHERE user_id = :userId", nativeQuery = true)
    List<Answer> findAllByUserId(long userId);
}
