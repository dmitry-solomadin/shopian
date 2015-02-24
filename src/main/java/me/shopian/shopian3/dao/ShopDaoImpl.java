package me.shopian.shopian3.dao;

import me.shopian.shopian3.entity.Ad;
import me.shopian.shopian3.entity.Department;
import me.shopian.shopian3.entity.Shop;
import me.shopian.shopian3.entity.User;
import me.shopian.shopian3.util.ColumnDirection;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ShopDaoImpl implements ShopDao {
    private static Logger logger = LoggerFactory.getLogger(ShopDaoImpl.class);

    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

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
        criteria.add(Restrictions.eq("user", user));
        return criteria.list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Shop> list(User user, int start, int length, List<ColumnDirection> sortColumns, String search) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Shop.class);
        criteria.add(Restrictions.eq("user", user));

        if (start > 0) {
            criteria.setFirstResult(start);
        }
        if (length > 0) {
            criteria.setMaxResults(length);
        }
        if (search != null && !search.isEmpty()) {
            criteria.add(Restrictions.like("title", "%" + search + "%"));
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
        return criteria.list();
    }

    @Override
    @Transactional(readOnly = true)
    public long count(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Shop.class);
        criteria.add(Restrictions.eq("user", user));
        return (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public long count(User user, String search) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Shop.class);
        criteria.add(Restrictions.eq("user", user));
        if (search != null && !search.isEmpty()) {
            criteria.add(Restrictions.like("title", "%" + search + "%"));
        }
        return (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public Shop get(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Shop) session.get(Shop.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Shop getByTitle(User user, String title) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Shop.class);
        criteria.add(Restrictions.eq("user", user));
        return (Shop) criteria.add(Restrictions.eq("title", title)).uniqueResult();
    }

    @Override
    @Transactional
    public void delete(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Shop shop = (Shop) session.load(Shop.class, id);
        if (shop != null) {
            Criteria criteria = session.createCriteria(Ad.class);
            criteria.add(Restrictions.eq("shop", shop));
            for(Ad ad: (List<Ad>)criteria.list()){
                ad.setShop(null);
                ad.setDepartment(null);
                session.save(ad);
            }
            session.delete(shop);
        }
    }

}
