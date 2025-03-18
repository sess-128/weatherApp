package com.rrtyui.weatherapplication.dao;

import com.rrtyui.weatherapplication.entity.User;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDao extends BaseDao<User> {

    protected UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Transactional
    public Optional<User> findByLogin(User user) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM User WHERE login = :login";
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("login", user.getLogin());
        return query.uniqueResultOptional();
    }
}
