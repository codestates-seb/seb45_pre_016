package com.codestates.server.answer.repository;

import com.codestates.server.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query(value = "SELECT * FROM Answer WHERE question_id = :questionId", nativeQuery = true)
    List<Answer> findByQuestionId(@Param("questionId") long questionId);

    @Query(value = "SELECT * FROM Answer WHERE user_id = :userId", nativeQuery = true)
    List<Answer> findAllByUserId(@Param("userId") long userId);
}
