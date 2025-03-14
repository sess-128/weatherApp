package com.rrtyui.weatherapplication.service;

import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {
    public UUID addSession() {
        return UUID.randomUUID();
    }
}
