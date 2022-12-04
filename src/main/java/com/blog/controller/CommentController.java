package com.blog.controller;

import com.blog.dto.CommentDto;
import com.blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/posts")
@Api(value = "CRUD REST API for Comment Controller")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postId}/comments")
    @ApiOperation(value = "Create a comment")
    public ResponseEntity<?> createComment(@PathVariable("postId") Long id, @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(id, commentDto), HttpStatus.CREATED);
    }

    @PutMapping("/{postId}/comments/{commentId}")
    @ApiOperation(value = "Update a comment")
    public ResponseEntity<?> updateComment(
            @PathVariable("postId") Long postId,
            @PathVariable("commentId") Long commentId,
            @Valid @RequestBody CommentDto commentDto
    ) {
        return ResponseEntity.ok(commentService.updateComment(postId, commentId, commentDto));
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    @ApiOperation(value = "Delete a comment")
    public ResponseEntity<?> deleteComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId) {
        return ResponseEntity.ok(commentService.deleteComment(postId, commentId));
    }

    @GetMapping("/{postId}/comments/{commentId}")
    @ApiOperation(value = "Get a comment by id")
    public ResponseEntity<?> getCommentById(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId) {
        return ResponseEntity.ok(commentService.getCommentById(postId, commentId));
    }

    @GetMapping("/{postId}/comments")
    @ApiOperation(value = "Get all comments by post id")
    public ResponseEntity<?> getCommentsByPostId(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }
}
