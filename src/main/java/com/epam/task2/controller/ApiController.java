package com.epam.task2.controller;

import com.epam.task2.domain.BlockedUser;
import com.epam.task2.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api")
public class ApiController {

    private final UserService userService;

    public ApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("about")
    public ResponseEntity<String> getAbout() {
        return ResponseEntity.ok("This is demo Spring Boot MVC application");
    }

    @PreAuthorize("hasRole('VIEW_INFO')")
    @GetMapping("info")
    public ResponseEntity<String> getInfo() {
        return ResponseEntity.ok("info protected resource");
    }

    @PreAuthorize("hasRole('VIEW_ADMIN')")
    @GetMapping("admin")
    public ResponseEntity<String> getAdmin() {
        return ResponseEntity.ok("admin protected resource");
    }

    @PreAuthorize("hasRole('VIEW_ADMIN')")
    @GetMapping("users/blocked")
    public ResponseEntity<List<BlockedUser>> getBlockedUsers() {
        List<BlockedUser> blockedUsers = userService.getBlockedUsers();
        return ResponseEntity.ok(blockedUsers);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public void handleForbidden(HttpServletResponse response) throws IOException {
        response.sendRedirect("/logout");
    }
}
