package me.shopian.shopian3.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "mobile_users")

public class UserMobile {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 40)
    private String token = UUID.randomUUID().toString();

    @ManyToOne
    private User user;

    @Column(name = "date_registration", nullable = false)
    private Date dateRegistration = new Date();

    public UserMobile(User user) {
        this.user = user;
    }

    public UserMobile() {
    }

    /////////////////////////////////////////////////////////


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(Date dateRegistration) {
        this.dateRegistration = dateRegistration;
    }
}
