package me.shopian.shopian3.dao;

import me.shopian.shopian3.entity.Ad;
import me.shopian.shopian3.entity.Beacon;
import me.shopian.shopian3.util.ColumnDirection;

import java.util.List;

public interface AdDao {
    public void add(Ad ad);

    public void update(Ad ad);

    public List<Ad> list();

    public List<Ad> list(int start, int length, List<ColumnDirection> sortColumns, String search);

    public long count();

    public long count(String search);

    public Ad get(long id);

    public void delete(long id);
}
