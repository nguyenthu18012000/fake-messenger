package com.main.fakeMessenger.repository;

import com.main.fakeMessenger.pojo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
