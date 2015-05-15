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
 * MemberCredit entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.user.data.MemberCredit
 */

public class MemberCreditDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(MemberCreditDAO.class);
    // property constants
    public static final String CREDIT = "credit";

    public void save(MemberCredit transientInstance) {
        log.debug("saving MemberCredit instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(MemberCredit persistentInstance) {
        log.debug("deleting MemberCredit instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public void update(MemberCredit mc) {
        try {
            super.update(mc);
        } catch (RuntimeException re) {
            log.error(re);
            throw re;
        }
    }

    public MemberCredit findById(java.lang.String id) {
        log.debug("getting MemberCredit instance with id: " + id);
        try {
            MemberCredit instance = (MemberCredit) getSession().get(
                    "com.sitexa.user.data.MemberCredit", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(MemberCredit instance) {
        log.debug("finding MemberCredit instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.user.data.MemberCredit").add(
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
        log.debug("finding MemberCredit instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from MemberCredit as model where model."
                    + propertyName + " = ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findAll() {
        log.debug("finding all MemberCredit instances");
        try {
            String queryString = "from MemberCredit";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public MemberCredit merge(MemberCredit detachedInstance) {
        log.debug("merging MemberCredit instance");
        try {
            MemberCredit result = (MemberCredit) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MemberCredit instance) {
        log.debug("attaching dirty MemberCredit instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(MemberCredit instance) {
        log.debug("attaching clean MemberCredit instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}