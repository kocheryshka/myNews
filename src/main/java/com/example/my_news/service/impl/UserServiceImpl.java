package com.example.my_news.service.impl;

import com.example.my_news.exception.EntityNotFoundException;
import com.example.my_news.exception.AlreadyExistsException;
import com.example.my_news.model.User;
import com.example.my_news.repository.UserRepository;
import com.example.my_news.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("User id={0} not found!", id)));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("User username={0} not found", username)));
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User update(UUID id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("User id={0} not found", id)));
        if (user.getUsername() != null && !user.getUsername().equals(existingUser.getUsername())) {
            if (userRepository.existsByUsername(user.getUsername())) {
                throw new AlreadyExistsException(
                        MessageFormat.format("Username {0} already exists!", user.getUsername()));
            } else {
                existingUser.setUsername(user.getUsername());
            }
        }
        if (user.getEmail() != null && !user.getEmail().equals(existingUser.getEmail())) {
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new AlreadyExistsException(
                        MessageFormat.format("Email {0} already exists!", user.getEmail()));
            } else {
                existingUser.setEmail(user.getEmail());
            }
        }
        if (user.getPhone() != null && !user.getPhone().equals(existingUser.getPhone())) {
            if (userRepository.existsByEmail(user.getPhone())) {
                throw new AlreadyExistsException(
                        MessageFormat.format("Phone {0} already exists!", user.getPhone()));
            } else {
                existingUser.setPhone(user.getPhone());
            }
        }

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteById(UUID id) {

    }
}
