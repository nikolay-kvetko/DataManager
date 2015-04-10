package com.intetics.dao;

import com.intetics.bean.EntityInstance;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * todo[a.chervyakovsky] place meaningful javadoc here
 */
@Repository
@Transactional
public class EntityInstanceDaoImpl implements EntityInstanceDao {
    protected static Logger LOGGER = LoggerFactory.getLogger(EntityInstanceDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveOrUpdate(EntityInstance entityInstance) {
        sessionFactory.getCurrentSession().saveOrUpdate(entityInstance);
    }
}
