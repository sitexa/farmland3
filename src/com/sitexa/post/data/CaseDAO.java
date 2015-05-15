package com.sitexa.post.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for Case
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.post.data.Case
 */

public class CaseDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(CaseDAO.class);
    public static final String CLASS_NAME = "Case";
    public static final String ID_NAME = "id";

    public CaseDAO() {
        super(CLASS_NAME, ID_NAME);
    }

    // property constants
    public static final String LOCATION = "location";

    public void save(Case transientInstance) {
        log.debug("saving Case instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Case persistentInstance) {
        log.debug("deleting Case instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Case findById(java.lang.String id) {
        log.debug("getting Case instance with id: " + id);
        try {
            Case instance = (Case) getSession().get(
                    "com.sitexa.post.data.Case", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Case instance) {
        log.debug("finding Case instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.post.data.Case").add(
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
        log.debug("finding Case instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Case as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByLocation(Object location) {
        return findByProperty(LOCATION, location);
    }

    public List findAll() {
        log.debug("finding all Case instances");
        try {
            String queryString = "from Case";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Case merge(Case detachedInstance) {
        log.debug("merging Case instance");
        try {
            Case result = (Case) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Case instance) {
        log.debug("attaching dirty Case instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Case instance) {
        log.debug("attaching clean Case instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}