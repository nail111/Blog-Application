package com.blog.service;

import com.blog.dto.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostDto updatePost(Long id, PostDto postDto);

    String deletePost(Long id);

    PostDto getPostById(Long id);

    List<PostDto> getAllPosts();
}
