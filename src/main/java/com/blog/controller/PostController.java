package com.blog.controller;

import com.blog.dto.PostDto;
import com.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable("postId") Long id, @RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.updatePost(id, postDto));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable("postId") Long id) {
        return ResponseEntity.ok(postService.deletePost(id));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable("postId") Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }
}
