package com.intetics.dao;

import com.intetics.bean.EntityName;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
public class EntityDaoImpl implements EntityDao {
    protected static Logger LOGGER = LoggerFactory.getLogger(EntityDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<EntityName> getEntityList() {
        LOGGER.trace("Retrieving all persons");

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM EntityName");
        // Retrieve all
        return query.list();
    };

    @Override
    public EntityName getEntity(Long id) {
        LOGGER.trace("Retrieving one person");

        Session session = sessionFactory.getCurrentSession();

        EntityName entityName;
        entityName = (EntityName) session.get(EntityName.class, id);

        // Retrieve all
        return entityName;
    };
}
