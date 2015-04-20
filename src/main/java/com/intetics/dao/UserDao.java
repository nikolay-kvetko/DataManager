package com.intetics.dao;

import com.intetics.bean.User;

/**
 * todo[a.chervyakovsky] place meaningful javadoc here
 */

public interface UserDao {

    User getUserById(Long id);

    void saveOrUpdate(User user);

    User getUserByEmail(String email);
}
