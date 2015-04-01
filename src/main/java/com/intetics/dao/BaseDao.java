package com.intetics.dao;

import com.intetics.util.HibernateUtil;
import org.hibernate.Session;

import java.lang.reflect.ParameterizedType;

/**
 * Base class of CRUD operations
 */
abstract class BaseDao<T> implements Dao<T> {

    private final Session session = HibernateUtil.getHibernateUtil().getSession();

    /**
     * Save Entity in database, if id don't exist.
     * Else make update Entity
     * @param t
     */
    @Override
    public void saveOrUpdate(T t) {

        session.saveOrUpdate(t);
    }

    /**
     * Get Entity from database
     * @param id
     * @return Entity
     */
    @Override
    public T get(Class aClass, Long id) {

        T t = null;
        t = (T) session.get(aClass, id);
        return t;
    }


    /**
     * Delete Entity from database
     * @param t
     */
    @Override
    public void delete(T t) {

        session.delete(t);
    }
}