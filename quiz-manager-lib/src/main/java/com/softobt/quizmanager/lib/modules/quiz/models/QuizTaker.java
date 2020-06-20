package com.softobt.quizmanager.lib.modules.quiz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.softobt.jpa.helpers.converters.DateTimeConverter;
import com.softobt.quizmanager.lib.common.models.QuizAdmin;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author aobeitor
 * @since 6/10/20
 */
@Entity
@Table(name = "quiz_taker")
public class QuizTaker {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private Quiz quiz;

    @Convert(converter = DateTimeConverter.class)
    private LocalDateTime launchedAt;

    @Convert(converter = DateTimeConverter.class)
    private LocalDateTime submittedAt;

    @Convert(converter = DateTimeConverter.class)
    private LocalDateTime markedAt;

    private boolean completed;

    private String fullName;

    private boolean isMarked;

    private int score;
    private String remark;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "marked_by", referencedColumnName = "id")
    @JsonIgnore
    private QuizAdmin markedBy;

    @JsonProperty("markedBy")
    public String getMarkedByName(){
        return markedBy == null ? "NA" : markedBy.getFirstname()+" "+markedBy.getLastname();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public LocalDateTime getLaunchedAt() {
        return launchedAt;
    }

    public void setLaunchedAt(LocalDateTime launchedAt) {
        this.launchedAt = launchedAt;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public LocalDateTime getMarkedAt() {
        return markedAt;
    }

    public void setMarkedAt(LocalDateTime markedAt) {
        this.markedAt = markedAt;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public QuizAdmin getMarkedBy() {
        return markedBy;
    }

    public void setMarkedBy(QuizAdmin markedBy) {
        this.markedBy = markedBy;
    }
}
