package com.rrtyui.weatherapplication.repository;

import com.rrtyui.weatherapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTestRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}
