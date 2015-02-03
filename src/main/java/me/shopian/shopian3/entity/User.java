/**
 * If you really care for the license, look for the LICENSE.txt at the project root. Frankly, I 
 * really don't care.
 **/
package me.shopian.shopian3.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;


@Entity
@Table(name="APP_USER")
public class User implements Serializable{

    /**
     * Randomly generated serialVersionUID
     **/
    private static final long serialVersionUID = -6960838612205920533L;
    
    /**
     * The unique username
     **/
    @Id
//    @Column(name="USERNAME", nullable=false)
    @Column(nullable=false)
    private String username;
    
    /**
     * Hashed password of the user
     **/
//    @Column(name="PASSWORD", nullable=false)
    @Column(nullable=false)
    private String password;


    /**
     * The collection of roles associated with the user
     **/
    @ManyToMany(
        fetch=FetchType.LAZY,
        cascade = CascadeType.ALL)
    @JoinTable(
        name="APP_USER_ROLE",
        joinColumns=@JoinColumn(name="USERNAME"),
        inverseJoinColumns=@JoinColumn(name="ROLENAME"))
    private Collection<Role> roles;
    
    /**
     * Reutrns the username of the user
     * @return username of the user
     **/
    public String getUsername() {
        return username;
    }
    
    /**
     * Sets the username of the user
     * @param username username of the user
     **/
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Reutrns the password of the user
     * @return password of the user
     **/
    public String getPassword() {
        return password;
    }
    
    /**
     * Sets the password of the user
     * @param password password of the user
     **/
    public void setPassword(String password) {
        this.password = password;
    }
    


    /**
     * Returns the collection of roles associated to this user
     * @returns the collection of roles
     **/
    public Collection<Role> getRoles() {
        return this.roles;
    }
    
    /**
     * Sets the collection of roles associated to this user
     * @param roles Collection of roles
     **/
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}