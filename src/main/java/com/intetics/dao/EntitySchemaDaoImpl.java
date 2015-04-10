package com.intetics.dao;

import com.intetics.bean.EntitySchema;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * @see EntitySchemaDao
 */
@Repository
@Transactional
public class EntitySchemaDaoImpl implements EntitySchemaDao {

    protected static Logger LOGGER = LoggerFactory.getLogger(EntitySchemaDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<EntitySchema> getEntitySchemaList() {
        LOGGER.trace("Retrieving list of entity schemas");

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();
        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM EntitySchema");
        // Retrieve all
        return query.list();
    };

    @Override
    @Nullable
    public EntitySchema getEntitySchema(@Nonnull Long id) {
        Assert.notNull(id);

        LOGGER.trace("Retrieving one person");

        Session session = sessionFactory.getCurrentSession();

        EntitySchema entitySchema;
        entitySchema = (EntitySchema) session.get(EntitySchema.class, id);

        return entitySchema;
    }

    @Override
    public void saveOrUpdate(EntitySchema entitySchema) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entitySchema);
    }

    @Override
    public void delete(EntitySchema entitySchema) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(entitySchema);
    }

}
