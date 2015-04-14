package com.intetics.dao;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.intetics.bean.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class EntityInstanceDaoImplIntegrationTest extends AbstractDaoImplIntegrationTest {
    @Autowired
    private EntityInstanceDao entityInstanceDao;

    @Autowired
    private EntitySchemaDao entitySchemaDao;

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/EntityInstanceDaoImplIntegrationTest.testSaveOrUpdateEntityInstance.setup.xml")
    @ExpectedDatabase(value = "/EntityInstanceDaoImplIntegrationTest.testSaveOrUpdateEntityInstance.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testSaveOrUpdateEntityInstance() throws Exception {
        EntityInstance entityInstance = new EntityInstance();
        entityInstance.setEntitySchema(entitySchemaDao.getEntitySchema(1L));

        entityInstanceDao.saveOrUpdate(entityInstance);
        sessionFactory.getCurrentSession().flush();
    }


    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/EntityInstanceDaoImplIntegrationTest.testSaveOrUpdateEntityInstanceWithValues.setup.xml")
    @ExpectedDatabase(value = "/EntityInstanceDaoImplIntegrationTest.testSaveOrUpdateEntityInstanceWithValues.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testSaveOrUpdateEntityInstanceWithValues() throws Exception {
        EntityInstance entityInstance = new EntityInstance();

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(1L);
        Field field = entitySchema.getFields().get(0);

        entityInstance.setEntitySchema(entitySchema);
        StringValue value = new StringValue();
        value.setValue("Anton");
        value.setField(field);
        entityInstance.setValues(Arrays.<FieldValue>asList(value));

        entityInstanceDao.saveOrUpdate(entityInstance);
        sessionFactory.getCurrentSession().flush();
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/EntityInstanceDaoImplIntegrationTest.testSaveOrUpdateEntityInstanceWithMultiChoiceValues.setup.xml")
    @ExpectedDatabase(value = "/EntityInstanceDaoImplIntegrationTest.testSaveOrUpdateEntityInstanceWithMultiChoiceValue.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testSaveOrUpdateEntityInstanceWithMultiChoiceValue() throws Exception {
        EntityInstance entityInstance = new EntityInstance();

        EntitySchema entitySchema = entitySchemaDao.getEntitySchema(1L);
        Field field = entitySchema.getFields().get(0);
        List<Choice> choiceList = ((MultiChoiceField)field).getChoices();

        entityInstance.setEntitySchema(entitySchema);
        MultiChoiceValue value = new MultiChoiceValue();

        value.setChoices(choiceList.subList(0, 2));
        value.setField(field);
        entityInstance.setValues(Arrays.<FieldValue>asList(value));

        entityInstanceDao.saveOrUpdate(entityInstance);
        sessionFactory.getCurrentSession().flush();
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/EntityInstanceDaoImplIntegrationTest.testGetEntityInstancesByEntitySchema.setup.xml")
    public void testGetEntityInstancesByEntitySchema() throws Exception {

        List<EntityInstance> entityInstances = entityInstanceDao.getEntityInstancesByEntitySchema(entitySchemaDao.getEntitySchema(1L));

        assertTrue("entityInstances size must be 3", entityInstances.size() == 3);
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/EntityInstanceDaoImplIntegrationTest.testDeleteEntityInstance.setup.xml")
    @ExpectedDatabase(value = "/EntityInstanceDaoImplIntegrationTest.testDeleteEntityInstance.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testDeleteEntityInstance() throws Exception {
        List<EntityInstance> entityInstances = entityInstanceDao.getEntityInstancesByEntitySchema(entitySchemaDao.getEntitySchema(1L));
        EntityInstance entityInstance = entityInstances.get(0);

        entityInstanceDao.delete(entityInstance);
        sessionFactory.getCurrentSession().flush();
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/EntitySchemaDaoImplIntegrationTest.testGetEntityInstance.setup.xml")
    public void testGetEntityInstance() throws Exception {

        EntityInstance entityInstance = entityInstanceDao.getEntityInstance(1L);
        assertNotNull(entityInstance);
        assertTrue("TEST".equals(entityInstance.getEntitySchema().getName()));
    }
}