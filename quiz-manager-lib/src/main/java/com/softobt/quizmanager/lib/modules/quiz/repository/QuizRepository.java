package com.softobt.quizmanager.lib.modules.quiz.repository;

import com.softobt.quizmanager.lib.modules.quiz.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author aobeitor
 * @since 6/14/20
 */
@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {
    Optional<Quiz> findFirstByQuizCode(String quizCode);


    List<Quiz> findAllByProtectedQuizFalse();
}
