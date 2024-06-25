package com.example.my_news.web.model.single;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsResponse {

    private UUID id;

    private String categoryShortDesc;

    private String username;

    private String title;

    private String text;

    private Integer commentsCount;

    private Instant createAt;

    private Instant updateAt;

}
