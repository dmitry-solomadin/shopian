package me.shopian.shopian3.service;


public interface DepartmentService {
    public void add(long shopId, String departmentTitle);
    public void del(long departmentId);
    public void update(long departmentId,String title);
}
