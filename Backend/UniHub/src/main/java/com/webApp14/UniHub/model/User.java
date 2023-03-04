package com.webApp14.UniHub.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    @OneToMany
    private List<Forms> formsList;

    @ManyToMany
    private List<Pack> packList = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    @Lob
    private byte[] image;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    // Constructors
    public User() {
    }

    public User(String username, String email, String password, String... roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = List.of(roles);
    }

    // Setters and Getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<Forms> getFormsList() {
        return formsList;
    }

    public void setFormsList(List<Forms> formsList) {
        this.formsList = formsList;
    }

    public List<Pack> getPackList() {
        return packList;
    }

    public void setPackList(List<Pack> packList) {
        this.packList = packList;
    }
}
