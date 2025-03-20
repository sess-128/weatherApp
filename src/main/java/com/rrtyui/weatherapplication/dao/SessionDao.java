package com.rrtyui.weatherapplication.dao;

import com.rrtyui.weatherapplication.entity.Session;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class SessionDao extends BaseDao<Session>{

    protected SessionDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Transactional
    public Optional<Session> findById (String id) {
        org.hibernate.Session session = sessionFactory.getCurrentSession();

        return Optional.ofNullable(session.find(Session.class, UUID.fromString(id)));
    }

    @Transactional
    public List<Session> findAll () {
        org.hibernate.Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select s from Session s", Session.class).getResultList();
    }
}
