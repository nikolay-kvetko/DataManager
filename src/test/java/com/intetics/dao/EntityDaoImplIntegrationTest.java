package com.intetics.dao;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
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

import static org.junit.Assert.assertFalse;

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
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "EntityDaoImplIntegrationTest.testGetEntityList.xml")
    public void testGetEntityList() throws Exception {
        List<EntityName> actual = entityDao.getEntityList();
        assertFalse(actual.isEmpty());
    }
}