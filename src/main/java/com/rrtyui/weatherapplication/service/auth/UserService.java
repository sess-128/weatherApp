package com.rrtyui.weatherapplication.service.auth;

import com.rrtyui.weatherapplication.dao.UserDao;
import com.rrtyui.weatherapplication.entity.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User add(User user) {

        String hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hash);

        userDao.save(user);

        return user;
    }

    public User findByLogin(User user) {
        return userDao.findByLogin(user).orElseThrow();
    }

    

}
