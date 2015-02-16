package me.shopian.shopian3.dao;

import me.shopian.shopian3.entity.Department;
import me.shopian.shopian3.entity.Shop;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DepartmentDaoImpl implements DepartmentDao{
    private SessionFactory sessionFactory;


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private static Logger logger = LoggerFactory.getLogger(ShopDaoImpl.class);

    @Override
    @Transactional
    public void add(long shopId, String departmentTitle) {
        Session session = this.sessionFactory.getCurrentSession();
        Shop shop= (Shop) session.load(Shop.class, shopId);

        Department department= new Department();
        department.setTitle(departmentTitle);
        department.setShop(shop);
        session.persist(department);

        shop.getDepartments().add(department);
        session.update(shop);
    }

    @Override
    @Transactional
    public void del(long departmentId) {
        Session session = this.sessionFactory.getCurrentSession();
        Department department= (Department)session.get(Department.class, departmentId);
        Shop shop=department.getShop();
        shop.getDepartments().remove(department);
        session.update(shop);
        session.delete(department);
    }

    @Override
    @Transactional
    public void update(long departmentId, String title) {
        Session session = this.sessionFactory.getCurrentSession();
        Department department= (Department)session.get(Department.class, departmentId);
        department.setTitle(title);
        session.update(department);
    }
}
