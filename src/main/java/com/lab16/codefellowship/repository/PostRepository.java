package com.lab16.codefellowship.repository;

import com.lab16.codefellowship.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
  public Post findByApplicationUser(String applicationUser);
}
