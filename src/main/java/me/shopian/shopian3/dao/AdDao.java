package me.shopian.shopian3.dao;

import me.shopian.shopian3.entity.Ad;
import me.shopian.shopian3.entity.Beacon;
import me.shopian.shopian3.entity.User;
import me.shopian.shopian3.util.ColumnDirection;

import java.util.List;

public interface AdDao {
    public void add(Ad ad);
    public void update(Ad ad);
    public List<Ad> list(User user);
    public List<Ad> list(User user,int start, int length, List<ColumnDirection> sortColumns, String search);
    public long count(User user);
    public long count(User user,String search);
    public Ad get(long id);
    public void delete(long id);
}
