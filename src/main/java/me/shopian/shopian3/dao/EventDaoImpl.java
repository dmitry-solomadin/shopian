package me.shopian.shopian3.dao;

import me.shopian.shopian3.entity.Event;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EventDaoImpl implements EventDao {
    private static Logger logger = LoggerFactory.getLogger(EventDaoImpl.class);

    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)

    public Event get(String title) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Event) session.get(Event.class, title);
    }
}
