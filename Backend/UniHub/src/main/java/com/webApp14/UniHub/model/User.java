package com.webApp14.UniHub.model;

import javax.persistence.*;
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

  /*  @Column(nullable = false)
    private Boolean discount;*/
    @Column
    private boolean admin;

    @Column
    @OneToMany
    private List<Forms> formsList;

    @Column
    @OneToMany
    private List<Pack> packList;

    // Constructors
    public User() {
    }

    public User(String username, String email, String password, Boolean admin) {
        this.username = username;
        this.email = email;
        this.password = password;
       // this.discount = discount;
        this.admin = admin;
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

  /*  public Boolean getDiscount() {
        return discount;
    }

    public void setDiscount(Boolean discount) {
        this.discount = discount;
    }*/

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

    public boolean getAdmin() {
        return admin;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }



}
