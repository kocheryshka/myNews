package com.example.my_news.web.controller;

import com.example.my_news.mapper.NewsMapper;
import com.example.my_news.model.News;
import com.example.my_news.service.NewsService;
import com.example.my_news.web.model.list.NewsListResponse;
import com.example.my_news.web.model.single.OneNewsResponse;
import com.example.my_news.web.model.upsert.UpsertNewsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    private final NewsMapper newsMapper;


    @GetMapping
    public ResponseEntity<NewsListResponse> findAll(){
        return ResponseEntity.ok(newsMapper.newsListToNewsListResponse(newsService.findAll()));
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

    @PutMapping("/{newsId}")
    public ResponseEntity<OneNewsResponse> update(@PathVariable UUID newsId, @RequestBody UpsertNewsRequest request){
        News news = newsService.findById(newsId);
        newsMapper.requestToNews(newsId, request, news);
        news = newsService.save(news);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newsMapper.oneNewsToResponse(news));
    }

    @DeleteMapping("/{newsId}")
    public ResponseEntity<Void> delete(@PathVariable UUID newsId){
        newsService.deleteById(newsId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
