package com.intetics.dao;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.intetics.bean.Field;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class EntityFieldDaoImplIntegrationTest extends AbstractDaoImplIntegrationTest {

    @Autowired
    private EntityFieldDao entityFieldDao;


    @Test
    @DatabaseSetup(type = DatabaseOperation.INSERT, value = "/EntityFieldDaoImplIntegrationTest.testGetEntityFieldList.setup.xml")
    public void testGetEntityFieldList() throws Exception {
        List<Field> actual = entityFieldDao.getEntityFieldList(1L);
        assertFalse(actual.isEmpty());
    }
}