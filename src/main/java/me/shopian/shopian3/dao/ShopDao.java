package me.shopian.shopian3.dao;

import me.shopian.shopian3.entity.Beacon;
import me.shopian.shopian3.entity.Department;
import me.shopian.shopian3.entity.Shop;
import me.shopian.shopian3.entity.User;
import me.shopian.shopian3.util.ColumnDirection;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ShopDao {
    public void add(Shop shop);
    public void update(Shop shop);
    public List list(User user);
    public List<Shop> list(User user, int start, int length, List<ColumnDirection> sortColumns, String search);
    public long count(User user);
    public long count(User user,String search);
    public Shop get(long id);
    public Shop getByTitle(User user,String title);
    public void delete(long id);
}
