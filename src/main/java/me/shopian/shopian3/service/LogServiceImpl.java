package me.shopian.shopian3.service;

import me.shopian.shopian3.dao.LogDaoImpl;
import me.shopian.shopian3.entity.Log;
import me.shopian.shopian3.entity.User;
import me.shopian.shopian3.util.ColumnDirection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LogServiceImpl implements LogService {
    private static Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);
    @Qualifier("logDaoImpl")
    @Autowired
    private LogDaoImpl logDao;


    @Override
    public void add(Log log) {
        logDao.add(log);
    }

    @Override
    public void update(Log log) {
        logDao.update(log);
    }

    @Override
    public List<Log> list(User user) {
        return logDao.list(user);
    }

    @Override
    public List<Log> list(User user, int start, int length, List<ColumnDirection> sortColumns, String search) {
        return logDao.list(user,start,length,sortColumns,search);
    }

    @Override
    public long count(User user) {
        return logDao.count(user);
    }

    @Override
    public long count(User user, String search) {
        return logDao.count(user,search);
    }

    @Override
    public Log get(long id) {
        return logDao.get(id);
    }

    @Override
    public void delete(long id) {
        logDao.delete(id);
    }
}
