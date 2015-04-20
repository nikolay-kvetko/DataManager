package com.intetics.dao;

import com.intetics.bean.Company;

/**
 * todo[a.chervyakovsky] place meaningful javadoc here
 */

public interface CompanyDao {

    Company getCompanyById(Long id);

    void saveOrUpdate(Company company);

    void delete(Company company);
}
