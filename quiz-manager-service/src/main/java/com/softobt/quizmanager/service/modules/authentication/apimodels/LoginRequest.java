package com.softobt.quizmanager.service.modules.authentication.apimodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author aobeitor
 * @since 6/16/20
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest {
    @NotNull(message = "Username cannot be empty")
    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @NotNull(message = "Password cannot be empty")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
