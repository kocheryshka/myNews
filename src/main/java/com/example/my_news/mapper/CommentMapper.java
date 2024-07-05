package com.example.my_news.mapper;

import com.example.my_news.model.Comment;
import com.example.my_news.web.model.list.CommentListResponse;
import com.example.my_news.web.model.single.CommentResponse;
import com.example.my_news.web.model.upsert.UpsertCommentRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", typeConversionPolicy = ReportingPolicy.IGNORE)
@DecoratedWith(CommentMapperDecorator.class)
public interface CommentMapper {

    @Mapping(source = "comment.user.username", target = "username")
    CommentResponse commentToResponse(Comment comment);

    Comment requestToComment(UpsertCommentRequest request);

    default CommentListResponse commentListToCommentListResponse(List<Comment> comments){
        CommentListResponse commentListResponse = new CommentListResponse();
        commentListResponse.setComments(comments.stream()
                .map(this::commentToResponse)
                .toList());

        return commentListResponse;
    }
}
