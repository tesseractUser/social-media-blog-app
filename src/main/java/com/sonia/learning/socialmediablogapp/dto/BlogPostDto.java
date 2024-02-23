package com.sonia.learning.socialmediablogapp.dto;

import lombok.Data;

@Data
public class BlogPostDto {
    private Long id;
    private String title;
    private String description;
    private String content;

}
