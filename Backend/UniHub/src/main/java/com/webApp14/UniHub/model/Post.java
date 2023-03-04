package com.webApp14.UniHub.model;


import javax.persistence.*;

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
    private String postDate;

    @Column(nullable = false)
    private String postAuthor;

    @Column
    private int postUpvotes;

    // Constructors
    public Post() {
    }

    public Post(String postContent, String postDate, String postAuthor) {
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

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(String postAuthor) {
        this.postAuthor = postAuthor;
    }

    public int getPostUpvotes() {
        return postUpvotes;
    }

    public void setPostUpvotes(int postUpvotes) {
        this.postUpvotes = postUpvotes;
    }
}

