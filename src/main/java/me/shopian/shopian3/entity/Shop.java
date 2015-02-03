package me.shopian.shopian3.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="shops")
public class Shop {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private String title;

    @Column(nullable=true)
    private String address;

    @OneToMany
    private Collection<Department> departments=new ArrayList();


/////////////////////////////////////////////////////


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Collection<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Collection<Department> departments) {
        this.departments = departments;
    }
}
