package me.shopian.shopian3.service;

import me.shopian.shopian3.entity.Department;
import me.shopian.shopian3.entity.Shop;
import me.shopian.shopian3.entity.User;
import me.shopian.shopian3.util.ColumnDirection;

import java.util.List;

public interface ShopService {
    public void add(Shop shop);

    public void update(Shop shop);

    public List list(User user);

    public List<Shop> list(User user, int start, int length, List<ColumnDirection> sortColumns, String search);

    public long count();

    public Shop get(long id);

    public Shop getByTitle(Shop shop);

    public void delete(long id);


}
