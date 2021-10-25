package com.epam.task2.bruteforce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private LoggingAttemptService loggingAttemptService;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        UsernamePasswordAuthenticationToken source = (UsernamePasswordAuthenticationToken) e.getSource();
        String username = (String) source.getPrincipal();

        log.info("auth fail for user = {}", username);

        loggingAttemptService.authFail(username);
    }
}

