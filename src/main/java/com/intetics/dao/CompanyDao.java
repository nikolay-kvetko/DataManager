package com.intetics.dao;

import com.intetics.pojos.Company;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Кузнец on 01.04.2015.
 */

@Repository
public class CompanyDao extends BaseDao<Company> {

    @Autowired
    public CompanyDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
