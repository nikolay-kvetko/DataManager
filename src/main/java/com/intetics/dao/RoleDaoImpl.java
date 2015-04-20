package com.intetics.dao;

import com.intetics.bean.Role;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * todo[a.chervyakovsky] place meaningful javadoc here
 */

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role getRoleByName(String roleName) {
        Session session = sessionFactory.getCurrentSession();

        Role role;

        Criteria criteria = session.createCriteria(Role.class);
        criteria.add(Restrictions.eq("name", roleName));
        role = (Role) criteria.uniqueResult();

        return role;
    }
}
