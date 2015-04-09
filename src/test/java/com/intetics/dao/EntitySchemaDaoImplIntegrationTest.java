package com.intetics.dao;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.intetics.bean.EntitySchema;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
public class EntitySchemaDaoImplIntegrationTest {

    @Autowired
    private EntitySchemaDao entitySchemaDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/EntitySchemaDaoImplIntegrationTest.testGetEntitySchemaList.setup.xml")
    public void testGetEntitySchemaList() throws Exception {

        List<EntitySchema> actual = entitySchemaDao.getEntitySchemaList();
        assertTrue(actual.size() == 4);
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/EntitySchemaDaoImplIntegrationTest.testGetEntitySchema.setup.xml")
    public void testGetEntitySchema() throws Exception {

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(1L);
        assertNotNull(entitySchema);
        assertTrue("TEST1".equals(entitySchema.getName()));
    }

    @Test
    @ExpectedDatabase(value = "/EntitySchemaDaoImplIntegrationTest.testSaveOrUpdateEntitySchema.setup.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testSaveOrUpdateEntity() throws Exception {

        EntitySchema entitySchema = new EntitySchema();
        entitySchema.setName("TEST");
        entitySchemaDao.saveOrUpdate(entitySchema);
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/EntitySchemaDaoImplIntegrationTest.testDeleteEntitySchema.setup.xml")
    @ExpectedDatabase(value = "/EntitySchemaDaoImplIntegrationTest.testDeleteEntitySchema.expected.xml")
    public void testDeleteEntitySchema() throws Exception {
        Session currentSession = sessionFactory.getCurrentSession();
        EntitySchema entitySchema = (EntitySchema) currentSession.load(EntitySchema.class, 1L);
        entitySchemaDao.delete(entitySchema);
        // in order to force hibernate to flush changes
        currentSession.flush();
    }
}