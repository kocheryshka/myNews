package com.example.my_news.web.model.upsert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertUserRequest {

    private String username;

    private String password;

    private String email;

    private String phone;
}
