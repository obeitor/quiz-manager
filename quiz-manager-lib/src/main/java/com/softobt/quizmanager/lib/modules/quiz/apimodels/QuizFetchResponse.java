package com.softobt.quizmanager.lib.modules.quiz.apimodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.softobt.quizmanager.lib.modules.quiz.models.Question;
import com.softobt.quizmanager.lib.modules.quiz.models.Quiz;
import java.util.*;

/**
 * @author aobeitor
 * @since 6/20/20
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuizFetchResponse {
    private Quiz quizInfo;
    private List<Question> quizQuestions;

    public Quiz getQuizInfo() {
        return quizInfo;
    }

    public void setQuizInfo(Quiz quizInfo) {
        this.quizInfo = quizInfo;
    }

    public List<Question> getQuizQuestions() {
        return quizQuestions;
    }

    public void setQuizQuestions(List<Question> quizQuestions) {
        this.quizQuestions = quizQuestions;
    }

}
