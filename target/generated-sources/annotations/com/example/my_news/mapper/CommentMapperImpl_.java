package com.example.my_news.mapper;

import com.example.my_news.model.Comment;
import com.example.my_news.model.User;
import com.example.my_news.web.model.single.CommentResponse;
import com.example.my_news.web.model.upsert.UpsertCommentRequest;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-04T10:49:36+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
@Qualifier("delegate")
public class CommentMapperImpl_ implements CommentMapper {

    @Override
    public CommentResponse commentToResponse(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentResponse commentResponse = new CommentResponse();

        commentResponse.setUsername( commentUserUsername( comment ) );
        commentResponse.setId( comment.getId() );
        commentResponse.setText( comment.getText() );
        commentResponse.setCreateAt( comment.getCreateAt() );
        commentResponse.setUpdateAt( comment.getUpdateAt() );

        return commentResponse;
    }

    @Override
    public Comment requestToComment(UpsertCommentRequest request) {
        if ( request == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setText( request.getText() );

        return comment;
    }

    private String commentUserUsername(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        User user = comment.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }
}
