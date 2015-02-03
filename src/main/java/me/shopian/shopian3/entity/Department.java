package me.shopian.shopian3.entity;

import javax.persistence.*;

@Entity
@Table(name="departments")
public class Department {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private String title;

    @ManyToOne
    private Shop shop;
////////////////////////////////////////////////////////


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

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
