package com.softobt.quizmanager.service.modules.authentication.service;

import com.softobt.core.api.TokenDetail;
import com.softobt.core.exceptions.models.CredentialException;
import com.softobt.core.exceptions.models.RestServiceException;
import com.softobt.quizmanager.lib.common.models.QuizAdmin;
import com.softobt.quizmanager.lib.modules.admin.service.AdminService;
import com.softobt.quizmanager.service.modules.authentication.apimodels.LoginRequest;
import com.softobt.quizmanager.service.modules.authentication.apimodels.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author aobeitor
 * @since 6/16/20
 */
@Service
public class LoginService {
    @Autowired
    AdminService adminService;

    public LoginResponse loginAdmin(LoginRequest request)throws CredentialException, RestServiceException{
        LoginResponse response = new LoginResponse();
        TokenDetail tokenDetail = adminService.getToken(request.getUsername(),request.getPassword());
        QuizAdmin admin = adminService.getAdmin(request.getUsername());
        response.setAuthorities(new ArrayList<>());
        response.getAuthorities().addAll(admin.getAuthorities());
        response.setFirstName(admin.getFirstname());
        response.setLastName(admin.getLastname());
        response.setTokenDetail(tokenDetail);
        return response;
    }
}
