package com.example.my_news.service.impl;

import com.example.my_news.exception.EntityNotFoundException;
import com.example.my_news.model.News;
import com.example.my_news.model.User;
import com.example.my_news.repository.NewsRepository;
import com.example.my_news.repository.NewsSpecification;
import com.example.my_news.security.AppUserPrincipal;
import com.example.my_news.service.CategoryService;
import com.example.my_news.service.NewsService;
import com.example.my_news.service.UserService;
import com.example.my_news.web.model.single.NewsFilterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final UserService userService;

    @Override
    public Page<News> filterBy(NewsFilterRequest filter) {
        return newsRepository.findAll(
                NewsSpecification.withFilter(filter),
                filter.getPagination().pageRequest());
    }

    @Override
    public News findById(UUID id) {
        return newsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                "Новость с ID {0} не найдена!", id))
        );
    }

    @Override
    public News save(News news) {
        var principal = (AppUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(principal.getUsername());
        news.setUser(user);

        return newsRepository.save(news);
    }

    @Override
    public News update(News news) {
        return newsRepository.save(news);
    }

    @Override
    public void deleteById(UUID id) {
        newsRepository.deleteById(id);
    }

}
