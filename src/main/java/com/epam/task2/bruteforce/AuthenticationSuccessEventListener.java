package com.epam.task2.bruteforce;

import com.epam.task2.auth.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private LoggingAttemptService loggingAttemptService;

    @Override
    public void onApplicationEvent(final AuthenticationSuccessEvent e) {
        UsernamePasswordAuthenticationToken source = (UsernamePasswordAuthenticationToken) e.getSource();
        UserPrincipal principal = (UserPrincipal) source.getPrincipal();

        log.info("auth success for user = {}", principal.getUsername());

        loggingAttemptService.authSuccess(principal.getUsername());
    }
}