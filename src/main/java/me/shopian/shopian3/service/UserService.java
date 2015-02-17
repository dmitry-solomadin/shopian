/**
 * If you really care for the license, look for the LICENSE.txt at the project root. Frankly, I 
 * really don't care.
 **/
package me.shopian.shopian3.service;

import me.shopian.shopian3.entity.User;

import java.util.Collection;

public interface UserService {
    public User getCurrentUser();
    public Collection<User> list(String roleName);
    public User findUserByUsername(String username);



}
