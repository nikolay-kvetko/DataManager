package com.intetics.dao;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.intetics.bean.Role;
import com.intetics.bean.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserDaoImplIntegrationTest extends AbstractDaoImplIntegrationTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/UserDaoImplIntegrationTest.testGetUserById.setup.xml")
    public void testGetUserById() throws Exception {

        User user = userDao.getUserById(1L);
        assertTrue("Admin".equals(user.getRole().getName()));
        assertTrue("Anton".equals(user.getFirstName()));
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/UserDaoImplIntegrationTest.testGetRoleByName.setup.xml")
    public void testGetRoleByName() throws Exception {

        Role role = roleDao.getRoleByName("Admin");

        assertNotNull(role);
        assertTrue(role.getUsers().size() == 2);
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/UserDaoImplIntegrationTest.testSaveOrUpdateUser.setup.xml")
    @ExpectedDatabase(value = "/UserDaoImplIntegrationTest.testSaveOrUpdateUser.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testSaveOrUpdateUser() throws Exception {

        Role role = roleDao.getRoleByName("Admin");

        User user = new User();
        user.setFirstName("Anton");
        user.setLastName("Chervyakovsky");
        user.setRole(role);

        userDao.saveOrUpdate(user);
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/UserDaoImplIntegrationTest.testGetUserByEmail.setup.xml")
    public void testGetUserByEmail() throws Exception {

        User user = userDao.getUserByEmail("anton@tut.by");
        assertTrue("Admin".equals(user.getRole().getName()));
        assertTrue("Anton".equals(user.getFirstName()));
    }

    /*@Test
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

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/EntitySchemaDaoImplIntegrationTest.testGetFieldValuesByField.setup.xml")
    public void testGetFieldValuesByField() throws Exception{

        Field field = entitySchemaDao.getField(1L);

        assertTrue("size must be 4", field.getFieldValues().size() == 4);

    }*/
}