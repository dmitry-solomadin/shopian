/**
 * If you really care for the license, look for the LICENSE.txt at the project root. Frankly, I 
 * really don't care.
 **/
package me.shopian.shopian3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="APP_ROLE")
public class Role implements Serializable {
    private static final long serialVersionUID = 1314324262194219402L;
    
    @Id
    @Column(name="ROLENAME", nullable=false)
    private String roleName;

    @JsonIgnore
    @ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name="APP_USER_ROLE",
        joinColumns=@JoinColumn(name="ROLENAME"),
        inverseJoinColumns=@JoinColumn(name="USERNAME"))
    private Collection<User> users;

    /////////////////////////////////////////////////////////

    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public Collection<User> getUsers() {
        return this.users;
    }
    
    public void setUsers(Collection<User> users) {
        this.users = users;
    }

}
