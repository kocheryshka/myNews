package com.example.my_news.web.model.upsert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertCommentRequest {

    private UUID newsId;

    private String text;

}
