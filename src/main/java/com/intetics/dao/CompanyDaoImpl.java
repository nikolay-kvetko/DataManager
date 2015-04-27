package com.intetics.dao;

import com.intetics.bean.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;

/**
 * @see com.intetics.dao.CompanyDao
 */

@Repository
@Transactional
public class CompanyDaoImpl implements CompanyDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Company getCompanyById(@Nonnull Long id) {
        Assert.notNull(id);

        Session session = sessionFactory.getCurrentSession();

        Company company;
        company = (Company) session.get(Company.class, id);

        return company;
    }

    @Override
    public void saveOrUpdate(Company company) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(company);
    }

    @Override
    public void delete(Company company) {

        Session session = sessionFactory.getCurrentSession();
        session.delete(company);
    }
}
