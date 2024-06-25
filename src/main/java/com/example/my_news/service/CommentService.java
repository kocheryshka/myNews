package com.example.my_news.service;

import com.example.my_news.model.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    List<Comment> findAllByNewsId(UUID newsId);

    Comment findById(UUID id);

    Comment save(Comment comment);

    Comment update(Comment comment);

    void deleteById(UUID id);

}
