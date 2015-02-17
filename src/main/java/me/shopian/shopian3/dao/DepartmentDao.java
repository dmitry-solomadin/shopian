package me.shopian.shopian3.dao;

import me.shopian.shopian3.entity.Department;
import me.shopian.shopian3.entity.Shop;

import java.util.Collection;
import java.util.List;

public interface DepartmentDao {
    public void add(long shopId, String departmentTitle);
    public void del(long departmentId);
    public void update(long departmentId,String title);
    public Department get(long id);
    public Collection list(long shopId);


}
