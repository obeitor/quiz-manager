package com.softobt.quizmanager.service.modules.authentication.controller;

import com.softobt.core.exceptions.models.RestControllerException;
import com.softobt.quizmanager.service.modules.authentication.apimodels.LoginRequest;
import com.softobt.quizmanager.service.modules.authentication.apimodels.LoginResponse;
import com.softobt.quizmanager.service.modules.authentication.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author aobeitor
 * @since 6/16/20
 */
@RestController
@RequestMapping("/api/v1/quiz/mgr/authentication")
public class AuthenticationController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public LoginResponse login(@RequestBody @Validated LoginRequest loginRequest)throws RestControllerException{
        try{
            return loginService.loginAdmin(loginRequest);
        }
        catch (Exception e){
            throw new RestControllerException(e);
        }
    }
}
