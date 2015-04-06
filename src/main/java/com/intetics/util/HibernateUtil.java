package com.intetics.util;


//import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    public static SessionFactory sessionFactory;
    private static HibernateUtil instance;

    /**
     * Create object of SessionFactory
     */
    private HibernateUtil() {
        try {
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static synchronized HibernateUtil getHibernateUtil(){
        if (instance == null){
            instance = new HibernateUtil();
        }
        return instance;
    }

    /**
     * Create object of Session
     * @return session
     */
    public Session getSession () {
        Session session;
        try{
            session = sessionFactory.getCurrentSession();
        }catch (HibernateException e){
            session = sessionFactory.openSession();
        }

        return session;
    }
}