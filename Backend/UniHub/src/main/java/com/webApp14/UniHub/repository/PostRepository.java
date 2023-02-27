package com.webApp14.UniHub.repository;

import com.webApp14.UniHub.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
