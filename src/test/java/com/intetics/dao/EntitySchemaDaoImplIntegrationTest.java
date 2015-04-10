package com.intetics.dao;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.intetics.bean.EntitySchema;
import org.hibernate.Session;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class EntitySchemaDaoImplIntegrationTest extends AbstractDaoImplIntegrationTest {

    @Autowired
    private EntitySchemaDao entitySchemaDao;

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
    @ExpectedDatabase(value = "/EntitySchemaDaoImplIntegrationTest.testDeleteEntitySchema.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testDeleteEntitySchema() throws Exception {
        Session currentSession = sessionFactory.getCurrentSession();
        EntitySchema entitySchema = (EntitySchema) currentSession.load(EntitySchema.class, 1L);
        entitySchemaDao.delete(entitySchema);
        // in order to force hibernate to flush changes
        currentSession.flush();
    }
}