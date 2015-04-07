package com.intetics.dao;

import com.intetics.bean.EntityInstance;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Кузнец on 01.04.2015.
 */

@Repository
public class EntityInstanceDao extends BaseDao<EntityInstance> {

    @Autowired
    public EntityInstanceDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
