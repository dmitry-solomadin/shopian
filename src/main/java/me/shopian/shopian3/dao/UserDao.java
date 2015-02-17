/**
 * If you really care for the license, look for the LICENSE.txt at the project root. Frankly, I 
 * really don't care.
 **/
package me.shopian.shopian3.dao;


import me.shopian.shopian3.entity.User;

import java.util.Collection;

public interface UserDao {
    public User findUserByUsername(String username);
    public Collection<User> list(String roleName);
}