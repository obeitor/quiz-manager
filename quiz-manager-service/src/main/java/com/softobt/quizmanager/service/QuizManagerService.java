package com.softobt.quizmanager.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @author aobeitor
 * @since 6/16/20
 */
@SpringBootApplication(scanBasePackages = "com.softobt",
        exclude = { SecurityAutoConfiguration.class })
public class QuizManagerService {

    public static void main(String... args){
        SpringApplication.run(QuizManagerService.class,args);
    }
}
