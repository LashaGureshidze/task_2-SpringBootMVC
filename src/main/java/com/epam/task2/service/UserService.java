package com.epam.task2.service;

import com.epam.task2.domain.BlockedUser;
import com.epam.task2.repository.BlockedUserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final BlockedUserRepository blockedUserRepository;

    public UserService(BlockedUserRepository blockedUserRepository) {
        this.blockedUserRepository = blockedUserRepository;
    }

    public void blockUser(String username) {
        Optional<BlockedUser> blockedUserOpt = blockedUserRepository.findByUsername(username);

        if (blockedUserOpt.isPresent()) return;

        //create BlockedUser
        BlockedUser blockedUser = new BlockedUser();
        blockedUser.setUsername(username);
        blockedUser.setBlockedTime(LocalDateTime.now());
        blockedUserRepository.save(blockedUser);
    }

    public List<BlockedUser> getBlockedUsers() {
        return blockedUserRepository.findAll();
    }
}
