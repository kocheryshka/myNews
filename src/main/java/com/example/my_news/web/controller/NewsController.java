package com.example.my_news.web.controller;

import com.example.my_news.aop.CheckAccess;
import com.example.my_news.aop.EntityType;
import com.example.my_news.mapper.NewsMapper;
import com.example.my_news.model.News;
import com.example.my_news.service.NewsService;
import com.example.my_news.web.model.list.NewsListResponse;
import com.example.my_news.web.model.single.NewsFilterRequest;
import com.example.my_news.web.model.single.OneNewsResponse;
import com.example.my_news.web.model.upsert.UpsertNewsRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
@Tag(name = "News V1", description = "News API version V1")
public class NewsController {

    private final NewsService newsService;

    private final NewsMapper newsMapper;


    @GetMapping
    public ResponseEntity<NewsListResponse> filterBy(NewsFilterRequest request){
        List<News> newsList = newsService.filterBy(request).stream().toList();
        var newsListResponse = newsMapper.newsListToNewsListResponse(newsList);
        return ResponseEntity.ok(newsListResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OneNewsResponse> findById(@PathVariable UUID id){
        return ResponseEntity.ok(newsMapper.oneNewsToResponse(newsService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<OneNewsResponse> save(@RequestBody UpsertNewsRequest request){
        News news = newsService.save(newsMapper.requestToNews(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newsMapper.oneNewsToResponse(news));
    }

    @PutMapping("/{id}")
    @CheckAccess(entityType = EntityType.NEWS)
    public ResponseEntity<OneNewsResponse> update(@PathVariable UUID id, @RequestBody UpsertNewsRequest request){
        News news = newsService.findById(id);
        newsMapper.requestToNews(id, request, news);
        news = newsService.update(news);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newsMapper.oneNewsToResponse(news));
    }

    @DeleteMapping("/{id}")
    @CheckAccess(entityType = EntityType.NEWS)
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        newsService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
