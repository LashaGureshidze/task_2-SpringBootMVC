package com.epam.task2.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BLOCKED_USERS")
@Data
public class BlockedUser {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "BLOCKED_USER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "BLOCKED_TIME")
    private LocalDateTime blockedTime;
}
