package com.example.my_news.web.model.list;

import com.example.my_news.web.model.single.NewsResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsListResponse {

    private List<NewsResponse> newsList = new ArrayList<>();

}
