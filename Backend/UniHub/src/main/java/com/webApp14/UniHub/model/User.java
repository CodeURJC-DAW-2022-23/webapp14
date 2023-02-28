package com.webApp14.UniHub.model;

import com.webApp14.UniHub.model.Pack;
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

    @Column(nullable = false)
    private Boolean discount;

    @Column
    @OneToMany
    private List<Forms> formsList;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;
    // @ElementCollection(fetch = FetchType.EAGER)
    //private List<Pack> userPack_List;

    // Constructors
    public User() {
    }

    public User(String username, String email, String password, Boolean discount) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.discount = discount;
        //this.roles = roles;
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

    public Boolean getDiscount() {
        return discount;
    }

    public void setDiscount(Boolean discount) {
        this.discount = discount;
    }

    public List<Forms> getFormsList() {
        return formsList;
    }

    public void setFormsList(List<Forms> formsList) {
        this.formsList = formsList;
    }

    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

   /* public List<Pack> getUserPack_List() {
        return userPack_List;
    }

    public void setUserPack_List(List<Pack> userPack_List) {
        this.userPack_List = userPack_List;
    }*/


}
