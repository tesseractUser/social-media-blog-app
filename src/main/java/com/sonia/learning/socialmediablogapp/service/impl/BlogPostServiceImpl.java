package com.sonia.learning.socialmediablogapp.service.impl;

import com.sonia.learning.socialmediablogapp.dto.BlogPostDto;
import com.sonia.learning.socialmediablogapp.entity.BlogPost;
import com.sonia.learning.socialmediablogapp.exception.ResourceNotFoundException;
import com.sonia.learning.socialmediablogapp.repository.BlogPostRepository;
import com.sonia.learning.socialmediablogapp.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogPostServiceImpl implements BlogPostService {
    @Autowired
    private BlogPostRepository blogPostRepository;


    @Override
    public BlogPostDto createPost(BlogPostDto blogPostDto){
        //Map post DTO to entity
        BlogPost blogPost =mapDtoToEntity(blogPostDto);

        // save to Db
        BlogPost savedPost=blogPostRepository.save(blogPost);
        //
        BlogPostDto savedPostDto=mapEntityToDto(savedPost);

        return savedPostDto;
    }


    @Override
    public List<BlogPostDto> getAllPosts() {
        List<BlogPost> allPosts = blogPostRepository.findAll();
        //Map Post Entity to PostDto
        List<BlogPostDto> postDtoList = allPosts.stream().map(post -> mapEntityToDto(post)).collect(Collectors.toList());
        return postDtoList;
    }
    @Override
    public BlogPostDto getPostById(Long id) {
        BlogPost post = blogPostRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
        BlogPostDto postDto = mapEntityToDto(post);
        return postDto;
    }

    @Override
    public BlogPostDto updatePost(BlogPostDto postDto, long id) {
        BlogPost existingPost = blogPostRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));

        existingPost.setDescription(postDto.getDescription());
        existingPost.setContent(postDto.getContent());
        existingPost.setTitle(postDto.getTitle());

        BlogPost updatedPost = blogPostRepository.save(existingPost);
        return mapEntityToDto(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        BlogPost existingPost = blogPostRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
        blogPostRepository.delete(existingPost);
    }

    private BlogPostDto mapEntityToDto(BlogPost post) {
        BlogPostDto postDto = new BlogPostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());
        return postDto;
    }

    private BlogPost mapDtoToEntity(BlogPostDto postDto) {
        BlogPost post = new BlogPost();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        return post;
    }
}

