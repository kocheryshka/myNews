package com.example.my_news.mapper;

import com.example.my_news.model.News;
import com.example.my_news.web.model.upsert.UpsertNewsRequest;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-03T16:32:28+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
@Primary
public class NewsMapperImpl extends NewsMapperDecorator {

    @Autowired
    @Qualifier("delegate")
    private NewsMapper delegate;

    @Override
    public void requestToNews(UUID newsId, UpsertNewsRequest request, News news)  {
        delegate.requestToNews( newsId, request, news );
    }
}
