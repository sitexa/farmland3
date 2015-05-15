package com.sitexa.sys.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * RoleInUser entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.user.data.RoleInUser
 */

public class RoleInUserDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(RoleInUserDAO.class);

    // property constants

    public void save(RoleInUser transientInstance) {
        log.debug("saving RoleInUser instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(RoleInUser persistentInstance) {
        log.debug("deleting RoleInUser instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public RoleInUser findById(RoleInUserId id) {
        log.debug("getting RoleInUser instance with id: " + id);
        try {
            RoleInUser instance = (RoleInUser) getSession().get(
                    "com.sitexa.sys.data.RoleInUser", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(RoleInUser instance) {
        log.debug("finding RoleInUser instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.sys.data.RoleInUser").add(
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
        log.debug("finding RoleInUser instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from RoleInUser as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findAll() {
        log.debug("finding all RoleInUser instances");
        try {
            String queryString = "from RoleInUser";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public RoleInUser merge(RoleInUser detachedInstance) {
        log.debug("merging RoleInUser instance");
        try {
            RoleInUser result = (RoleInUser) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(RoleInUser instance) {
        log.debug("attaching dirty RoleInUser instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(RoleInUser instance) {
        log.debug("attaching clean RoleInUser instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}