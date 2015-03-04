/**
 * If you really care for the license, look for the LICENSE.txt at the project root. Frankly, I 
 * really don't care.
 **/
package me.shopian.shopian3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;


@Entity
@Table(name = "APP_USER")
public class User implements Serializable {
    private static final long serialVersionUID = -6960838612205920533L;


    @Id
//    @Column(name="USERNAME", nullable=false)
    @Column(nullable = false)
    private String username;


    //    @Column(name="PASSWORD", nullable=false)
    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name="APP_USER_ROLE",
        joinColumns=@JoinColumn(name="USERNAME"),
        inverseJoinColumns=@JoinColumn(name="ROLENAME"))
    private Collection<Role> roles;

    @Column(length = 40)
    private String token;

    //////////////////////////////////////////////////////////////

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}