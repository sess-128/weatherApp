package com.rrtyui.weatherapplication.service;

import com.rrtyui.weatherapplication.entity.Session;
import com.rrtyui.weatherapplication.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }
    
    public List<Session> getAllEntities() {
        return sessionRepository.findAll();
    }

    public Optional<Session> getEntityById(UUID uuid) {
        return sessionRepository.findById(uuid);
    }

    public Session saveEntity(Session entity) {
        return sessionRepository.save(entity);
    }

    public void deleteEntity(UUID uuid) {
        sessionRepository.deleteById(uuid);
    }
}
