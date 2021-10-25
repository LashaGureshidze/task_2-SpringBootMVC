package com.epam.task2.repository;

import com.epam.task2.domain.BlockedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BlockedUserRepository extends JpaRepository<BlockedUser, Long> {

    Optional<BlockedUser> findByUsername(String username);

    @Query("select u from BlockedUser u " +
            "where u.blockedTime < :beforeTime")
    List<BlockedUser> findBlockedUsersByBlockedTime(@Param("beforeTime") LocalDateTime beforeTime);
}
