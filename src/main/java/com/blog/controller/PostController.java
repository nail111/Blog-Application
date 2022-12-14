package com.blog.controller;

import com.blog.dto.PostDto;
import com.blog.service.PostService;
import com.blog.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Api(value = "CRUD REST APIs for Post Controller")
public class PostController {
    private final PostService postService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "api/posts", headers = "X-API-VERSION=1")
    @ApiOperation(value = "Create a post")
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "api/posts/{postId}", headers = "X-API-VERSION=1")
    @ApiOperation(value = "Update a post")
    public ResponseEntity<?> updatePost(@PathVariable("postId") Long id, @Valid @RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.updatePost(id, postDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "api/posts/{postId}", headers = "X-API-VERSION=1")
    @ApiOperation(value = "Delete a post")
    public ResponseEntity<?> deletePost(@PathVariable("postId") Long id) {
        return ResponseEntity.ok(postService.deletePost(id));
    }

    @GetMapping(value = "api/posts/{postId}", headers = "X-API-VERSION=1")
    @ApiOperation(value = "Get post by id")
    public ResponseEntity<?> getPostById(@PathVariable("postId") Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping(value = "api/posts/{postId}", headers = "X-API-VERSION=2")
    @ApiOperation(value = "Get post by id version 2")
    public ResponseEntity<?> getPostByIdV2(@PathVariable("postId") Long id) {
        return ResponseEntity.ok("This API is version 2");
    }

    @GetMapping(value = "api/posts", headers = "X-API-VERSION=1")
    @ApiOperation(value = "Get all posts")
    public ResponseEntity<?> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return ResponseEntity.ok(postService.getAllPosts(pageNo, pageSize, sortBy, sortDir));
    }
}
