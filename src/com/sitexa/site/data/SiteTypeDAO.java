package com.sitexa.site.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * SiteType entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.sitexa.site.data.SiteType
 */

public class SiteTypeDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(SiteTypeDAO.class);
    // property constants
    public static final String TYPE_NAME = "typeName";

    public void save(SiteType transientInstance) {
        log.debug("saving SiteType instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(SiteType persistentInstance) {
        log.debug("deleting SiteType instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public SiteType findById(java.lang.String id) {
        log.debug("getting SiteType instance with id: " + id);
        try {
            SiteType instance = (SiteType) getSession().get(
                    "com.sitexa.site.data.SiteType", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(SiteType instance) {
        log.debug("finding SiteType instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.site.data.SiteType").add(
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
        log.debug("finding SiteType instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from SiteType as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByTypeName(Object typeName) {
        return findByProperty(TYPE_NAME, typeName);
    }

    public List findAll() {
        log.debug("finding all SiteType instances");
        try {
            String queryString = "from SiteType order by typeId desc";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public SiteType merge(SiteType detachedInstance) {
        log.debug("merging SiteType instance");
        try {
            SiteType result = (SiteType) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(SiteType instance) {
        log.debug("attaching dirty SiteType instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(SiteType instance) {
        log.debug("attaching clean SiteType instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}