package me.shopian.shopian3.dao;

import me.shopian.shopian3.entity.Ad;
import me.shopian.shopian3.util.ColumnDirection;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AdDaoImpl implements AdDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private static Logger logger = LoggerFactory.getLogger(BeaconDaoImpl.class);
    @Override
    public void add(Ad ad) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(ad);
    }

    @Override
    public void update(Ad ad) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(ad);
    }

    @Override
    public List<Ad> list() {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Ad.class);
        return criteria.list();
    }

    @Override
    public List<Ad> list(int start, int length, List<ColumnDirection> sortColumns, String search) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Ad.class);

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
        return criteria.list();
    }

    @Override
    public long count() {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Ad.class);
        return (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public long count(String search) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Ad.class);
        System.out.println("search = " + search);
        if (search!=null&&!search.isEmpty()){
            criteria.add(Restrictions.like("title", "%" + search + "%"));
        }
        return (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Ad get(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Ad) session.get(Ad.class, id);
    }

    @Override
    public void delete(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Ad ad = (Ad) session.load(Ad.class, id);
        if (ad != null) {
            session.delete(ad);
        }
    }
}
