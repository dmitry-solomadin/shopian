/**
 * If you really care for the license, look for the LICENSE.txt at the project root. Frankly, I 
 * really don't care.
 **/
package me.shopian.shopian3.dao;

import me.shopian.shopian3.entity.Role;
import me.shopian.shopian3.entity.Shop;
import me.shopian.shopian3.entity.User;
import me.shopian.shopian3.util.ColumnDirection;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


@Repository("userDao")
public class UserDaoImpl implements UserDao {

    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public User findUserByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.createQuery("from User user where user.username=?")
                .setParameter(0, username)
                .uniqueResult();

        if (logger.isDebugEnabled()) {
            if (user == null) {
                logger.trace("User not found: " + username);
            } else {
                logger.trace("User found: " + username);
                Iterator<Role> roleIterator = user.getRoles().iterator();
                while (roleIterator.hasNext()) {
                    Role role = roleIterator.next();
                    logger.trace("\tUser role: " + role.getRoleName());
                }
            }
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<User> list(String roleName) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        List<User> res= new ArrayList<>();
        if (roleName!=null&&!roleName.isEmpty()){
            Role role = (Role) this.sessionFactory.getCurrentSession().get(Role.class,roleName);

            for(User user: (List<User>)criteria.list()) {
                Hibernate.initialize(user.getRoles());
                if (role!=null &&  user.getRoles().contains(role)){
                    res.add(user);
                }
            }
        }
        return res;
    }
}
