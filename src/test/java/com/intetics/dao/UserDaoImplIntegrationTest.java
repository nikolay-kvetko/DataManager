package com.intetics.dao;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.intetics.bean.Role;
import com.intetics.bean.User;
import org.hibernate.Session;
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

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/UserDaoImplIntegrationTest.testDeleteUser.setup.xml")
    @ExpectedDatabase(value = "/UserDaoImplIntegrationTest.testDeleteUser.expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testDeleteUser() throws Exception {

        Session currentSession = sessionFactory.getCurrentSession();
        User user  = (User) currentSession.get(User.class, 1L);

        userDao.delete(user);
        currentSession.flush();
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/UserDaoImplIntegrationTest.testGetRoleByName.setup.xml")
    public void testGetRoleByName() throws Exception {

        Role role = roleDao.getRoleByName("Admin");

        assertNotNull(role);
        assertTrue(role.getUsers().size() == 2);
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/UserDaoImplIntegrationTest.testGetUserByConfirmingURL.setup.xml")
    public void testGetUserByConfirmingURL() throws Exception {

        User user = userDao.getUserByConfirmingURL("1234_a123");
        assertTrue("Admin".equals(user.getRole().getName()));
        assertTrue("Anton".equals(user.getFirstName()));
    }
}