package com.softobt.quizmanager.lib.modules.quiz.repository;

import com.softobt.quizmanager.lib.modules.quiz.models.Quiz;
import com.softobt.quizmanager.lib.modules.quiz.models.QuizTaker;
import com.softobt.quizmanager.lib.modules.quiz.models.QuizTakerSolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author aobeitor
 * @since 6/16/20
 */
@Repository
public interface TakerSolutionRepository extends JpaRepository<QuizTakerSolution,Long> {

    List<QuizTakerSolution> findAllByQuizTakerAndQuestion_Quiz(QuizTaker quizTaker, Quiz quiz);
}
