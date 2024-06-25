package com.example.my_news.web.model.single;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OneNewsResponse {

    private UUID id;

    private String categoryShortDesc;

    private String username;

    private String title;

    private String text;

    private List<CommentResponse> comments = new ArrayList<>();

    private Instant createAt;

    private Instant updateAt;

}
