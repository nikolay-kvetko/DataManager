package com.intetics.dao;

import com.intetics.bean.Role;
import com.intetics.bean.User;

import java.util.List;

/**
 * Operates over {@link com.intetics.bean.User}
 */

public interface UserDao {

    User getUserById(Long id);

    void saveOrUpdate(User user);

    User getUserByEmail(String email);

    void delete(User user);

    User getUserByConfirmingURL(String confirmingURL);

    List<User> getUsersExcludingAdmin(Role role);
}
