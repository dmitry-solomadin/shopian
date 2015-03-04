package me.shopian.shopian3.dao;


import me.shopian.shopian3.entity.User;

import java.util.Collection;

public interface UserDao {
    public User findUserByUsername(String username);
    public Collection<User> list(String roleName);
    public User findUserByToken(String token);
}