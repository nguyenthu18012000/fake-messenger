package com.main.fakeMessenger.repository;

import com.main.fakeMessenger.pojo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String userName);
    Optional<User> findByPhone(String userName);
    Optional<User> findById(Integer id);
}
