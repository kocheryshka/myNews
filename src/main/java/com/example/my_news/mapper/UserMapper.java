package com.example.my_news.mapper;

import com.example.my_news.model.User;
import com.example.my_news.web.model.list.UserListResponse;
import com.example.my_news.web.model.single.UserResponse;
import com.example.my_news.web.model.upsert.UpsertUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserResponse userToResponse(User user);

    User requestToUser(UpsertUserRequest request);

    default UserListResponse userListToUserListResponse(List<User> users){
        UserListResponse userListResponse = new UserListResponse();
        userListResponse.setUsers(users.stream()
                .map(this::userToResponse)
                .toList());

        return userListResponse;
    }

}
