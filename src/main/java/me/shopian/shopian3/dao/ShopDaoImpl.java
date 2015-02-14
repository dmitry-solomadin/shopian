package me.shopian.shopian3.dao;

import me.shopian.shopian3.entity.Shop;
import me.shopian.shopian3.util.ColumnDirection;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ShopDaoImpl implements ShopDao {
    private SessionFactory sessionFactory;


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private static Logger logger = LoggerFactory.getLogger(ShopDaoImpl.class);
    @Override
    public void add(Shop shop) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(shop);
        logger.info(shop.toString());
    }

    @Override
    public void update(Shop shop) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(shop);
        logger.info(shop.toString());
    }

    @Override
    public List<Shop> list() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from Shop").list();
    }

    @Override
    public List<Shop> list(int start, int length, List<ColumnDirection> sortColumns, String search) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Shop.class);
       if (start>0){
            criteria.setFirstResult(start);
        }
        if (length>0){
            criteria.setMaxResults(length);
        }
        if (search!=null&&!search.isEmpty()){
            criteria.add(Restrictions.like("title", "%"+search+"%"));
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
    public long count() {
        Session session = this.sessionFactory.getCurrentSession();
        return (long) session.createQuery("select count(*) from Shop").uniqueResult();
    }

    @Override
    public Shop get(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Shop) session.load(Shop.class, id);
    }

    @Override
    public Shop getByTitle(Shop shop) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Shop.class);
        return (Shop) criteria.add(Restrictions.eq("title", shop.getTitle())).uniqueResult();
    }

    @Override
    public void delete(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Shop shop = (Shop) session.load(Shop.class, id);
        logger.debug("SHOP:"+shop);
        if (shop != null) {
            session.delete(shop);
        }
    }

}
