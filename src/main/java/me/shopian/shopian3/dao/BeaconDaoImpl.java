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
public class BeaconDaoImpl implements BeaconDao {
    private SessionFactory sessionFactory;


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private static Logger logger = LoggerFactory.getLogger(BeaconDaoImpl.class);

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
        if (length>0){
            criteria.setMaxResults(length);
        }
        if (search!=null&&!search.isEmpty()){
            criteria.add(Restrictions.like("uuid", "%" + search +"%"));
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
        return (long) session.createQuery("select count(*) from Beacon").uniqueResult();
//        List<Person> personsList =
//                this.sessionFactory.getCurrentSession().createQuery("from Person").list().size()
//        return 0;
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
