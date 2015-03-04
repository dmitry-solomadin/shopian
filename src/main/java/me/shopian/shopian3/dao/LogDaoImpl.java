package me.shopian.shopian3.dao;

import me.shopian.shopian3.entity.Log;
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
public class LogDaoImpl  implements  LogDao{
    private static Logger logger = LoggerFactory.getLogger(LogDaoImpl.class);

    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void add(Log log) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(log);
    }

    @Override
    @Transactional
    public void update(Log log) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(log);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Log> list(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Log.class);
        criteria.add(Restrictions.eq("user", user));
        return criteria.list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Log> list(User user, int start, int length, List<ColumnDirection> sortColumns, String search) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Log.class);
        criteria.add(Restrictions.eq("user", user));

        if (start > 0) {
            criteria.setFirstResult(start);
        }
        if (length > 0) {
            criteria.setMaxResults(length);
        }
//        if (search != null && !search.isEmpty()) {
//            criteria.add(Restrictions.like("title", "%" + search + "%"));  // TODO !!!!
//        }
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
        Criteria criteria = session.createCriteria(Log.class);
        criteria.add(Restrictions.eq("user", user));
        return (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public long count(User user, String search) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Log.class);
        criteria.add(Restrictions.eq("user", user));
//        if (search != null && !search.isEmpty()) {
//            criteria.add(Restrictions.like("title", "%" + search + "%"));  //TODO !!!!!!!!
//        }
        return (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public Log get(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Log) session.get(Log.class, id);
    }

    @Override
    @Transactional
    public void delete(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Log log = (Log) session.load(Log.class, id);
        if (log != null) {
            session.delete(log);
        }
    }
}
