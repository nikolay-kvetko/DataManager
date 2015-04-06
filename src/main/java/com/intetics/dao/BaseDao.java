package com.intetics.dao;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Base Dao class with crud operations
 */
@Repository
public class BaseDao<T>
{

    private SessionFactory sessionFactory;

    @Autowired
    public BaseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session currentSession()
    {
        Session session = null;

        try {
            session = sessionFactory.getCurrentSession();

        } catch(HibernateException ex) {
            System.err.println(ex);
        }

        if (session == null) {
            session = sessionFactory.openSession();
            session.setFlushMode(FlushMode.MANUAL);
        }

        return session;
    }

    public void saveOrUpdate(T t)
    {
        try {
            Session session = currentSession();
            session.saveOrUpdate(t);

        } catch(HibernateException e) {
            System.err.println(e.fillInStackTrace());
        }
    }

    public T get(Class aClass, Long id)
    {
        T t = null;

        try {
            Session session = currentSession();
            t = (T) session.get(aClass, id);

        } catch(HibernateException e){
            System.err.println(e);
        }

        return t;
    }


    public void delete(T t)
    {
        try {
            Session session = currentSession();
            session.delete(t);

        } catch(HibernateException e){
            System.err.println(e);
        }
    }

    public List<T> getAll(Class aClass)
    {
        List<T> result = null;

        try {
            Session session = currentSession();
            Criteria criteria = session.createCriteria(aClass);
            result = criteria.list();

        } catch (HibernateException e) {
            System.err.println(e);
        }

        return result;
    }
}
