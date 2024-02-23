package com.sonia.learning.socialmediablogapp.repository;

import com.sonia.learning.socialmediablogapp.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost,Long> {
}
