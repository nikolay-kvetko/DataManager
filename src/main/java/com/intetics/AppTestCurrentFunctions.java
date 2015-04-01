package com.intetics;

import com.intetics.dao.EntityNameDao;
import com.intetics.pojos.EntityName;
import com.intetics.pojos.EntityValue;
import com.intetics.pojos.Field;
import com.intetics.pojos.values.DateValues;
import com.intetics.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;

/**
 * Created by Кузнец on 31.03.2015.
 */

public class AppTestCurrentFunctions {

    public static void main(String[] args) {

        Session session = HibernateUtil.getHibernateUtil().getSession();
        Transaction transaction = session.beginTransaction();

        EntityName entityName = new EntityName();
        EntityName entityName1 = new EntityName();
        EntityName entityName2 = new EntityName();
        Field field1 = new Field();
        Field field2 = new Field();
        Field field3 = new Field();
        EntityValue entityValue1 = new EntityValue();
        EntityValue entityValue2 = new EntityValue();
        DateValues dateValues = new DateValues();

        entityName.setName("Person");
        entityName1.setName("GitHub");
        entityName2.setName("Full Date and Time");

        field1.setName("First Name");
        field1.setType("String");
        field1.setEntityName(entityName);

        field2.setName("Last Name");
        field2.setType("String");
        field2.setEntityName(entityName);

        field3.setName("FullDate");
        field3.setType("Date");
        field3.setEntityName(entityName);

        dateValues.setValue(new java.util.Date());
        dateValues.setField(field3);

        HashSet<DateValues> dateValuesSet = new HashSet<DateValues>();
        dateValuesSet.add(dateValues);

        field3.setDateValues(dateValuesSet);

        HashSet<Field> fieldHashSet = new HashSet<Field>();
        fieldHashSet.add(field1);
        fieldHashSet.add(field2);
        fieldHashSet.add(field3);


        entityValue1.setCreateDate(Date.valueOf(LocalDate.now()));
        entityValue1.setLastModificationDate(Date.valueOf(LocalDate.now()));
        entityValue1.setEntityName(entityName);

        entityValue2.setCreateDate(Date.valueOf(LocalDate.now()));
        entityValue2.setLastModificationDate(Date.valueOf(LocalDate.now()));
        entityValue2.setEntityName(entityName);

        HashSet<EntityValue> entityValueHashSet = new HashSet<EntityValue>();
        entityValueHashSet.add(entityValue1);
        entityValueHashSet.add(entityValue2);

        entityName.setEntityValue(entityValueHashSet);
        entityName.setFields(fieldHashSet);

        EntityNameDao entityNameDao = new EntityNameDao();
        entityNameDao.saveOrUpdate(entityName);
        entityNameDao.saveOrUpdate(entityName1);
        entityNameDao.saveOrUpdate(entityName2);

        /*session.saveOrUpdate(entityName);
        session.saveOrUpdate(entityName1);
        session.saveOrUpdate(entityName2);*/

        transaction.commit();
    }
}
