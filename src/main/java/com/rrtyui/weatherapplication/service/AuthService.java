package com.rrtyui.weatherapplication.service;

import com.rrtyui.weatherapplication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final SessionService sessionService;
    private final UserService userService;

    @Autowired
    public AuthService(SessionService sessionService, UserService userService) {
        this.sessionService = sessionService;
        this.userService = userService;
    }

    public Optional<User> addUser(User user) {
        return userService.getEntityByIdName(user);
    }
}
