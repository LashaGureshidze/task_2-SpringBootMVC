package com.epam.task2.bruteforce;

import com.epam.task2.domain.BlockedUser;
import com.epam.task2.repository.BlockedUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.epam.task2.bruteforce.LoggingAttemptService.CLEANUP_PERIOD_MINUTES;

@Service
@Slf4j
public class BlockedUserCleaner {

    private final BlockedUserRepository blockedUserRepository;
    private final LoggingAttemptService loggingAttemptService;

    public BlockedUserCleaner(BlockedUserRepository blockedUserRepository, LoggingAttemptService loggingAttemptService) {
        this.blockedUserRepository = blockedUserRepository;
        this.loggingAttemptService = loggingAttemptService;
    }

    @Scheduled(fixedRate = CLEANUP_PERIOD_MINUTES * 60 * 1000)
    public void cleanBlockedUsers() {
        log.info("executing BlockedUsers clean up");

        LocalDateTime beforeTime = LocalDateTime.now().minusMinutes(CLEANUP_PERIOD_MINUTES);

        List<BlockedUser> blockedUsers = blockedUserRepository.findBlockedUsersByBlockedTime(beforeTime);
        blockedUsers.forEach(blockedUser -> {
            loggingAttemptService.cleatAttempts(blockedUser.getUsername());
            blockedUserRepository.delete(blockedUser);
        });
    }
}
