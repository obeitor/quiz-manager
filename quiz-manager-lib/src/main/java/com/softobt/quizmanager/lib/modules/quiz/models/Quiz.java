package com.softobt.quizmanager.lib.modules.quiz.models;

import com.softobt.jpa.helpers.converters.DateTimeConverter;
import com.softobt.quizmanager.lib.modules.quiz.enums.QuizStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author aobeitor
 * @since 6/10/20
 */
@Entity
@Table(name = "quiz")
public class Quiz {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Convert(converter = DateTimeConverter.class)
    private LocalDateTime startDate;
    @Convert(converter = DateTimeConverter.class)
    private LocalDateTime endDate;

    @Convert(converter = DateTimeConverter.class)
    private LocalDateTime dateAdded;

    private boolean timed;

    private int duration;
    private boolean autoScore;
    private boolean showScore;
    @Enumerated(EnumType.STRING)
    private QuizStatus status;

    @Column(unique = true, length = 15, nullable = false)
    private String quizCode;
    private boolean protectedQuiz;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public boolean isTimed() {
        return timed;
    }

    public void setTimed(boolean timed) {
        this.timed = timed;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isAutoScore() {
        return autoScore;
    }

    public void setAutoScore(boolean autoScore) {
        this.autoScore = autoScore;
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

    public String getQuizCode() {
        return quizCode;
    }

    public void setQuizCode(String quizCode) {
        this.quizCode = quizCode;
    }

    public boolean isProtectedQuiz() {
        return protectedQuiz;
    }

    public void setProtectedQuiz(boolean protectedQuiz) {
        this.protectedQuiz = protectedQuiz;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
