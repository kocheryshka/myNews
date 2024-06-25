package com.example.my_news.web.controller;

import com.example.my_news.mapper.UserMapper;
import com.example.my_news.model.RoleType;
import com.example.my_news.model.User;
import com.example.my_news.service.UserService;
import com.example.my_news.web.model.list.UserListResponse;
import com.example.my_news.web.model.single.UserResponse;
import com.example.my_news.web.model.upsert.UpsertUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<UserListResponse> findAll(){
        return ResponseEntity.ok(userMapper.userListToUserListResponse(userService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable UUID id){
        return ResponseEntity.ok(userMapper.userToResponse(userService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody UpsertUserRequest request, @RequestParam RoleType role){
        User user = userMapper.requestToUser(request);
        user.setRoles(Collections.singleton(role));

        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.userToResponse(userService.save(user)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable UUID id, @RequestBody UpsertUserRequest request){

        return ResponseEntity.ok(userMapper.userToResponse(userService.update(id, userMapper.requestToUser(request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        userService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
