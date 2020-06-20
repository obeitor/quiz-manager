package com.softobt.quizmanager.lib.modules.quiz.repository;

import com.softobt.quizmanager.lib.modules.quiz.models.Question;
import com.softobt.quizmanager.lib.modules.quiz.models.QuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

/**
 * @author aobeitor
 * @since 6/16/20
 */
@Repository
public interface ChoiceRepository  extends JpaRepository<QuestionChoice,Long>{
    List<QuestionChoice> findAllByQuestion(Question question);
}
