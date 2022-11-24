package com.blog.service;

import com.blog.dto.PostDto;
import com.blog.dto.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostDto updatePost(Long id, PostDto postDto);

    String deletePost(Long id);

    PostDto getPostById(Long id);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
}
