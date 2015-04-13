package com.intetics.dao;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.intetics.bean.EntitySchema;
import com.intetics.bean.Field;
import com.intetics.bean.TextField;
import org.hibernate.Session;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/EntitySchemaDaoImplIntegrationTest.testGetEntityFieldList.setup.xml")
    public void testGetEntityFieldList() throws Exception {
        List<Field> actual = entitySchemaDao.getEntityFieldList(1L);
        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        assertEquals("First Name", actual.get(0).getName());
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/EntitySchemaDaoImplIntegrationTest.testGetEntityField.setup.xml")
    public void testGetEntityField() throws Exception {
        Field actual = entitySchemaDao.getField(1L);
        assertNotNull(actual);
        assertEquals("First Name", actual.getName());
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/EntitySchemaDaoImplIntegrationTest.testSaveOrUpdateField.setup.xml")
    @ExpectedDatabase(value = "/EntitySchemaDaoImplIntegrationTest.testSaveOrUpdateField.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testSaveOrUpdateField() throws Exception {
        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(1L);
        Field newField = new TextField();
        newField.setName("Last Name");
        entitySchema.getFields().add(newField);
        entitySchemaDao.saveOrUpdate(entitySchema);
        sessionFactory.getCurrentSession().flush();
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/EntitySchemaDaoImplIntegrationTest.testDeleteField.setup.xml")
    @ExpectedDatabase(value = "/EntitySchemaDaoImplIntegrationTest.testDeleteField.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testDeleteField() throws Exception {
        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(1L);
        Field field = entitySchemaDao.getField(1L);
        entitySchema.getFields().remove(field);
        entitySchemaDao.saveOrUpdate(entitySchema);
        sessionFactory.getCurrentSession().flush();
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/EntitySchemaDaoImplIntegrationTest.testEditField.setup.xml")
    @ExpectedDatabase(value = "/EntitySchemaDaoImplIntegrationTest.testEditField.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testEditField() throws Exception {
        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(1L);
        Field field = entitySchemaDao.getField(1L);
        field.setName("First Name");
        entitySchemaDao.saveOrUpdate(entitySchema);
        sessionFactory.getCurrentSession().flush();
    }
}