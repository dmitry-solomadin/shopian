package me.shopian.shopian3.dao;

import me.shopian.shopian3.entity.Log;
import me.shopian.shopian3.entity.User;
import me.shopian.shopian3.util.ColumnDirection;

import java.util.List;

public interface LogDao {
    public void add(Log log);
    public void update(Log log);
    public List<Log> list(User user);
    public List<Log> list(User user, int start, int length, List<ColumnDirection> sortColumns, String search);
    public long count(User user);
    public long count(User user, String search);
    public Log get(long id);
    public void delete(long id);
}
