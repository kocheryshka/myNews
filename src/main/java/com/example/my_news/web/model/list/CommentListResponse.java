package com.example.my_news.web.model.list;

import com.example.my_news.web.model.single.CommentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentListResponse {

    private List<CommentResponse> comments = new ArrayList<>();

}
