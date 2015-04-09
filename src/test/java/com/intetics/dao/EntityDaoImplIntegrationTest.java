package com.intetics.dao;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.intetics.bean.EntityName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/test-root-context.xml")
@Transactional
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class} )
@DbUnitConfiguration(databaseConnection = "dbUnitDatabaseConnection")
public class EntityDaoImplIntegrationTest {

    @Autowired
    private EntityDao entityDao;

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/EntityDaoImplIntegrationTest.testGetEntityList.xml")
    public void testGetEntityList() throws Exception {
        List<EntityName> actual = entityDao.getEntityList();
        assertTrue(actual.size() == 1);
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/EntityDaoImplIntegrationTest.testGetEntity.xml")
    public void testGetEntity() throws Exception {
        EntityName entityName = entityDao.getEntity(1L);
        //List<EntityName> actual = entityDao.getEntityList();
        assertNotNull(entityName);
    }

    @Test
    @ExpectedDatabase(value = "/EntityDaoImplIntegrationTest.testSaveOrUpdateEntity.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testSaveOrUpdateEntity() throws Exception {
        EntityName entityName = new EntityName();
        entityName.setName("New Entity");
        entityDao.saveOrUpdate(entityName);
        //List<EntityName> actual = entityDao.getEntityList();

    }
}