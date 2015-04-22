package com.intetics.dao;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.intetics.bean.Company;
import org.hibernate.Session;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

public class CompanyDaoImplIntegrationTest extends AbstractDaoImplIntegrationTest {

    @Autowired
    private CompanyDao companyDao;

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/CompanyDaoImplIntegrationTest.testGetCompanyById.setup.xml")
    public void testGetCompanyById() throws Exception {

        Company company = companyDao.getCompanyById(1L);
        assertTrue("Intetics".equals(company.getName()));
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/CompanyDaoImplIntegrationTest.testSaveOrUpdateCompany.setup.xml")
    @ExpectedDatabase(value = "/CompanyDaoImplIntegrationTest.testSaveOrUpdateCompany.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testSaveOrUpdateUserCompany() throws Exception {

        Company company = new Company();
        company.setName("Intetics");
        company.setAddress("Minsk");

        companyDao.saveOrUpdate(company);
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/CompanyDaoImplIntegrationTest.testDeleteCompany.setup.xml")
    @ExpectedDatabase(value = "/CompanyDaoImplIntegrationTest.testDeleteCompany.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testDeleteCompany() throws Exception {

        Session currentSession = sessionFactory.getCurrentSession();
        Company company  = (Company) currentSession.get(Company.class, 1L);

        companyDao.delete(company);
        currentSession.flush();
    }
}