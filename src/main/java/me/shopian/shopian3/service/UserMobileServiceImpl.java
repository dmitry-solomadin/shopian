package me.shopian.shopian3.service;

import me.shopian.shopian3.dao.UserMobileDaoImpl;
import me.shopian.shopian3.entity.UserMobile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserMobileServiceImpl implements UserMobileService {
    private static Logger logger = LoggerFactory.getLogger(UserMobileServiceImpl.class);

    @Qualifier("userMobileDaoImpl")
    @Autowired
    private UserMobileDaoImpl userMobile;


    @Override
    public void add(UserMobile userMobile) {
        this.userMobile.add(userMobile);
    }

    @Override
    public UserMobile get(long id) {
        return this.userMobile.get(id);
    }

    @Override
    public UserMobile get(String token) {
        return this.userMobile.get(token);
    }

    @Override
    public void delete(long id) {
        this.userMobile.delete(id);
    }
}
