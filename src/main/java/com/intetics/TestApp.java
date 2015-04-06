package com.intetics;

import com.intetics.pojos.*;
import org.hibernate.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Кузнец on 05.04.2015.
 */

public class TestApp {

    static ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-configuration.xml");

    private static SessionFactory sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");

    protected static Session currentSession()
    {
        Session session = null;

        /*try {
            session = sessionFactory.getCurrentSession();
        }
        catch (HibernateException ex) {
            System.out.println(ex);
        }
        if (session == null) {*/
            session = sessionFactory.openSession();
            session.setFlushMode(FlushMode.MANUAL);
        /*}*/

        return session;
    }

    @Transactional
    public static void main(String[] args) {

        Session session = currentSession();
        //Transaction transaction = session.beginTransaction();

        EntityName entityName = (EntityName) session.get(EntityName.class, 1L);
        System.out.println(entityName.getFields().size());
        System.out.println(entityName.getEntityInstance().size());

        /*Field field = new Field();
        field.setName("Age");
        field.setType("Number");
        field.setEntityName(entityName);
        session.saveOrUpdate(field);*/
        /*entityName.getFields().add(field);
        session.saveOrUpdate(entityName);*/



        /*EntityName entityName = (EntityName) session.get(EntityName.class, 1L);
        EntityInstance entityInstance = new EntityInstance();

        FieldValuesFactory fieldValuesFactory = new FieldValuesFactory();

        System.out.println(
                fieldValuesFactory.setValue(entityName.getFields(), entityInstance, "March"));
        System.out.println(
                fieldValuesFactory.setValue(entityName.getFields(), entityInstance, 48D));

        entityInstance.setEntityName(entityName);

        session.saveOrUpdate(entityInstance);*/





        /*Company company = new Company();
        company.setName("Intetics");

        EntityName entityName = new EntityName();
        entityName.setName("Clients");

        entityName.setCompany(company);
        company.getEntityNames().add(entityName);

        Field field = new Field();
        field.setName("First Name");
        field.setType("StringText");

        TextStringRestriction textStringRestriction = new TextStringRestriction();
        textStringRestriction.setSize(224);

        textStringRestriction.setField(field);
        field.setTextStringRestriction(textStringRestriction);

        entityName.getFields().add(field);
        field.setEntityName(entityName);

        session.saveOrUpdate(company);*/

        //transaction.commit();

        /*Field field = (Field) session.get(Field.class, 1L);

        System.out.println(field.getValues().size());*/

    }
}
