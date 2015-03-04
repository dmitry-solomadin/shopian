package me.shopian.shopian3.service;

import me.shopian.shopian3.dao.EventDaoImpl;
import me.shopian.shopian3.entity.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventServiceImpl implements EventService {
    private static Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);
    @Qualifier("eventDaoImpl")
    @Autowired
    private EventDaoImpl eventDao;

    @Override
    public Event get(String title) {
        return eventDao.get(title);
    }
}
