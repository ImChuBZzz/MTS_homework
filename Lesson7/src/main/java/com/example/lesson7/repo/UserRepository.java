package com.example.lesson7.repo;

import com.example.lesson7.data.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {}
