package com.rrtyui.weatherapplication.service;

import com.rrtyui.weatherapplication.dao.SessionDao;
import com.rrtyui.weatherapplication.entity.CustomSession;
import com.rrtyui.weatherapplication.entity.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class SessionService {
    private final SessionDao sessionDao;

    @Autowired
    public SessionService(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    public CustomSession add(User user) {
        CustomSession customSession = CustomSession.builder()
                .id(UUID.randomUUID())
                .user(user)
                .expiresAt(LocalDateTime.now().plusHours(1))
                .build();

        return sessionDao.save(customSession);
    }

    public CustomSession findByUUID(String uuid) {

        return sessionDao.findById(uuid).orElseThrow(IllegalArgumentException::new);
    }

    public boolean isSessionValid(String sessionId) {
        Optional<CustomSession> session = sessionDao.findById(sessionId);

        if (session.isEmpty()) {
            return false;
        }
        CustomSession customSession1 = session.get();
        LocalDateTime expiresAt = customSession1.getExpiresAt();

        if (isValidTimeEnded(expiresAt)) {
            sessionDao.delete(customSession1);
            return false;
        }
        return true;
    }

    public void deleteOldSessions() {
        List<CustomSession> customSessions = sessionDao.findAll();
        for (CustomSession customSession : customSessions) {
            if (isValidTimeEnded(customSession.getExpiresAt())) {
                sessionDao.delete(customSession);
            }
        }
    }

    public Optional<String> findSessionIdInCookies(HttpServletRequest httpServletRequest) {
        String sessionId = "";

        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if ("session_id".equals(cookie.getName())) {
                sessionId = cookie.getValue();
            }
        }
        return Optional.ofNullable(sessionId);
    }

    private boolean isValidTimeEnded (LocalDateTime timeToCheck) {
        return LocalDateTime.now().isAfter(timeToCheck);
    }
}
