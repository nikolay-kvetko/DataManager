package com.intetics.dao;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.intetics.bean.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

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
}