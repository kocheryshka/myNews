package com.example.my_news.mapper;

import com.example.my_news.model.Comment;
import com.example.my_news.model.News;
import com.example.my_news.service.NewsService;
import com.example.my_news.service.UserService;
import com.example.my_news.web.model.upsert.UpsertCommentRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CommentMapperDecorator implements CommentMapper{

    @Autowired
    private CommentMapper delegate;

    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;

    @Override
    public Comment requestToComment(UpsertCommentRequest request) {
        Comment comment = delegate.requestToComment(request);
        News news = newsService.findById(request.getNewsId());
        comment.setNews(news);
        return comment;
    }
}
