package com.softobt.quizmanager.lib.common.models;

import com.softobt.asgardian.control.models.AsgardianUser;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author aobeitor
 * @since 6/9/20
 */
@Entity
@Table(name = "quiz_admin")
public class QuizAdmin extends AsgardianUser {
    private String firstname;
    private String lastname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
