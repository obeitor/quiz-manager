package com.softobt.quizmanager.service.modules.quiz.apimodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author aobeitor
 * @since 6/20/20
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetQuizRequest {

    @NotNull(message = "Quiz Code must be entered")
    @NotEmpty(message = "Quiz Code must be entered")
    private String quizId;
    private String passcode;

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }
}
