package com.sitexa.framework.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Data access object (DAO) for domain model
 *
 * @author MyEclipse Persistence Tools
 */
public class BaseDAO implements IBaseDAO {

    private static final String ID_NULL = "";
    String className;
    String idPropertyName;

    private Session session = null;

    protected BaseDAO() {
        getSession();
    }

    protected BaseDAO(String className, String idPropertyName) {
        this();
        this.className = className;
        this.idPropertyName = idPropertyName;
    }

    public Session getSession() {
        if (null == this.session)
            session = HibernateSessionFactory.getSession();
        return session;
    }

    protected void delete(Object object) {
        Transaction tx = session.beginTransaction();
        tx.begin();
        session.delete(object);
        tx.commit();
    }

    protected void save(Object object) {
        Transaction tx = session.beginTransaction();
        tx.begin();
        session.save(object);
        tx.commit();
    }

    protected void update(Object object) {
        Transaction tx = session.beginTransaction();
        tx.begin();
        session.update(object);
        tx.commit();
    }

    protected void saveOrUpdate(Object object) {
        Transaction tx = session.beginTransaction();
        tx.begin();
        session.saveOrUpdate(object);
        tx.commit();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    protected boolean isNullID(String id) {
        return id == null || ID_NULL.equals(id);
    }
}