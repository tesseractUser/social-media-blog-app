package com.sonia.learning.socialmediablogapp.service;
import com.sonia.learning.socialmediablogapp.dto.BlogPostDto;

import java.util.List;

public interface BlogPostService {

    BlogPostDto createPost(BlogPostDto postDto);

    List<BlogPostDto> getAllPosts();

    BlogPostDto getPostById(Long id);

    BlogPostDto updatePost(BlogPostDto postDto, long id);

    void deletePostById(long id);
}