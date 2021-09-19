package com.exam.repos.auth;

import com.exam.models.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {

    public User findByUsername(String username);
}
