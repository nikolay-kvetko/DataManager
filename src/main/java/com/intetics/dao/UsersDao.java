package com.intetics.dao;

import com.intetics.bean.Users;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Кузнец on 01.04.2015.
 */

@Repository
public class UsersDao extends BaseDao<Users> {

    @Autowired
    public UsersDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
