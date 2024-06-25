package com.example.my_news.mapper;

import com.example.my_news.model.Category;
import com.example.my_news.model.Comment;
import com.example.my_news.model.News;
import com.example.my_news.model.User;
import com.example.my_news.web.model.single.CommentResponse;
import com.example.my_news.web.model.single.NewsResponse;
import com.example.my_news.web.model.single.OneNewsResponse;
import com.example.my_news.web.model.upsert.UpsertNewsRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-25T10:22:22+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
@Qualifier("delegate")
public class NewsMapperImpl_ implements NewsMapper {

    @Override
    public OneNewsResponse oneNewsToResponse(News news) {
        if ( news == null ) {
            return null;
        }

        OneNewsResponse oneNewsResponse = new OneNewsResponse();

        oneNewsResponse.setCategoryShortDesc( newsCategoryShortDesc( news ) );
        oneNewsResponse.setUsername( newsUserUsername( news ) );
        oneNewsResponse.setId( news.getId() );
        oneNewsResponse.setTitle( news.getTitle() );
        oneNewsResponse.setText( news.getText() );
        oneNewsResponse.setComments( commentListToCommentResponseList( news.getComments() ) );
        oneNewsResponse.setCreateAt( news.getCreateAt() );
        oneNewsResponse.setUpdateAt( news.getUpdateAt() );

        return oneNewsResponse;
    }

    @Override
    public NewsResponse newsToResponse(News news) {
        if ( news == null ) {
            return null;
        }

        NewsResponse newsResponse = new NewsResponse();

        newsResponse.setCategoryShortDesc( newsCategoryShortDesc( news ) );
        newsResponse.setUsername( newsUserUsername( news ) );
        newsResponse.setId( news.getId() );
        newsResponse.setTitle( news.getTitle() );
        newsResponse.setText( news.getText() );
        newsResponse.setCreateAt( news.getCreateAt() );
        newsResponse.setUpdateAt( news.getUpdateAt() );

        return newsResponse;
    }

    @Override
    public News requestToNews(UpsertNewsRequest request) {
        if ( request == null ) {
            return null;
        }

        News.NewsBuilder news = News.builder();

        news.title( request.getTitle() );
        news.text( request.getText() );

        return news.build();
    }

    @Override
    public void requestToNews(UUID newsId, UpsertNewsRequest request, News news) {
        if ( newsId == null && request == null ) {
            return;
        }

        if ( request != null ) {
            news.setTitle( request.getTitle() );
            news.setText( request.getText() );
        }
    }

    private String newsCategoryShortDesc(News news) {
        if ( news == null ) {
            return null;
        }
        Category category = news.getCategory();
        if ( category == null ) {
            return null;
        }
        String shortDesc = category.getShortDesc();
        if ( shortDesc == null ) {
            return null;
        }
        return shortDesc;
    }

    private String newsUserUsername(News news) {
        if ( news == null ) {
            return null;
        }
        User user = news.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }

    protected CommentResponse commentToCommentResponse(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentResponse commentResponse = new CommentResponse();

        commentResponse.setId( comment.getId() );
        commentResponse.setText( comment.getText() );
        commentResponse.setCreateAt( comment.getCreateAt() );
        commentResponse.setUpdateAt( comment.getUpdateAt() );

        return commentResponse;
    }

    protected List<CommentResponse> commentListToCommentResponseList(List<Comment> list) {
        if ( list == null ) {
            return null;
        }

        List<CommentResponse> list1 = new ArrayList<CommentResponse>( list.size() );
        for ( Comment comment : list ) {
            list1.add( commentToCommentResponse( comment ) );
        }

        return list1;
    }
}
