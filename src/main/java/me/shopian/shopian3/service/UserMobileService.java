package me.shopian.shopian3.service;

import me.shopian.shopian3.entity.UserMobile;

public interface UserMobileService {
    public void add(UserMobile userMobile);
    public UserMobile get(long id);
    public UserMobile get(String token);
    public void delete(long id);
}
