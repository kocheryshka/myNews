package com.example.my_news.service.impl;

import com.example.my_news.aop.CheckAccess;
import com.example.my_news.model.Comment;
import com.example.my_news.model.News;
import com.example.my_news.model.User;
import com.example.my_news.repository.CommentRepository;
import com.example.my_news.security.AppUserPrincipal;
import com.example.my_news.service.CommentService;
import com.example.my_news.service.NewsService;
import com.example.my_news.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final NewsService newsService;

    private final UserService userService;

    @Override
    public List<Comment> findAllByNewsId(UUID newsId) {
        return commentRepository.findAllByNewsId(newsId);
    }

    @Override
    public Comment findById(UUID id) {
        return commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                "Комментарий с ID {0} не найден!", id))
        );
    }

    @Override
    public Comment save(Comment comment) {
        var principal = (AppUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(principal.getUsername());
        comment.setUser(user);

        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment comment) {
        Comment existingComment = findById(comment.getId());
        existingComment.setText(comment.getText());

        return commentRepository.save(existingComment);
    }

    @Override
    public void deleteById(UUID id) {
        commentRepository.deleteById(id);
    }

}
