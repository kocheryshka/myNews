package com.example.my_news.web.controller;

import com.example.my_news.aop.CheckAccess;
import com.example.my_news.aop.EntityType;
import com.example.my_news.mapper.CommentMapper;
import com.example.my_news.model.Comment;
import com.example.my_news.service.CommentService;
import com.example.my_news.web.model.list.CommentListResponse;
import com.example.my_news.web.model.single.CommentResponse;
import com.example.my_news.web.model.upsert.UpsertCommentRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
@Tag(name = "Comment V1", description = "Comment API version V1")
public class CommentController {

    private final CommentService commentService;

    private final CommentMapper commentMapper;

    @GetMapping
    public ResponseEntity<CommentListResponse> findAllByNewsId(@RequestParam UUID newsId){
        return ResponseEntity.ok(commentMapper.commentListToCommentListResponse(commentService.findAllByNewsId(newsId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable UUID id){
        return ResponseEntity.ok(commentMapper.commentToResponse(commentService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> save(@RequestBody UpsertCommentRequest request){
        Comment comment = commentService.save(commentMapper.requestToComment(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(commentMapper.commentToResponse(comment));
    }

    @PutMapping("/{id}")
    @CheckAccess(entityType = EntityType.COMMENT)
    public ResponseEntity<CommentResponse> update(@PathVariable UUID id, @RequestBody UpsertCommentRequest request){
        Comment comment = commentService.findById(id);
        comment.setText(request.getText());

        return ResponseEntity.ok(commentMapper.commentToResponse(commentService.save(comment)));
    }

    @DeleteMapping("/{id}")
    @CheckAccess(entityType = EntityType.COMMENT)
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        commentService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
