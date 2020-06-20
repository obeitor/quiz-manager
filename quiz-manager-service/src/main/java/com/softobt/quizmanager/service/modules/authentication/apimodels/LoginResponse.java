package com.softobt.quizmanager.service.modules.authentication.apimodels;

import com.softobt.asgardian.control.models.Authority;
import com.softobt.core.api.TokenDetail;
import java.util.*;

/**
 * @author aobeitor
 * @since 6/16/20
 */
public class LoginResponse {
    private TokenDetail tokenDetail;
    private String firstName;
    private String lastName;
    private List<Authority> authorities;

    public TokenDetail getTokenDetail() {
        return tokenDetail;
    }

    public void setTokenDetail(TokenDetail tokenDetail) {
        this.tokenDetail = tokenDetail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}
