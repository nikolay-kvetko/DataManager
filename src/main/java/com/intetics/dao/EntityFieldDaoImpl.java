package com.intetics.dao;

import com.intetics.bean.Field;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by s.berzhynsky on 10.04.2015.
 */
@Repository
@Transactional
public class EntityFieldDaoImpl implements EntityFieldDao {
    protected static Logger LOGGER = LoggerFactory.getLogger(EntityFieldDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private EntitySchemaDao entitySchemaDao;

    @Override
    public List<Field> getEntityFieldList(Long entitySchemaId) {
        LOGGER.trace("Retrieving list of entity {} fields", entitySchemaId);

        return entitySchemaDao.getEntitySchema(entitySchemaId).getFields();
    }

    @Override
    public Field getField(long fieldId) {
        Assert.notNull(fieldId);

        LOGGER.trace("Retrieving entity field by id {}", fieldId);

        Session session = sessionFactory.getCurrentSession();
        Field field = (Field) session.get(Field.class, fieldId);

        return field;
    }
}
