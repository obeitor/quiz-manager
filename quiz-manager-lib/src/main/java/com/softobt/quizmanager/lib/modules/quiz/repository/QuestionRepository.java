package com.softobt.quizmanager.lib.modules.quiz.repository;

import com.softobt.quizmanager.lib.modules.quiz.models.Question;
import com.softobt.quizmanager.lib.modules.quiz.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author aobeitor
 * @since 6/14/20
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findAllByQuizOrderByPositionAsc(Quiz quiz);
}
