/**
 * If you really care for the license, look for the LICENSE.txt at the project root. Frankly, I 
 * really don't care.
 **/
package me.shopian.shopian3.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import me.shopian.shopian3.dao.UserDaoImpl;
import me.shopian.shopian3.entity.Role;
import me.shopian.shopian3.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {
    


    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Qualifier("userDao")
    @Autowired
    private UserDaoImpl userDao;

    /**
     * Implementation of UserDetailsService interface's loadUserByUsername method. The returned 
     * User object facilitate spring security to validate user credentials and decide on access 
     * to resources.
     * @param username username of the logged in user
     * @return Spring security user object for the logged in user
     */
    @Transactional
    public org.springframework.security.core.userdetails.User loadUserByUsername(String username) {
        logger.info("Spring security loading user details for user: " + username);
        User user = userDao.findUserByUsername(username);
        System.out.println("user = " + user);
        Collection<Role> roles = user.getRoles();
        System.out.println("roles = " + roles);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        Iterator<Role> iterator = roles.iterator();
        while(iterator.hasNext()) {
            authorities.add(new SimpleGrantedAuthority(iterator.next().getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
    }
    
    /**
     * This method returns the User entity object of the session's signed in user
     * @return User entity object of the current session's signed in user
     */
    public User getCurrentUser() {
        return userDao.findUserByUsername(
                ((org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().
                        getAuthentication().getPrincipal()).getUsername());
    }
}
