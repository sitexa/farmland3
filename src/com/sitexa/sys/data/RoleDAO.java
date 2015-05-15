package com.sitexa.sys.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for Role
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.sys.data.Role
 */

public class RoleDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(RoleDAO.class);
    // property constants
    public static final String ROLE_NAME = "roleName";
    public static final String ROLE_TYPE = "roleType";

    public void save(Role transientInstance) {
        log.debug("saving Role instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Role persistentInstance) {
        log.debug("deleting Role instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Role findById(java.lang.String id) {
        log.debug("getting Role instance with id: " + id);
        try {
            Role instance = (Role) getSession().get(
                    "com.sitexa.sys.data.Role", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Role instance) {
        log.debug("finding Role instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.sys.data.Role").add(
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
        log.debug("finding Role instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Role as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByRoleName(Object roleName) {
        return findByProperty(ROLE_NAME, roleName);
    }

    public List findByRoleType(Object roleType) {
        return findByProperty(ROLE_TYPE, roleType);
    }

    public List findAll() {
        log.debug("finding all Role instances");
        try {
            String queryString = "from Role";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Role merge(Role detachedInstance) {
        log.debug("merging Role instance");
        try {
            Role result = (Role) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Role instance) {
        log.debug("attaching dirty Role instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Role instance) {
        log.debug("attaching clean Role instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}