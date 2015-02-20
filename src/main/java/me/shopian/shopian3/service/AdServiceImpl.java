package me.shopian.shopian3.service;

import me.shopian.shopian3.dao.AdDaoImpl;
import me.shopian.shopian3.entity.Ad;
import me.shopian.shopian3.entity.User;
import me.shopian.shopian3.util.ColumnDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdServiceImpl implements AdService {


    @Qualifier("adDaoImpl")
    @Autowired
    private AdDaoImpl adDao;

    @Override
    public void add(Ad ad) {
        adDao.add(ad);
    }

    @Override
    public void update(Ad ad) {
        adDao.update(ad);
    }

    @Override
    public List<Ad> list(User user) {
        return adDao.list(user);
    }

    @Override
    public List<Ad> list(User user, int start, int length, List<ColumnDirection> sortColumns, String search) {
        return adDao.list(user,start,length,sortColumns,search);
    }

    @Override
    public long count(User user) {
        return adDao.count(user);
    }

    @Override
    public long count(User user,String search) {
        return adDao.count(user,search);
    }

    @Override
    public Ad get(long id) {
        return adDao.get(id);
    }

    @Override
    public void delete(long id) {
        adDao.delete(id);
    }
}
