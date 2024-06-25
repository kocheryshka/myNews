package com.example.my_news.web.model.list;

import com.example.my_news.web.model.single.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListResponse {

    private List<UserResponse> users = new ArrayList<>();

}
