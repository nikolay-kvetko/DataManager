package com.intetics.dao;

import com.intetics.bean.Role;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @see com.intetics.dao.RoleDao
 */

@Repository
@Transactional
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

    @Override
    public List<Role> getRoleNamesExcludingAdmin() {
        Session session = sessionFactory.getCurrentSession();

        List<Role> roles;

        Criteria criteria = session.createCriteria(Role.class);
        criteria.add(Restrictions.ne("name", "Admin"));
        roles = criteria.list();

        return roles;
    }

    @Override
    public void saveOrUpdate(Role role) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(role);
    }
}
