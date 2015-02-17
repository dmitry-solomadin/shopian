package me.shopian.shopian3.service;


import me.shopian.shopian3.entity.Department;
import me.shopian.shopian3.entity.Shop;

public interface DepartmentService {
    public void add(long shopId, String departmentTitle);
    public void del(long departmentId);
    public void update(long departmentId,String title);
    public Department get(long id);

}
