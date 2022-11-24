package com.blog.service;

import com.blog.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(Long id, CommentDto commentDto);

    CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto);

    String deleteComment(Long postId, Long commentId);

    CommentDto getCommentById(Long postId, Long commentId);

    List<CommentDto> getCommentsByPostId(Long postId);
}
