package com.webApp14.UniHub.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String postContent;

    @Column(nullable = false)
    private LocalDateTime postDate;

    @Column(nullable = false, unique = true)
    private String postAuthor;

    // Constructors
    public Post() {
    }

    public Post(String postContent, LocalDateTime postDate, String postAuthor) {
        this.postContent = postContent;
        this.postDate = postDate;
        this.postAuthor = postAuthor;
    }

    // Setters and Getters
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public String getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(String postAuthor) {
        this.postAuthor = postAuthor;
    }
}

