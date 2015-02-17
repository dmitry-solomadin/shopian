package me.shopian.shopian3.dao;

import me.shopian.shopian3.entity.Department;

public interface DepartmentDao {
    public void add(long shopId, String departmentTitle);
    public void del(long departmentId);
    public void update(long departmentId,String title);
    public Department get(long id);

}
