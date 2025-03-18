package com.rrtyui.weatherapplication.service;

import com.rrtyui.weatherapplication.dao.SessionDao;
import com.rrtyui.weatherapplication.entity.Session;
import com.rrtyui.weatherapplication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        boolean isExpired = LocalDateTime.now().isAfter(expiresAt);

        if (isExpired) {
            sessionDao.delete(session1);
            return false;
        }
        return true;
    }
}
