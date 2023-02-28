package com.webApp14.UniHub.model;


import javax.persistence.*;

@Entity
public class ThreadPics {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String picRoute;

    @Column(nullable = false, unique = true)
    private String picName;


    // Constructor
    public ThreadPics() {
    }

    public ThreadPics(String picRoute, String picName) {
        this.picRoute = picRoute;
        this.picName = picName;
    }


    // Setters and Getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicRoute() {
        return picRoute;
    }

    public void setPicRoute(String picRoute) {
        this.picRoute = picRoute;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }
}
