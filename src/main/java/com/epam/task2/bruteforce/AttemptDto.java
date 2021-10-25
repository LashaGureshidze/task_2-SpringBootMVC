package com.epam.task2.bruteforce;

import java.time.LocalDateTime;

public class AttemptDto {

    private LocalDateTime lastAttempt;
    private long attemptCount;

    public AttemptDto() {
        this.lastAttempt = LocalDateTime.now();
        this.attemptCount = 0L;
    }

    public long increaseAttemptCount() {
        return ++attemptCount;
    }

    public void resetAttemptCount() {
        attemptCount = 0L;
    }

    public LocalDateTime getLastAttempt() {
        return lastAttempt;
    }
    public void setLastAttempt(LocalDateTime lastAttempt) {
        this.lastAttempt = lastAttempt;
    }
}
