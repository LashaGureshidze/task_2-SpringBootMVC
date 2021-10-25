package com.epam.task2.bruteforce;

import com.epam.task2.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class LoggingAttemptService {

    private final static int MAX_ATTEMPT = 3;
    public final static int CLEANUP_PERIOD_MINUTES = 5;
    private final ConcurrentMap<String, AttemptDto> attemptsCache;

    private final UserService userService;

    public LoggingAttemptService(UserService userService) {
        attemptsCache = new ConcurrentHashMap<>();
        this.userService = userService;
    }

    /**
     * increases attempt count for user
     * @param username
     */
    public void authFail(String username) {
        LocalDateTime now = LocalDateTime.now();
        AttemptDto attemptDto;
        if (attemptsCache.containsKey(username)) {
            attemptDto = attemptsCache.get(username);
            if (now.minusMinutes(CLEANUP_PERIOD_MINUTES).isAfter(attemptDto.getLastAttempt())) {
                //old value should reset
                attemptDto.resetAttemptCount();
            }
        } else {
            attemptDto = new AttemptDto();
        }

        //add attempt
        attemptDto.setLastAttempt(now);
        if (attemptDto.increaseAttemptCount() > MAX_ATTEMPT) {
            userService.blockUser(username);
        }

        attemptsCache.putIfAbsent(username, attemptDto);
    }

    /**
     * clears attempts for user
     * @param username
     */
    public void authSuccess(String username) {
        cleatAttempts(username);
    }

    /**
     * removes attempts from cache
     * @param username
     */
    public void cleatAttempts(String username) {
        attemptsCache.remove(username);
    }
}
