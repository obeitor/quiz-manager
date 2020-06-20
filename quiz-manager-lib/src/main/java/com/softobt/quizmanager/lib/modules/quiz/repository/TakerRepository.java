package com.softobt.quizmanager.lib.modules.quiz.repository;

import com.softobt.quizmanager.lib.modules.quiz.models.Quiz;
import com.softobt.quizmanager.lib.modules.quiz.models.QuizTaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author aobeitor
 * @since 6/16/20
 */
@Repository
public interface TakerRepository extends JpaRepository<QuizTaker,Long>{
    List<QuizTaker> findAllByQuizAndCompleted(Quiz quiz, boolean completed);
    List<QuizTaker> findAllByQuiz(Quiz quiz);
}
