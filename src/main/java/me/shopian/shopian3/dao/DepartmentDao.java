package me.shopian.shopian3.dao;

public interface DepartmentDao {
    public void add(long shopId, String departmentTitle);
    public void del(long departmentId);
    public void update(long departmentId,String title);
}
