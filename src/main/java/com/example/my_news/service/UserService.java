package com.example.my_news.service;

import com.example.my_news.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<User> findAll();

    User findById(UUID id);

    User findByUsername(String username);

    User save(User user);

    User update(UUID id, User user);

    void deleteById(UUID id);

}
