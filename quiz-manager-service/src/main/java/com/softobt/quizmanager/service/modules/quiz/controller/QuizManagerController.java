package com.softobt.quizmanager.service.modules.quiz.controller;

import com.softobt.asgardian.control.apimodels.LoggedInUser;
import com.softobt.core.exceptions.models.RestControllerException;
import com.softobt.quizmanager.lib.modules.quiz.apimodels.CreateQuizRequest;
import com.softobt.quizmanager.lib.modules.quiz.apimodels.QuizFetchResponse;
import com.softobt.quizmanager.lib.modules.quiz.models.Quiz;
import com.softobt.quizmanager.lib.modules.quiz.service.QuizService;
import com.softobt.quizmanager.service.modules.quiz.apimodels.GetQuizRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author aobeitor
 * @since 6/20/20
 */
@RestController
@RequestMapping("/api/v1/quiz/mgr/quiz-manager")
public class QuizManagerController {
    @Autowired
    QuizService quizService;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    @ResponseBody
    public List<Quiz> getAllQuiz(@RequestAttribute("user")LoggedInUser loggedInUser)throws RestControllerException{
        try{
            return quizService.getAllQuiz(loggedInUser);
        }
        catch (Exception e){
            throw new RestControllerException(e);
        }
    }

    @RequestMapping(value = "/get-available", method = RequestMethod.GET)
    @ResponseBody
    public List<Quiz> getAvailableQuiz()throws RestControllerException{
        try{
            return quizService.getAllOpenQuiz();
        }
        catch (Exception e){
            throw new RestControllerException(e);
        }
    }

    @RequestMapping(value = "/view/{quizId}", method = RequestMethod.GET)
    @ResponseBody
    public QuizFetchResponse viewQuiz(@PathVariable("quizId")Long quizId, @RequestAttribute("user")LoggedInUser loggedInUser)throws RestControllerException{
        try{
            return quizService.viewQuiz(quizId,loggedInUser);
        }
        catch (Exception e){
            throw new RestControllerException(e);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public QuizFetchResponse getQuiz(@RequestBody @Validated GetQuizRequest request)throws RestControllerException{
        try{
            return quizService.getQuiz(request.getQuizId(),request.getPasscode());
        }
        catch (Exception e){
            throw new RestControllerException(e);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Boolean createQuiz(@RequestBody @Validated CreateQuizRequest request, @RequestAttribute("user") LoggedInUser loggedInUser)throws RestControllerException{
        try{
            return quizService.createQuiz(loggedInUser,request);
        }
        catch (Exception e){
            throw new RestControllerException(e);
        }
    }
}
