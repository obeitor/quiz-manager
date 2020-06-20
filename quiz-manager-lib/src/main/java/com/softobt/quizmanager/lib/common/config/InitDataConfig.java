package com.softobt.quizmanager.lib.common.config;

import com.softobt.asgardian.control.models.Authority;
import com.softobt.asgardian.control.models.Domain;
import com.softobt.core.logger.services.LoggerService;
import com.softobt.quizmanager.lib.common.models.QuizAdmin;
import com.softobt.quizmanager.lib.common.repository.AdminRepository;
import com.softobt.quizmanager.lib.common.repository.AuthorityRepository;
import com.softobt.quizmanager.lib.common.repository.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author aobeitor
 * @since 6/16/20
 */
@Configuration
public class InitDataConfig {
    @Value("${quiz.manager.domain.name:QUIZ-SERVICE}")
    private String defaultDomainName;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    DomainRepository domainRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    AdminRepository adminRepository;

    @Bean
    void initAdminUser(){
        createDefaultAuthority();
        createSuperUser(createDefaultDomain());
    }

    void createDefaultAuthority(){
        if(!authorityRepository.findFirstByName("odin").isPresent()){
            authorityRepository.save(new Authority("odin","Super Admin Right - all quiz",0,false));
        }
        if(!authorityRepository.findFirstByName("thor").isPresent()){
            authorityRepository.save(new Authority("thor","Admin Right - all quiz",1,false));
        }
    }

    Domain createDefaultDomain(){
        Optional<Domain> defaultDomain = domainRepository.findFirstByName(defaultDomainName);
        if(!defaultDomain.isPresent()){
            Domain d = domainRepository.save(new Domain(defaultDomainName));
            LoggerService.info(this.getClass(),"Created default service domain ~ "+defaultDomainName);
            return d;
        }
        return defaultDomain.get();
    }

    void createSuperUser(Domain domain){
        Optional<QuizAdmin> optional = adminRepository.findByUsernameAndDomain_Name("superodin",defaultDomainName);
        QuizAdmin superUser;
        if(!optional.isPresent()) {
            superUser = new QuizAdmin();
            superUser.setUsername("superodin");
            superUser.setPassword(passwordEncoder.encode("Q#!zS3rv!c3"));
            superUser.setFirstname("Super");
            superUser.setLastname("Admin");
            superUser.setActive(true);
            superUser.setDateCreated(LocalDateTime.now());
            superUser.setPasswordLastUpdate(LocalDateTime.now());
            superUser.setDomain(domain);
            superUser =adminRepository.save(superUser);
            LoggerService.info(this.getClass(), "Created" + superUser.getFirstname() + " " + superUser.getLastname());
        }
        else{
            superUser = optional.get();
        }
        if(superUser.getAuthorities().size()==0){
            superUser.getAuthorities().add(authorityRepository.findFirstByName("odin").get());
            adminRepository.save(superUser);
            LoggerService.info(this.getClass(), "Setup" + superUser.getFirstname() + " " + superUser.getLastname()+" authority");
        }
    }
}
