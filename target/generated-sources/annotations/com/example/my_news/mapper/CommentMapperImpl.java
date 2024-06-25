package com.example.my_news.mapper;

import com.example.my_news.model.Comment;
import com.example.my_news.web.model.single.CommentResponse;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-25T10:22:22+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
@Primary
public class CommentMapperImpl extends CommentMapperDecorator {

    @Autowired
    @Qualifier("delegate")
    private CommentMapper delegate;

    @Override
    public CommentResponse commentToResponse(Comment comment)  {
        return delegate.commentToResponse( comment );
    }
}
