package com.softobt.quizmanager.lib.modules.admin.service;

import com.softobt.asgardian.control.config.JWTokenUtil;
import com.softobt.asgardian.control.models.AsgardianUser;
import com.softobt.asgardian.control.repositories.AsgardianUserRepository;
import com.softobt.asgardian.control.service.AsgardianUserValidationService;
import com.softobt.core.api.TokenDetail;
import com.softobt.core.exceptions.models.CredentialException;
import com.softobt.core.exceptions.models.RestServiceException;
import com.softobt.quizmanager.lib.common.models.QuizAdmin;
import com.softobt.quizmanager.lib.common.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author aobeitor
 * @since 6/16/20
 */
@Service
public class AdminService extends AsgardianUserValidationService {

    @Value("${quiz.manager.domain.name:QUIZ-SERVICE}")
    private String defaultDomainName;

    @Override
    public TokenDetail getToken(AsgardianUser user) {
        return tokenUtil.getToken(user);
    }

    public TokenDetail getToken(String username, String password)throws CredentialException{
        TokenDetail td = getToken(validateUserCredentials(username,password,defaultDomainName));
        getAdminRepository().updateLastLogin(LocalDateTime.now(),username,defaultDomainName);
        return td;
    }

    public QuizAdmin getAdmin(String username)throws RestServiceException{
        Optional<QuizAdmin> admin = getAdminRepository().findByUsernameAndDomain_Name(username,defaultDomainName);
        if(admin.isPresent()&&admin.get().isActive()){
            return admin.get();
        }
        throw new RestServiceException("Admin not found or is Inactive");
    }

    @Override
    public AsgardianUser changePassword(String username, String authenticationDomain, String newPassword) throws RestServiceException {
        return null;
    }

    @Override
    public AsgardianUser saveNewUser(AsgardianUser user) throws RestServiceException {
        return null;
    }

    @Autowired
    @Override
    public void setTokenUtil(JWTokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
    }

    @Autowired
    @Override
    public void setUserRepository(AsgardianUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    protected AdminRepository getAdminRepository(){
        return (AdminRepository)this.userRepository;
    }

    @Autowired
    @Override
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
