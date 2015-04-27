package com.intetics.dao;

import com.intetics.bean.Company;

/**
 * Operates over {@link com.intetics.bean.Company}
 */

public interface CompanyDao {

    Company getCompanyById(Long id);

    void saveOrUpdate(Company company);

    void delete(Company company);
}
