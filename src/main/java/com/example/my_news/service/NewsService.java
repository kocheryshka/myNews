package com.example.my_news.service;

import com.example.my_news.model.News;

import java.util.List;
import java.util.UUID;

public interface NewsService {

    List<News> findAll();

    News findById(UUID id);

    News save(News news);

    News update(News news);

    void deleteById(UUID id);
}
