package com.example.my_news.service;

import com.example.my_news.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    Page<User> findAll(Pageable pageable);

    User findById(UUID id);

    User findByUsername(String username);

    User save(User user);

    User update(UUID id, User user);

    void deleteById(UUID id);

}
