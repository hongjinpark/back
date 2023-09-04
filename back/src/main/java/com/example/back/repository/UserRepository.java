package com.example.back.repository;

import com.example.back.entity.User;
import com.example.back.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    Long deleteByEmail(String email);
}
