package com.softobt.quizmanager.lib.modules.quiz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softobt.quizmanager.lib.modules.quiz.enums.QuestionType;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author aobeitor
 * @since 6/10/20
 */
@Entity
@Table(name = "question")
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id",referencedColumnName = "id")
    @JsonIgnore
    private Quiz quiz;
    private int position;
    @NotNull(message = "Please enter question")
    @NotEmpty(message = "Please enter question")
    private String questionText;
    private boolean hasImage = false;
    private String imageLocation;
    @Enumerated(EnumType.STRING)
    private QuestionType questionType = QuestionType.OPTIONS;
    private int markAttached = 1;
    private boolean hasScore = true;
    private String textAns = "";
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<QuestionChoice> questionChoices = new ArrayList<>();

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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public boolean isHasImage() {
        return hasImage;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public int getMarkAttached() {
        return markAttached;
    }

    public void setMarkAttached(int markAttached) {
        this.markAttached = markAttached;
    }

    public boolean isHasScore() {
        return hasScore;
    }

    public void setHasScore(boolean hasScore) {
        this.hasScore = hasScore;
    }

    public String getTextAns() {
        return textAns;
    }

    public void setTextAns(String textAns) {
        this.textAns = textAns;
    }

    public List<QuestionChoice> getQuestionChoices() {
        return questionChoices;
    }

    public void setQuestionChoices(List<QuestionChoice> questionChoices) {
        this.questionChoices = questionChoices;
    }
}
