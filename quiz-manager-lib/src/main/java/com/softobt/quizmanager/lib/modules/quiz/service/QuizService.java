package com.softobt.quizmanager.lib.modules.quiz.service;

import com.softobt.asgardian.control.apimodels.LoggedInUser;
import com.softobt.core.exceptions.models.CredentialException;
import com.softobt.core.exceptions.models.RestServiceException;
import com.softobt.quizmanager.lib.common.service.QuizAuthorizationService;
import com.softobt.quizmanager.lib.modules.quiz.apimodels.CreateQuizRequest;
import com.softobt.quizmanager.lib.modules.quiz.apimodels.QuizFetchResponse;
import com.softobt.quizmanager.lib.modules.quiz.enums.QuizStatus;
import com.softobt.quizmanager.lib.modules.quiz.models.Question;
import com.softobt.quizmanager.lib.modules.quiz.models.Quiz;
import com.softobt.quizmanager.lib.modules.quiz.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author aobeitor
 * @since 6/20/20
 */
@Service
public class QuizService {
    @Autowired
    QuizRepository quizRepository;

    @Autowired
    TakerRepository takerRepository;

    @Autowired
    TakerSolutionRepository solutionRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ChoiceRepository choiceRepository;

    @Autowired
    QuizAuthorizationService authorizationService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<Quiz> getAllOpenQuiz()throws RestServiceException{
        return quizRepository.findAllByProtectedQuizFalse();
    }

    public List<Quiz> getAllQuiz(LoggedInUser loggedInUser)throws RestServiceException,CredentialException{
        authorizationService.preAuthorize(loggedInUser,"thor");
        return quizRepository.findAll();
    }

    public QuizFetchResponse viewQuiz(Long quizId, LoggedInUser loggedInUser)throws RestServiceException, CredentialException{
        authorizationService.preAuthorize(loggedInUser,"thor");
        Optional<Quiz> optional = quizRepository.findById(quizId);
        if(!optional.isPresent())
            throw new RestServiceException("Quiz does not exist");
        Quiz quiz = optional.get();
        QuizFetchResponse response = new QuizFetchResponse();
        response.setQuizInfo(quiz);
        response.setQuizQuestions(questionRepository.findAllByQuizOrderByPositionAsc(quiz));
        return response;
    }

    public QuizFetchResponse getQuiz(String uniqueId, String passcode)throws RestServiceException, CredentialException{
        Optional<Quiz> optional = quizRepository.findFirstByQuizCode(uniqueId);
        if(!optional.isPresent())
            throw new RestServiceException("Quiz does not exist");
        Quiz quiz = optional.get();
        if(quiz.getEndDate()!=null && LocalDateTime.now().isAfter(quiz.getEndDate())){
            quiz.setStatus(QuizStatus.EXPIRED);
            quiz = quizRepository.save(quiz);
        }
        if(!QuizStatus.ACTIVE.equals(quiz.getStatus())){
            throw new RestServiceException(quiz.getStatus().getDesc());
        }

        if(quiz.isProtectedQuiz() && !quiz.getPassword().equals(passcode)){
            throw new RestServiceException("Passcode for quiz is invalid");
        }
        QuizFetchResponse response = new QuizFetchResponse();
        response.setQuizInfo(quiz);
        response.setQuizQuestions(questionRepository.findAllByQuizOrderByPositionAsc(quiz));
        return response;
    }

    public Boolean createQuiz(LoggedInUser loggedInUser, CreateQuizRequest request)throws CredentialException,RestServiceException{
        Quiz quiz = request.getQuizFromRequest();
        quiz = quizRepository.save(quiz);
        for(Question question : request.getQuestions()){
            question.setQuiz(quiz);
            questionRepository.save(question);
        }
        return true;
    }


}
