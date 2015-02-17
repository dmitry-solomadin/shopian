package me.shopian.shopian3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(
        name = "beacons",
        uniqueConstraints = @UniqueConstraint(columnNames = {"uuid", "minor", "major"})
)
public class Beacon {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column(nullable = false)
    private String uuid;

    @Column(nullable = false)
    private int minor;

    @Column(nullable = false)
    private int major;

    @ManyToOne
//    @JsonIgnore
    private User user;

    @ManyToOne
    private Shop shop;

    @ManyToOne
    private Department department;


    ///////////////////////////////////////////////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
