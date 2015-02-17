package me.shopian.shopian3.dao;

import me.shopian.shopian3.entity.Department;
import me.shopian.shopian3.entity.Shop;
import me.shopian.shopian3.entity.User;
import me.shopian.shopian3.util.ColumnDirection;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ShopDaoImpl implements ShopDao {
    private SessionFactory sessionFactory;


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private static Logger logger = LoggerFactory.getLogger(ShopDaoImpl.class);
    @Override
    @Transactional
    public void add(Shop shop) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(shop);
        logger.info(shop.toString());
    }

    @Override
    @Transactional
    public void update(Shop shop) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(shop);
        logger.info(shop.toString());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Shop> list(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Shop.class);
        criteria.add(Restrictions.eq("user",user));
        return criteria.list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Shop> list(User user, int start, int length, List<ColumnDirection> sortColumns, String search) {
        System.out.println("user = [" + user + "], start = [" + start + "], length = [" + length + "], sortColumns = [" + sortColumns + "], search = [" + search + "]");
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Shop.class);
        criteria.add(Restrictions.eq("user",user));

        if (start > 0) {
            criteria.setFirstResult(start);
        }
        if (length>0){
            criteria.setMaxResults(length);
        }
        if (search!=null&&!search.isEmpty()){
            criteria.add(Restrictions.like("title", "%" + search +"%"));
        }
        if (sortColumns != null) {
            for (ColumnDirection cd : sortColumns) {
                if (cd.isDesc()) {
                    criteria.addOrder(Order.desc(cd.getColumn()));
                } else {
                    criteria.addOrder(Order.asc(cd.getColumn()));
                }
            }
        }

        List<Shop> list=criteria.list();
        for(Shop shop:list){
            Hibernate.initialize(shop.getUser());
            Hibernate.initialize(shop.getDepartments());
        }
        System.out.println("list = " + list.size());
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        Session session = this.sessionFactory.getCurrentSession();
        return (long) session.createQuery("select count(*) from Shop").uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public Shop get(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Shop) session.get(Shop.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Shop getByTitle(Shop shop) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Shop.class);
        return (Shop) criteria.add(Restrictions.eq("title", shop.getTitle())).uniqueResult();
    }

    @Override
    @Transactional
    public void delete(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Shop shop = (Shop) session.load(Shop.class, id);
        for (Department department:shop.getDepartments()){
            session.delete(department);
        }
        if (shop != null) {
            session.delete(shop);
        }
    }

}
