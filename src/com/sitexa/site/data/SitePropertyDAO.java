package com.sitexa.site.data;

import com.sitexa.framework.exception.BaseException;
import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * SiteProperty entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.site.data.SiteProperty
 */

public class SitePropertyDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(SitePropertyDAO.class);

    // property constants
    public static final String PROP_NAME = "propName";
    public static final String PROP_VALUE = "propValue";

    public static final String CLASS_NAME = "SiteProperty";
    public static final String ID_NAME = "propId";

    public SitePropertyDAO() {
        super(CLASS_NAME, ID_NAME);
    }

    public String getProperty(Site site, String propName) {
        String propValue = null;
        String hql = "from SiteProperty where site=? and propName=? ";
        try {
            Query query = getSession().createQuery(hql);
            query.setParameter(0, site).setParameter(1, propName);
            List list = query.list();
            for (Object aList : list) {
                SiteProperty siteProperty = (SiteProperty) aList;
                if (propName.equals(siteProperty.getPropName())) {
                    propValue = siteProperty.getPropValue();
                    break;
                }
            }
            return propValue;
        } catch (HibernateException e) {
            log.error(e);
            throw e;
        }
    }

    public void saveOrUpdate(List<SiteProperty> properties) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            int i = 0;
            for (SiteProperty property : properties) {
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

    public void save(SiteProperty property) throws BaseException {
        log.debug("saving SiteProperty instance");
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

    public void delete(String propId) {
        delete(findById(propId));
    }

    public void delete(SiteProperty persistentInstance) {
        log.debug("deleting SiteProperty instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public SiteProperty findById(java.lang.String id) {
        log.debug("getting SiteProperty instance with id: " + id);
        try {
            SiteProperty instance = (SiteProperty) getSession().get(
                    "com.sitexa.site.data.SiteProperty", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(SiteProperty instance) {
        log.debug("finding SiteProperty instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.site.data.SiteProperty").add(
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
        log.debug("finding SiteProperty instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from SiteProperty as model where model."
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
        log.debug("finding all SiteProperty instances");
        try {
            String queryString = "from SiteProperty";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public SiteProperty merge(SiteProperty detachedInstance) {
        log.debug("merging SiteProperty instance");
        try {
            SiteProperty result = (SiteProperty) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(SiteProperty instance) {
        log.debug("attaching dirty SiteProperty instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(SiteProperty instance) {
        log.debug("attaching clean SiteProperty instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}