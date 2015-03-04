package me.shopian.shopian3.dao;


import me.shopian.shopian3.entity.UserMobile;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserMobileDaoImpl implements UserMobileDao {
    private static Logger logger = LoggerFactory.getLogger(UserMobileDaoImpl.class);

    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @Transactional
    public void add(UserMobile userMobile) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(userMobile);
    }

    @Override
    @Transactional(readOnly = true)
    public UserMobile get(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (UserMobile) session.get(UserMobile.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserMobile get(String token) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(UserMobile.class);
        return (UserMobile) criteria.add(Restrictions.eq("token", token)).uniqueResult();
    }

    @Override
    @Transactional
    public void delete(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        UserMobile userMobile = (UserMobile) session.load(UserMobile.class, id);
        if (userMobile != null) {
            session.delete(userMobile);
        }
    }
}
