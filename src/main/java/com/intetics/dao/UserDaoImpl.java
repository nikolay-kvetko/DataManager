package com.intetics.dao;

import com.intetics.bean.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;

/**
 * todo[a.chervyakovsky] place meaningful javadoc here
 */

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getUser(@Nonnull Long id) {
        Assert.notNull(id);

        Session session = sessionFactory.getCurrentSession();

        User user = (User) session.get(User.class, id);

        return user;
    }

    @Override
    public void saveOrUpdate(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }
}
