package com.example.my_news.web.controller;

import com.example.my_news.mapper.CommentMapper;
import com.example.my_news.model.Comment;
import com.example.my_news.service.CommentService;
import com.example.my_news.web.model.list.CommentListResponse;
import com.example.my_news.web.model.single.CommentResponse;
import com.example.my_news.web.model.upsert.UpsertCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
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
    public ResponseEntity<CommentResponse> update(@PathVariable UUID id, @RequestBody UpsertCommentRequest request){
        Comment comment = commentService.findById(id);
        comment.setText(request.getText());

        return ResponseEntity.ok(commentMapper.commentToResponse(commentService.save(comment)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        commentService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
