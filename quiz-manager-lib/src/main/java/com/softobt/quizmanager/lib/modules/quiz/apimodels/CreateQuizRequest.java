package com.softobt.quizmanager.lib.modules.quiz.apimodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.softobt.quizmanager.lib.modules.quiz.enums.QuizStatus;
import com.softobt.quizmanager.lib.modules.quiz.models.Question;
import com.softobt.quizmanager.lib.modules.quiz.models.Quiz;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @author aobeitor
 * @since 6/20/20
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateQuizRequest {
    @NotNull(message = "Please enter a title for quiz")
    @NotEmpty(message = "Please enter a title for quiz")
    private String title;
    private String description;

    @NotNull(message = "Please enter a start date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date startDate;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    private Boolean timed = false;
    private Integer duration = 0;
    private boolean autoscore = true;
    private boolean showScore = false;
    private QuizStatus status = QuizStatus.ACTIVE;
    private boolean protectedQuiz = false;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getTimed() {
        return timed;
    }

    public void setTimed(Boolean timed) {
        this.timed = timed;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public boolean isAutoscore() {
        return autoscore;
    }

    public void setAutoscore(boolean autoscore) {
        this.autoscore = autoscore;
    }

    public boolean isShowScore() {
        return showScore;
    }

    public void setShowScore(boolean showScore) {
        this.showScore = showScore;
    }

    public QuizStatus getStatus() {
        return status;
    }

    public void setStatus(QuizStatus status) {
        this.status = status;
    }

    public boolean isProtectedQuiz() {
        return protectedQuiz;
    }

    public void setProtectedQuiz(boolean protectedQuiz) {
        this.protectedQuiz = protectedQuiz;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    private List<Question> questions;

    public Quiz getQuizFromRequest(){
        Quiz quiz = new Quiz();
        quiz.setDateAdded(LocalDateTime.now());
        quiz.setTimed(this.timed);
        quiz.setStatus(this.status);
        quiz.setStartDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(this.startDate.getTime()), ZoneId.systemDefault()));
        if(this.endDate!=null){
            quiz.setEndDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(this.endDate.getTime()), ZoneId.systemDefault()));
        }
        quiz.setAutoScore(this.autoscore);
        quiz.setDescription(this.description);
        quiz.setDuration(this.duration);
        quiz.setQuizCode(UUID.randomUUID().toString().substring(0,6));
        quiz.setShowScore(this.showScore);
        quiz.setProtectedQuiz(this.protectedQuiz);
        if(this.protectedQuiz){
            quiz.setPassword(UUID.randomUUID().toString().substring(0,6));
        }
        quiz.setTitle(this.title);
        return quiz;
    }
}
