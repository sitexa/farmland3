package com.sitexa.user.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * MemberType entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.user.data.MemberType
 */

public class MemberTypeDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(MemberTypeDAO.class);
    // property constants
    public static final String NAME = "name";

    public void save(MemberType transientInstance) {
        log.debug("saving MemberType instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(MemberType persistentInstance) {
        log.debug("deleting MemberType instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public MemberType findById(java.lang.String id) {
        log.debug("getting MemberType instance with id: " + id);
        try {
            MemberType instance = (MemberType) getSession().get(
                    "com.sitexa.user.data.MemberType", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(MemberType instance) {
        log.debug("finding MemberType instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.user.data.MemberType").add(
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
        log.debug("finding MemberType instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from MemberType as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByName(Object name) {
        return findByProperty(NAME, name);
    }

    public List findAll() {
        log.debug("finding all MemberType instances");
        try {
            String queryString = "from MemberType";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public MemberType merge(MemberType detachedInstance) {
        log.debug("merging MemberType instance");
        try {
            MemberType result = (MemberType) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MemberType instance) {
        log.debug("attaching dirty MemberType instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(MemberType instance) {
        log.debug("attaching clean MemberType instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}