package com.sitexa.farm.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * RuleType entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.farm.data.RuleType
 */

public class RuleTypeDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(RuleTypeDAO.class);
    // property constants
    public static final String TYPE_NAME = "typeName";
    public static final String DESCRIPTION = "description";

    public void save(RuleType transientInstance) {
        log.debug("saving RuleType instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(RuleType persistentInstance) {
        log.debug("deleting RuleType instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public RuleType findById(java.lang.String id) {
        log.debug("getting RuleType instance with id: " + id);
        try {
            RuleType instance = (RuleType) getSession().get(
                    "com.sitexa.farm.data.RuleType", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(RuleType instance) {
        log.debug("finding RuleType instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.farm.data.RuleType").add(
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
        log.debug("finding RuleType instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from RuleType as model where model."
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

    public List findByDescription(Object description) {
        return findByProperty(DESCRIPTION, description);
    }

    public List findAll() {
        log.debug("finding all RuleType instances");
        try {
            String queryString = "from RuleType";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public RuleType merge(RuleType detachedInstance) {
        log.debug("merging RuleType instance");
        try {
            RuleType result = (RuleType) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(RuleType instance) {
        log.debug("attaching dirty RuleType instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(RuleType instance) {
        log.debug("attaching clean RuleType instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}