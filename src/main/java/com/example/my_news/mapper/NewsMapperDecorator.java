package com.example.my_news.mapper;

import com.example.my_news.model.Category;
import com.example.my_news.model.News;
import com.example.my_news.service.CategoryService;
import com.example.my_news.service.NewsService;
import com.example.my_news.service.UserService;
import com.example.my_news.web.model.single.NewsResponse;
import com.example.my_news.web.model.single.OneNewsResponse;
import com.example.my_news.web.model.upsert.UpsertNewsRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public abstract class NewsMapperDecorator implements NewsMapper{

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private NewsMapper delegate;

    @Override
    public OneNewsResponse oneNewsToResponse(News news) {
        OneNewsResponse response = delegate.oneNewsToResponse(news);
        response.setComments(news.getComments().stream()
                .map(commentMapper::commentToResponse)
                .toList());

        return response;
    }

    @Override
    public NewsResponse newsToResponse(News news) {
        NewsResponse response = delegate.newsToResponse(news);
        response.setCommentsCount(news.getComments().size());
        response.setCategoryShortDesc(news.getCategory().getShortDesc());
        return response;
    }

    @Override
    public News requestToNews(UpsertNewsRequest request) {
        News news = delegate.requestToNews(request);
        Category category = categoryService.findById(request.getCategoryId());
        news.setCategory(category);
        return news;
    }
}
