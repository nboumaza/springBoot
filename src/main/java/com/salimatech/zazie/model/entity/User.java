package com.salimatech.zazie.model.entity;

import javax.persistence.*;
import java.io.Serializable;


/**
 *  User entity
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 2353528370345499815L;
    private long id;
    private String userId;
    private String email;


    public User() {
        super();
    }

    public User(String userId, String email) {
        this.setUserId(userId);
        this.setEmail(email);
    }


    /**
     * getter
     * @return id of the user account
     */
    @Id
    @GeneratedValue()
    public long getId() {
        return this.id;
    }

    /**
     * setter
     * @param id unique user account id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *  getter
     * @return user account userId
     */
    @Column(unique = true, updatable = false)
    public String getUserId() {
        return this.userId;
    }

    /**
     * setter
     * @param userId user account userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * setter
     * @return email associated with the account
     */
    @Column(unique = true, updatable = false)
    public String getEmail() {
        return this.email;
    }

    /**
     * setter
     * @param email unique email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


}
