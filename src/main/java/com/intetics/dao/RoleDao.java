package com.intetics.dao;

import com.intetics.bean.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Кузнец on 01.04.2015.
 */

@Repository
public class RoleDao extends BaseDao<Role> {

    @Autowired
    public RoleDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
