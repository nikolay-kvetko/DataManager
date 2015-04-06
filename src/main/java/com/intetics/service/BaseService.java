package com.intetics.service;

import com.intetics.dao.BaseDao;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BaseService<T>
{
    @Autowired
    private BaseDao<T> baseDao;

    public boolean saveOrUpdate(T t)
    {
        if (t!=null) {
            try {
                baseDao.saveOrUpdate(t);
                return true;
            } catch (HibernateException e) {
                System.err.println(e);
                return false;
            }
        }
        else {
            System.err.println(NullPointerException.class);
            return false;
        }
    }

    @Transactional(readOnly = true)
    public T get(Class<T> classes, Long id)
    {
        T t = null;

        if (id!=null) {
            try {
                t = baseDao.get(classes, id);

            } catch (HibernateException e) {
                System.err.println(e);
            }

        } else {System.err.println(NullPointerException.class);}

        return t;
    }

    public boolean delete (T t)
    {
        if (t!=null) {
            try {
                baseDao.delete(t);
                return true;
            } catch (HibernateException e) {
                System.err.println(e);
                return false;
            }
        }
        else {System.err.println(NullPointerException.class);return false;}
    }

    public List<T> getAll(Class aClass)
    {
        List<T> result;

        try {
            result = baseDao.getAll(aClass);
            return result;

        } catch (HibernateException e) {
            System.err.println(e);
        }

        return null;
    }
}
