package me.shopian.shopian3.dao;

import me.shopian.shopian3.entity.Beacon;
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

import java.util.List;

@Repository
@Transactional
public class BeaconDaoImpl implements BeaconDao {
    private SessionFactory sessionFactory;


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private static Logger logger = LoggerFactory.getLogger(BeaconDaoImpl.class);

    @Override
    public void add(Beacon beacon) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(beacon);
        logger.info(beacon.toString());
    }

    @Override
    public void update(Beacon beacon) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(beacon);
        logger.info(beacon.toString());
    }

    @Override
    public List<Beacon> list() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from Beacon").list();
    }

    @Override
    public List<Beacon> list(int start, int length, List<ColumnDirection> sortColumns, String search) {
        Session session = this.sessionFactory.getCurrentSession();
//        Query query = session.createQuery("from Beacon");
        Criteria criteria = session.createCriteria(Beacon.class);
        criteria.addQueryHint("from Beacon")
                .setFirstResult(start)
                .setMaxResults(length);

        if (sortColumns!=null) for (ColumnDirection cd : sortColumns) {
            if (cd.isDesc()) {
                criteria.addOrder(Order.desc(cd.getColumn()));
            } else {
                criteria.addOrder(Order.asc(cd.getColumn()));
            }
        }
        System.out.println("criteria2 = " + criteria);
//        query.setFirstResult(start);
//        query.setMaxResults(length);
//        return session.createQuery("from Beacon").list();

        return criteria.list();
    }

    @Override
    public long count() {
        Session session = this.sessionFactory.getCurrentSession();
        return (long) session.createQuery("select count(*) from Beacon").uniqueResult();
//        List<Person> personsList =
//                this.sessionFactory.getCurrentSession().createQuery("from Person").list().size()
//        return 0;
    }

    @Override
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
    public Beacon get(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Beacon beacon = (Beacon) session.load(Beacon.class, id);
        logger.info(beacon.toString());
        return beacon;
    }

    @Override
    public void delete(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Beacon beacon = (Beacon) session.load(Beacon.class, id);
        logger.info(beacon.toString());
        if (beacon != null) {
            session.delete(beacon);
        }
        logger.info(beacon.toString());
    }
}
