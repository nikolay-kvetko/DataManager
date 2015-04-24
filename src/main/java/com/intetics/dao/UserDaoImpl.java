package com.intetics.dao;

import com.intetics.bean.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
    public User getUserById(@Nonnull Long id) {
        Assert.notNull(id);

        Session session = sessionFactory.getCurrentSession();

        User user;
        user = (User) session.get(User.class, id);

        return user;
    }

    @Override
    public void saveOrUpdate(User user) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }

    @Override
    public User getUserByEmail(String email) {

        Session session = sessionFactory.getCurrentSession();

        User user;
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("email", email));
        user = (User) criteria.uniqueResult();

        return user;
    }

    @Override
    public void delete(User user) {

        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }

    @Override
    public User getUserByConfirmingURL(String confirmingURL) {

        Session session = sessionFactory.getCurrentSession();

        User user;
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("confirmingURL", confirmingURL));
        user = (User) criteria.uniqueResult();

        return user;
    }
}
