package com.rrtyui.weatherapplication.repository;

import com.rrtyui.weatherapplication.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private List<User> users;

    {
        users = new ArrayList<>();
    }

    public void add(User user) {
        users.add(user);
        //TODO: в сервисе по идее делаем куки и сессию, чтобы работало дальше
    }
}
