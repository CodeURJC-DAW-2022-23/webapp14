package com.webApp14.UniHub.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Forms {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String threadTitle;

    @Column(nullable = false)
    private String threadContent_short;

    @Lob
    @Column(nullable = false)
    private String threadContent;

    @Column(nullable = false)
    private String threadDate;

    @Column(nullable = false)
    private String threadAuthor;

    @Column
    private int threadUpvotes;

    @Column
    private String threadImage;
    @Column
    @OneToMany
    private List<Post> posts;

    // Constructors
    public Forms() {
    }

    public Forms(String threadTitle, String threadContent_short, String threadContent, String threadDate, String threadAuthor, int threadUpvotes, String threadImage) {
        this.threadTitle = threadTitle;
        this.threadContent_short = threadContent_short;
        this.threadContent = threadContent;
        this.threadDate = threadDate;
        this.threadAuthor = threadAuthor;
        this.threadUpvotes = threadUpvotes;
        this.threadImage = threadImage;
    }


    // Setters and Getters
    public String getThreadTitle() {
        return threadTitle;
    }

    public void setThreadTitle(String threadTitle) {
        this.threadTitle = threadTitle;
    }

    public String getThreadContent() {
        return threadContent;
    }

    public void setThreadContent(String threadContent) {
        this.threadContent = threadContent;
    }

    public String getThreadAuthor() {
        return threadAuthor;
    }

    public void setThreadAuthor(String threadAuthor) {
        this.threadAuthor = threadAuthor;
    }

    public String getThreadContent_short() {
        return threadContent_short;
    }

    public String getThreadDate() {
        return threadDate;
    }

    public void setThreadDate(String threadDate) {
        this.threadDate = threadDate;
    }

    public void setThreadContent_short(String threadContent_short) {
        this.threadContent_short = threadContent_short;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public int getThreadUpvotes() {
        return threadUpvotes;
    }

    public void setThreadUpvotes(int threadUpvotes) {
        this.threadUpvotes = threadUpvotes;
    }

    public String getThreadImage() {
        return threadImage;
    }

    public void setThreadImage(String threadImage) {
        this.threadImage = threadImage;
    }
}
