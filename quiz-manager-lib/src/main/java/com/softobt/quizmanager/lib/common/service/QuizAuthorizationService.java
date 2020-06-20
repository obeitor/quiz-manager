package com.softobt.quizmanager.lib.common.service;

import com.softobt.asgardian.control.apimodels.LoggedInUser;
import com.softobt.asgardian.control.models.Authority;
import com.softobt.asgardian.control.service.AuthorizationService;
import com.softobt.core.exceptions.models.CredentialException;
import com.softobt.quizmanager.lib.common.models.QuizAdmin;
import com.softobt.quizmanager.lib.common.repository.AdminRepository;
import com.softobt.quizmanager.lib.common.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author aobeitor
 * @since 6/16/20
 */
@Service
public class QuizAuthorizationService implements AuthorizationService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void preAuthorize(LoggedInUser user, String minimumAuthority) throws CredentialException {
        authorize(getUser(user),minimumAuthority);
    }

    private QuizAdmin getUser(LoggedInUser loggedInUser)throws CredentialException{
        Optional<QuizAdmin> optional = adminRepository.findByUsernameAndDomain_Name(loggedInUser.getUsername(),loggedInUser.getDomain());
        if(!optional.isPresent())
            throw new CredentialException(CredentialException.CredentialExceptionType.UNKNOWN_USER);
        return optional.get();
    }

    private void authorize(QuizAdmin user, String minAuthority)throws CredentialException{
        Optional<Authority> minimum = authorityRepository.findFirstByName(minAuthority);
        if(!minimum.isPresent())
            throw new CredentialException("User Pre-Validation Failed");
        for(Authority authority : user.getAuthorities()){
            if(authority.getLevel()<minimum.get().getLevel() || authority.getAuthority().equalsIgnoreCase(minimum.get().getAuthority()))
                return;
        }
        throw new CredentialException(CredentialException.CredentialExceptionType.NOT_AUTHORIZED);
    }
}
