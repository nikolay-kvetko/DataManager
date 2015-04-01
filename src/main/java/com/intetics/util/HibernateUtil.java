package com.intetics.util;


//import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    public static SessionFactory sessionFactory;
    private final ThreadLocal threadLocal = new ThreadLocal();
    private static HibernateUtil instance;
    //private static Logger log = Logger.getLogger(HibernateUtil.class);


    /**
     * Create object of SessionFactory
     */
    private HibernateUtil() {
        try {
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Throwable e) {
            //log.error("Initial SessionFactory creation failed. " + e);
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
        Session session = (Session) threadLocal.get();
        if (session == null) {
            session = sessionFactory.openSession();
            threadLocal.set(session);
        }

        return session;
    }
}