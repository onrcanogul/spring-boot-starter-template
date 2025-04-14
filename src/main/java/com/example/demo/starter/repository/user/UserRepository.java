package com.example.demo.starter.repository.user;

import com.example.demo.starter.entity.user.User;
import com.example.demo.starter.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByUsername(String username);
}
