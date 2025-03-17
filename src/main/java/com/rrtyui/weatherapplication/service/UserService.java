package com.rrtyui.weatherapplication.service;

import com.rrtyui.weatherapplication.entity.User;
import com.rrtyui.weatherapplication.repository.UserTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserTestRepository userTestRepository;

    @Autowired
    public UserService(UserTestRepository userTestRepository) {
        this.userTestRepository = userTestRepository;
    }

    public List<User> getAllEntities() {
        return userTestRepository.findAll();
    }

    public Optional<User> getEntityById(Long id) {
        return userTestRepository.findById(id);
    }

    public User saveEntity(User entity) {
        return userTestRepository.save(entity);
    }

    public void deleteEntity(Long id) {
        userTestRepository.deleteById(id);
    }

    public Optional<User> getEntityByIdName(User entity) {
        return userTestRepository.findByName(entity.getLogin());
    }
}
