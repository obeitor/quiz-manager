package com.softobt.quizmanager.lib.modules.quiz.enums;

/**
 * @author aobeitor
 * @since 6/10/20
 */
public enum QuizStatus {
    ACTIVE("Quiz is Active"),
    EXPIRED("Sorry, Quiz has expired"),
    INACTIVE("Quiz is currently inactive");

    private String desc;
    private QuizStatus(String desc){
        this.desc = desc;
    }

    public String getDesc(){
        return this.desc;
    }
}
