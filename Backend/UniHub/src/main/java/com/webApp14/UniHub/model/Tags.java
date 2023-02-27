package com.webApp14.UniHub.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Tags {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String tagName;

    @Column(nullable = false, unique = true)
    private String tagType;

    @ManyToMany
    private List<Pack> packs;

    // Constructors
    protected Tags() {
    }
    public Tags(String tagName, String tagType, List<Pack> packs) {
        this.tagName = tagName;
        this.tagType = tagType;
        this.packs = packs;
    }

    // Setters and Getters
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<Pack> getPacks() {
        return packs;
    }

    public void setPacks(List<Pack> packs) {
        this.packs = packs;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }


}
