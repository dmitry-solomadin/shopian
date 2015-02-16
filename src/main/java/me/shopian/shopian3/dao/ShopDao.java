package me.shopian.shopian3.dao;

import me.shopian.shopian3.entity.Beacon;
import me.shopian.shopian3.entity.Department;
import me.shopian.shopian3.entity.Shop;
import me.shopian.shopian3.util.ColumnDirection;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ShopDao {
    public void add(Shop shop);

    public void update(Shop shop);

    public List<Shop> list();

    public List<Shop> list(int start, int length, List<ColumnDirection> sortColumns, String search);

    public long count();

    public Shop get(long id);

    public Shop getByTitle(Shop shop);

    public void delete(long id);



}
