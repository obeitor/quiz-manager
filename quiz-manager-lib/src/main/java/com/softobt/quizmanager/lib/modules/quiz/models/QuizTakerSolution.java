package com.softobt.quizmanager.lib.modules.quiz.models;

import javax.persistence.*;

/**
 * @author aobeitor
 * @since 6/10/20
 */
@Entity
@Table(name = "quiz_taker_soln")
public class QuizTakerSolution {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_taker_id", referencedColumnName = "id")
    private QuizTaker quizTaker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

    private String textAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "choice_id", referencedColumnName = "id")
    private QuestionChoice choice;

    private boolean isCorrect;

    private int markGiven;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuizTaker getQuizTaker() {
        return quizTaker;
    }

    public void setQuizTaker(QuizTaker quizTaker) {
        this.quizTaker = quizTaker;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getTextAnswer() {
        return textAnswer;
    }

    public void setTextAnswer(String textAnswer) {
        this.textAnswer = textAnswer;
    }

    public QuestionChoice getChoice() {
        return choice;
    }

    public void setChoice(QuestionChoice choice) {
        this.choice = choice;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public int getMarkGiven() {
        return markGiven;
    }

    public void setMarkGiven(int markGiven) {
        this.markGiven = markGiven;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
