package me.shopian.shopian3.service;

import me.shopian.shopian3.dao.ShopDao;
import me.shopian.shopian3.entity.Department;
import me.shopian.shopian3.entity.Shop;
import me.shopian.shopian3.entity.User;
import me.shopian.shopian3.util.ColumnDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {

    @Qualifier("shopDaoImpl")
    @Autowired
    private ShopDao shopDao;

    @Override
    public void add(Shop shop) {
        shopDao.add(shop);
    }

    @Override
    public void update(Shop shop) {
        shopDao.update(shop);
    }

    @Override
    public List<Shop> list(User user) {
        return shopDao.list(user);
    }

    @Override
    public List<Shop> list(User user, int start, int length, List<ColumnDirection> sortColumns, String search) {
        return shopDao.list(user,start, length, sortColumns, search);
    }

    @Override
    public long count() {
        return shopDao.count();
    }

    @Override
    public Shop get(long id) {
        return shopDao.get(id);
    }

    @Override
    public Shop getByTitle(Shop shop) {
        return shopDao.getByTitle(shop);
    }

    @Override
    public void delete(long id) {
        shopDao.delete(id);
    }

}
