/**
 * If you really care for the license, look for the LICENSE.txt at the project root. Frankly, I 
 * really don't care.
 **/
package me.shopian.shopian3.dao;


import me.shopian.shopian3.entity.User;

public interface UserDao {

    /**
     * Returns the user object matched by the passed username. Returns null if the user is not 
     * found in the database.
     * @param username username of the user which needs to query for
     * @return an instance of User if the user is found, null otherwise
     **/
    public User findUserByUsername(String username);
}