package me.shopian.shopian3.dao;

import me.shopian.shopian3.entity.Beacon;
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
public class BeaconDaoImpl implements BeaconDao {
    private static Logger logger = LoggerFactory.getLogger(BeaconDaoImpl.class);

    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void add(Beacon beacon) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(beacon);
        logger.info(beacon.toString());
    }

    @Override
    @Transactional
    public void update(Beacon beacon) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(beacon);
        logger.info(beacon.toString());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Beacon> list() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from Beacon").list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Beacon> list(int start, int length, List<ColumnDirection> sortColumns, String search) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Beacon.class);
        if (start > 0) {
            criteria.setFirstResult(start);
        }
        if (length > 0) {
            criteria.setMaxResults(length);
        }
        if (search != null && !search.isEmpty()) {
            criteria.add(Restrictions.like("uuid", "%" + search + "%"));
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
    public long count() {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Beacon.class);
        return (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public long count(String search) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Beacon.class);
        if (search != null && !search.isEmpty()) {
            criteria.add(Restrictions.like("uuid", "%" + search + "%"));
        }
        return (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public Beacon getBayUuidMajorMinor(Beacon beacon) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Beacon.class);
        Beacon beacon2 = (Beacon) criteria
                .add(Restrictions.eq("uuid", beacon.getUuid()))
                .add(Restrictions.eq("major", beacon.getMajor()))
                .add(Restrictions.eq("minor", beacon.getMinor()))
                .uniqueResult();
        return beacon2;
    }

    @Override
    @Transactional(readOnly = true)
    public Beacon getBayUuidMajorMinor(String uuid, int major, int minor) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Beacon.class);
        Beacon beacon2 = (Beacon) criteria
                .add(Restrictions.eq("uuid", uuid))
                .add(Restrictions.eq("major", major))
                .add(Restrictions.eq("minor", minor))
                .uniqueResult();
        return beacon2;
    }

    @Override
    @Transactional(readOnly = true)
    public Beacon get(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Beacon) session.get(Beacon.class, id);
    }

    @Override
    @Transactional
    public void delete(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Beacon beacon = (Beacon) session.load(Beacon.class, id);
        if (beacon != null) {
            session.delete(beacon);
        }
    }
}
