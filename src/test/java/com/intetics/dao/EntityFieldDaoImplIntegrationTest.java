package com.intetics.dao;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.intetics.bean.EntitySchema;
import com.intetics.bean.Field;
import com.intetics.bean.TextField;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class EntityFieldDaoImplIntegrationTest extends AbstractDaoImplIntegrationTest {

    @Autowired
    private EntityFieldDao entityFieldDao;

    @Autowired
    private EntitySchemaDao entitySchemaDao;


    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/EntityFieldDaoImplIntegrationTest.testGetEntityFieldList.setup.xml")
    public void testGetEntityFieldList() throws Exception {
        List<Field> actual = entityFieldDao.getEntityFieldList(1L);
        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        assertEquals("First Name", actual.get(0).getName());
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/EntityFieldDaoImplIntegrationTest.testGetEntityField.setup.xml")
    public void testGetEntityField() throws Exception {
        Field actual = entityFieldDao.getField(1L);
        assertNotNull(actual);
        assertEquals("First Name", actual.getName());
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/EntityFieldDaoImplIntegrationTest.testSaveOrUpdateField.setup.xml")
    @ExpectedDatabase(value = "/EntityFieldDaoImplIntegrationTest.testSaveOrUpdateField.expected.xml",
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
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/EntityFieldDaoImplIntegrationTest.testDeleteField.setup.xml")
    @ExpectedDatabase(value = "/EntityFieldDaoImplIntegrationTest.testDeleteField.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testDeleteField() throws Exception {
        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(1L);
        Field field = entityFieldDao.getField(1L);
        entitySchema.getFields().remove(field);
        entitySchemaDao.saveOrUpdate(entitySchema);
        sessionFactory.getCurrentSession().flush();
    }
}