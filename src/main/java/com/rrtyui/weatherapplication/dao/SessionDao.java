package com.rrtyui.weatherapplication.dao;

import com.rrtyui.weatherapplication.entity.CustomSession;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class SessionDao extends BaseDao<CustomSession>{

    protected SessionDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Transactional
    public Optional<CustomSession> findById (String id) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.get(CustomSession.class, UUID.fromString(id)));
    }

    @Transactional
    public List<CustomSession> findAll () {
        org.hibernate.Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select s from CustomSession s", CustomSession.class).getResultList();
    }
}
