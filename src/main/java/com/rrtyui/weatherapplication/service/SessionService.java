package com.rrtyui.weatherapplication.service;

import com.rrtyui.weatherapplication.dao.SessionDao;
import com.rrtyui.weatherapplication.entity.Session;
import com.rrtyui.weatherapplication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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

    public Session add(User user) {
        Session session = Session.builder()
                .id(UUID.randomUUID())
                .user(user)
                .expiresAt(LocalDateTime.now().plusHours(1))
                .build();

        return sessionDao.save(session);
    }

    public Session findByUUID(String uuid) {

        return sessionDao.findById(uuid).orElseThrow(IllegalArgumentException::new);
    }

    public boolean isSessionValid(String sessionId) {
        Optional<Session> session = sessionDao.findById(sessionId);

        if (session.isEmpty()) {
            return false;
        }
        Session session1 = session.get();
        LocalDateTime expiresAt = session1.getExpiresAt();

        if (isValidTimeEnded(expiresAt)) {
            sessionDao.delete(session1);
            return false;
        }
        return true;
    }

    public void deleteOldSessions() {
        List<Session> sessions = sessionDao.findAll();
        for (Session session : sessions) {
            if (isValidTimeEnded(session.getExpiresAt())) {
                sessionDao.delete(session);
            }
        }
    }

    private boolean isValidTimeEnded (LocalDateTime timeToCheck) {
        return LocalDateTime.now().isAfter(timeToCheck);
    }
}
