package com.epam.task2.auth;

import com.epam.task2.domain.Permission;
import com.epam.task2.domain.User;
import com.epam.task2.repository.BlockedUserRepository;
import com.epam.task2.repository.PermissionRepository;
import com.epam.task2.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final BlockedUserRepository blockedUserRepository;

    public CustomUserDetailsService(UserRepository userRepository, PermissionRepository permissionRepository, BlockedUserRepository blockedUserRepository) {
        super();
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
        this.blockedUserRepository = blockedUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //check if blocked
        if (blockedUserRepository.findByUsername(username).isPresent()) {
            throw new UsernameNotFoundException("username: " + username + " is blocked because of many unsuccessful attempts");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("cannot find username: " + username));

        Set<Permission> permissions = permissionRepository.findByUserId(user.getUserId());

        return new UserPrincipal(user, permissions);
    }
}