package com.salimatech.zazie.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 2353528370345499815L;
    private Long id;
    private String userId;
    private String email;

    public User() {
        super();
    }

    public User(String userId, String email) {
        this.setUserId(userId);
        this.setEmail(email);
    }

    @Id
    @GeneratedValue()
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(unique = true, updatable = false)
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    @Column(unique = true, updatable = false)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
