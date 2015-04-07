package com.intetics.dao;

import com.intetics.bean.EntityName;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Кузнец on 01.04.2015.
 */

@Repository
public class EntityNameDao extends BaseDao<EntityName> {

    @Autowired
    public EntityNameDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
