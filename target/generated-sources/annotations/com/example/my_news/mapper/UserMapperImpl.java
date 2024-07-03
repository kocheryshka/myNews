package com.example.my_news.mapper;

import com.example.my_news.model.User;
import com.example.my_news.web.model.single.UserResponse;
import com.example.my_news.web.model.upsert.UpsertUserRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-02T14:32:58+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse userToResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setUsername( user.getUsername() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setPhone( user.getPhone() );
        userResponse.setCreateAt( user.getCreateAt() );
        userResponse.setUpdateAt( user.getUpdateAt() );

        return userResponse;
    }

    @Override
    public User requestToUser(UpsertUserRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( request.getUsername() );
        user.password( request.getPassword() );
        user.email( request.getEmail() );
        user.phone( request.getPhone() );

        return user.build();
    }
}
