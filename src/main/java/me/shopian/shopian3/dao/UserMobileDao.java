package me.shopian.shopian3.dao;

import me.shopian.shopian3.entity.UserMobile;

public interface UserMobileDao {
    public void add(UserMobile userMobile);
    public UserMobile get(long id);
    public UserMobile get(String token);
    public void delete(long id);
}
