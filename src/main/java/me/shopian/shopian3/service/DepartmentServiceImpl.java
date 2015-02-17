package me.shopian.shopian3.service;

import me.shopian.shopian3.dao.DepartmentDaoImpl;
import me.shopian.shopian3.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService{


    @Qualifier("departmentDaoImpl")
    @Autowired
    private DepartmentDaoImpl departmentDao;

    @Override
    public void add(long shopId, String departmentTitle) {
        departmentDao.add(shopId,departmentTitle);
    }

    @Override
    public void del(long departmentId) {
        departmentDao.del(departmentId);

    }

    @Override
    public void update(long departmentId, String title) {
        departmentDao.update(departmentId,title);

    }

    @Override
    public Department get(long id) {
        return departmentDao.get(id);
    }

    @Override
    public Collection list(long shopId) {
        return departmentDao.list(shopId);
    }
}
