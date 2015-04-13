package com.intetics.dao;

import com.intetics.bean.EntityInstance;
import com.intetics.bean.EntitySchema;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public List<EntityInstance> getEntityInstancesByEntitySchema (EntitySchema entitySchema) {
        Session session = sessionFactory.getCurrentSession();
        List<EntityInstance> entityInstances;
        Criteria criteria = session.createCriteria(EntityInstance.class);
        criteria.add(Restrictions.eq("entitySchema", entitySchema));
        entityInstances = criteria.list();

        return entityInstances;
    }
}
