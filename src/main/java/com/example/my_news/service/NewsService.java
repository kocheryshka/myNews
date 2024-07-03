package com.example.my_news.service;

import com.example.my_news.model.News;
import com.example.my_news.web.model.single.NewsFilterRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface NewsService {

    Page<News> filterBy(NewsFilterRequest filter);

    News findById(UUID id);

    News save(News news);

    News update(News news);

    void deleteById(UUID id);
}
