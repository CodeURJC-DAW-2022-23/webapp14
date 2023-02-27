package com.webApp14.UniHub.model;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate threadDate;

    @Column(nullable = false)
    private String threadAuthor;

    // Constructors
    public Forms() {
    }

    public Forms(String threadTitle, String threadContent_short, String threadContent, LocalDate threadDate, String threadAuthor) {
        this.threadTitle = threadTitle;
        this.threadContent_short = threadContent_short;
        this.threadContent = threadContent;
        this.threadDate = threadDate;
        this.threadAuthor = threadAuthor;
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

    public LocalDate getThreadDate() {
        return threadDate;
    }

    public void setThreadDate(LocalDate threadDate) {
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
}
