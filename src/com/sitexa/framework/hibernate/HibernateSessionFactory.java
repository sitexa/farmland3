package com.sitexa.framework.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Configures and provides access to Hibernate sessions, tied to the
 * current thread of execution.  Follows the Thread Local Session
 * pattern, see {@link http://hibernate.org/42.html }.
 */
public class HibernateSessionFactory {

    /**
     * Location of hibernate.cfg.xml file.
     * Location should be on the classpath as Hibernate uses
     * #resourceAsStream style lookup for its configuration file.
     * The default classpath location of the hibernate config file is
     * in the default package. Use #setConfigFile() to update
     * the location of the configuration file for the current session.
     */
    private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
    private static String configFile = CONFIG_FILE_LOCATION;

    private static final ThreadLocal<Session> sessionThread = new ThreadLocal<Session>();
    private static final ThreadLocal<Transaction> transactionThread = new ThreadLocal<Transaction>();

    private static Configuration configuration = new Configuration();
    private static org.hibernate.SessionFactory sessionFactory;

    static {
        try {
            configuration.configure(CONFIG_FILE_LOCATION);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            System.err.println("%%%% Error Creating SessionFactory %%%%");
            e.printStackTrace();
        }
    }

    private HibernateSessionFactory() {
    }

    /**
     * Returns the ThreadLocal Session instance.  Lazy initialize
     * the <code>SessionFactory</code> if needed.
     *
     * @return Session
     * @throws HibernateException
     */
    public static Session getSession() throws HibernateException {
        Session session = null;// (Session) sessionThread.get();

        if (session == null || !session.isOpen()) {
            if (sessionFactory == null) {
                rebuildSessionFactory();
            }
            session = (sessionFactory != null) ? sessionFactory.openSession() : null;
            sessionThread.set(session);
        }

        return session;
    }

    /**
     * Rebuild hibernate session factory
     */
    public static void rebuildSessionFactory() {
        try {
            configuration.configure(configFile);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            System.err
                    .println("%%%% Error Creating SessionFactory %%%%");
            e.printStackTrace();
        }
    }

    /**
     * Close the single hibernate session instance.
     *
     * @throws HibernateException
     */
    public static void closeSession() throws HibernateException {
        Session session = (Session) sessionThread.get();
        sessionThread.set(null);

        if (session != null) {
            session.close();
        }
    }

    /**
     * return session factory
     */
    public static org.hibernate.SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * return session factory
     * <p/>
     * session factory will be rebuilded in the next call
     */
    public static void setConfigFile(String configFile) {
        HibernateSessionFactory.configFile = configFile;
        sessionFactory = null;
    }

    /**
     * return hibernate configuration
     */
    public static Configuration getConfiguration() {
        return configuration;
    }

    /**
     * 取得当前session的事务
     *
     * @return Transaction
     */
    public static Transaction transaction() {
        Transaction transaction = transactionThread.get();
        if (transaction == null) {
            transaction = getSession().beginTransaction();
            transactionThread.set(transaction);
        }
        return transaction;
    }

    /**
     * 提交当前session的事务
     */
    public static void commitTransaction() {
        Transaction transaction = transactionThread.get();
        transactionThread.set(null);
        if (transaction != null)
            transaction.commit();
    }

    /**
     * 回滚当前session的事务
     */
    public static void rollbackTransaction() {
        Transaction tx = transactionThread.get();
        transactionThread.set(null);
        if (tx != null)
            tx.rollback();
    }

    /**
     * 获取独立的session,只用于长流程、一次性事务管理中,此session不受管于Http Request请求线程
     */
    public static Session getIndependentSession() {
//        sessionFactory.getCurrentSession();
        return sessionFactory.openSession();
    }

    /**
     * 关闭指定的session,只用于长流程、一次性事务管理中
     *
     * @param session
     */
    public static void closeSession(Session session) {
        if (session != null) {
            session.clear();
            session.close();
            sessionThread.set(null);
        }
    }

}