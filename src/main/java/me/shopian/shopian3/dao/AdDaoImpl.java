package me.shopian.shopian3.dao;

import me.shopian.shopian3.entity.Ad;
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
public class AdDaoImpl implements AdDao {
    private static Logger logger = LoggerFactory.getLogger(BeaconDaoImpl.class);

    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void add(Ad ad) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(ad);
    }

    @Override
    @Transactional
    public void update(Ad ad) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(ad);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ad> list(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Ad.class);
        return criteria.list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ad> list(User user, int start, int length, List<ColumnDirection> sortColumns, String search) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Ad.class);

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
        Criteria criteria = session.createCriteria(Ad.class);
        criteria.add(Restrictions.eq("user", user));
        return (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public long count(User user, String search) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Ad.class);
        criteria.add(Restrictions.eq("user", user));
        System.out.println("search = " + search);
        if (search != null && !search.isEmpty()) {
            criteria.add(Restrictions.like("title", "%" + search + "%"));
        }
        return (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public Ad get(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Ad) session.get(Ad.class, id);
    }

    @Override
    @Transactional
    public void delete(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Ad ad = (Ad) session.load(Ad.class, id);
        if (ad != null) {
            session.delete(ad);
        }
    }
}
