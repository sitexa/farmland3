package com.sitexa.user.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * MemberProperty entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.user.data.MemberProperty
 */

public class MemberPropertyDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(MemberPropertyDAO.class);
    private static final String CLASS_NAME = "MemberProperty";
    private static final String ID_NAME = "propId";
    // property constants
    public static final String PROP_NAME = "propName";
    public static final String PROP_VALUE = "propValue";

    public MemberPropertyDAO() {
        super(CLASS_NAME, ID_NAME);
    }

    public void updateProperties(List<MemberProperty> properties) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            int i = 0;
            for (MemberProperty property : properties) {

                if (property.getPropName() != null && property.getPropName().length() > 50)
                    property.setPropName(property.getPropName().substring(0, 50));
                if (property.getPropValue() != null && property.getPropValue().length() > 250)
                    property.setPropValue(property.getPropValue().substring(0, 250));

                session.saveOrUpdate(property);
                if (++i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            session.flush();
            session.clear();
            tx.commit();
        } catch (HibernateException e) {
            log.error(e);
            tx.rollback();
            throw e;
        }
    }

    public void createProperties(List<MemberProperty> properties) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            int i = 0;
            for (MemberProperty property : properties) {
                if (property.getPropName() != null && property.getPropName().length() > 50)
                    property.setPropName(property.getPropName().substring(0, 50));
                if (property.getPropValue() != null && property.getPropValue().length() > 250)
                    property.setPropValue(property.getPropValue().substring(0, 250));

                session.save(property);
                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            session.flush();
            session.clear();
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            log.error(e);
            throw e;
        }
    }

    public void save(MemberProperty property) {
        log.debug("saving MemberProperty instance");
        try {
            if (property.getPropName() != null && property.getPropName().length() > 50)
                property.setPropName(property.getPropName().substring(0, 50));
            if (property.getPropValue() != null && property.getPropValue().length() > 250)
                property.setPropValue(property.getPropValue().substring(0, 250));

            super.save(property);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(MemberProperty persistentInstance) {
        log.debug("deleting MemberProperty instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public MemberProperty findById(java.lang.String id) {
        log.debug("getting MemberProperty instance with id: " + id);
        try {
            MemberProperty instance = (MemberProperty) getSession().get(
                    "com.sitexa.user.data.MemberProperty", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(MemberProperty instance) {
        log.debug("finding MemberProperty instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.user.data.MemberProperty").add(
                    Example.create(instance)).list();
            log.debug("find by example successful, result size: "
                    + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding MemberProperty instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from MemberProperty as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByPropName(Object propName) {
        return findByProperty(PROP_NAME, propName);
    }

    public List findByPropValue(Object propValue) {
        return findByProperty(PROP_VALUE, propValue);
    }

    public List findAll() {
        log.debug("finding all MemberProperty instances");
        try {
            String queryString = "from MemberProperty";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public MemberProperty merge(MemberProperty detachedInstance) {
        log.debug("merging MemberProperty instance");
        try {
            MemberProperty result = (MemberProperty) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MemberProperty instance) {
        log.debug("attaching dirty MemberProperty instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(MemberProperty instance) {
        log.debug("attaching clean MemberProperty instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}