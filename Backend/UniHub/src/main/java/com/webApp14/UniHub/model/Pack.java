package com.webApp14.UniHub.model;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;


@Entity
public class Pack {

    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String packTitle;

    @Column(nullable = false, unique = true)
    private String packTitle_expanded;

    @Column(nullable = false)
    private String packDescription_short;

    @Lob
    @Column(nullable = false)
    private String packDescriptionLong;

    @Column(nullable = false)
    private Float packPrice;

    @Column(nullable = false)
    private String packImage;

    @ManyToMany(mappedBy = "packs")
    private List<Tags> tags;

    // Constructors
    protected Pack(){}

    public Pack(String packTitle, String packImage, String packTitle_expanded, String packDescription_short, String packDescriptionLong, Float packPrice, List<Tags> tags) {
        super();
        this.packTitle = packTitle;
        this.packImage = packImage;
        this.packTitle_expanded = packTitle_expanded;
        this.packDescription_short = packDescription_short;
        this.packDescriptionLong = packDescriptionLong;
        this.packPrice = packPrice;
        this.tags = tags;
    }

    // Setters and Getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackTitle() {
        return packTitle;
    }

    public void setPackTitle(String packTitle) {
        this.packTitle = packTitle;
    }

    public String getPackDescription_short() {
        return packDescription_short;
    }

    public void setPackDescription_short(String packDescription_short) {
        this.packDescription_short = packDescription_short;
    }

    public String getpackDescriptionLong() {
        return packDescriptionLong;
    }

    public void setpackDescriptionLong(String packDescriptionLong) {
        this.packDescriptionLong = packDescriptionLong;
    }

    public Float getPackPrice() {
        return packPrice;
    }

    public void setPackPrice(Float packPrice) {
        this.packPrice = packPrice;
    }

    public String getPackImage() {
        return packImage;
    }

    public void setPackImage(String packImage) {
        this.packImage = packImage;
    }

    public String getPackTitle_expanded() {
        return packTitle_expanded;
    }

    public void setPackTitle_expanded(String packTitle_expanded) {
        this.packTitle_expanded = packTitle_expanded;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }
}
