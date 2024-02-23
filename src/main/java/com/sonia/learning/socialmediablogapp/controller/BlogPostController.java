package com.sonia.learning.socialmediablogapp.controller;

import com.sonia.learning.socialmediablogapp.dto.BlogPostDto;
import com.sonia.learning.socialmediablogapp.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")

public class BlogPostController {
    @Autowired
    private BlogPostService blogPostService;

   @PostMapping("/new")
    public ResponseEntity<BlogPostDto> createPost(@RequestBody BlogPostDto blogPostDTO){
       BlogPostDto newPost= blogPostService.createPost(blogPostDTO);
       return new ResponseEntity(newPost, HttpStatus.CREATED);
   }

    @GetMapping("/getAll")
    public List<BlogPostDto> getAllPosts() {
        return blogPostService.getAllPosts();
    }

    //GET /api/posts/{id}
    @GetMapping("/{id}")
    public ResponseEntity<BlogPostDto> getPostById(@PathVariable long id) {
        return ResponseEntity.ok(blogPostService.getPostById(id));
    }

    //PUT /api/posts/{id}

    @PutMapping("/update/{id}")
    public ResponseEntity<BlogPostDto> updatePost(@RequestBody BlogPostDto postDto, @PathVariable long id) {
        BlogPostDto updatedPostResponse = blogPostService.updatePost(postDto, id);
        return ResponseEntity.ok(updatedPostResponse);
    }

    //DELETE ///api/posts/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id) {
        blogPostService.deletePostById(id);
        return ResponseEntity.ok("Deleted Successfully Post Resource :: "+id);
    }


}
