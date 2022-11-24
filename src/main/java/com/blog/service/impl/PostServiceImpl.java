package com.blog.service.impl;

import com.blog.dto.PostDto;
import com.blog.exception.ResourceNotFoundException;
import com.blog.model.Post;
import com.blog.repo.PostRepo;
import com.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepo postRepo;
    private final ModelMapper modelMapper;

    // map DTO to Entity
    private Post mapToEntity(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        return post;
    }

    // map Entity to DTO
    private PostDto mapToDto(Post post) {
        PostDto postDto = modelMapper.map(post, PostDto.class);
        return postDto;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post newPost = postRepo.save(post);
        return mapToDto(newPost);
    }

    @Override
    public PostDto updatePost(Long id, PostDto postDto) {
        Post post = getPost(id);

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepo.save(post);
        return mapToDto(updatedPost);
    }

    private Post getPost(Long id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return post;
    }

    @Override
    public String deletePost(Long id) {
        Post post = getPost(id);
        postRepo.delete(post);
        return "Post has been deleted";
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = getPost(id);
        return mapToDto(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<PostDto> postDtos = postRepo.findAll().stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        return postDtos;
    }
}
