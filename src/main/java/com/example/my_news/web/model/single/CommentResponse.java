package com.example.my_news.web.model.single;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    private UUID id;

    private String username;

    private String text;

    private Instant createAt;

    private Instant updateAt;

}
