package com.example.my_news.mapper;

import com.example.my_news.model.News;
import com.example.my_news.web.model.list.NewsListResponse;
import com.example.my_news.web.model.single.NewsResponse;
import com.example.my_news.web.model.single.OneNewsResponse;
import com.example.my_news.web.model.upsert.UpsertNewsRequest;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE//,
//        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
@DecoratedWith(NewsMapperDecorator.class)
public interface NewsMapper {

    @Mapping(source = "news.category.shortDesc", target = "categoryShortDesc")
    @Mapping(source = "news.user.username", target = "username")
    OneNewsResponse oneNewsToResponse(News news);

    @Mapping(source = "news.category.shortDesc", target = "categoryShortDesc")
    @Mapping(source = "news.user.username", target = "username")
    NewsResponse newsToResponse(News news);

    News requestToNews(UpsertNewsRequest request);

    void requestToNews(UUID newsId, UpsertNewsRequest request, @MappingTarget News news);

    default NewsListResponse newsListToNewsListResponse(List<News> newsList){
        NewsListResponse newsListResponse = new NewsListResponse();
        newsListResponse.setNewsList(newsList.stream()
                .map(this::newsToResponse)
                .toList());

        return newsListResponse;
    }

}
