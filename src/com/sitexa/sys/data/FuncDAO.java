package com.sitexa.sys.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for Func
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.sys.data.Func
 */

public class FuncDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(FuncDAO.class);
    // property constants
    public static final String FUNC_NAME = "funcName";
    public static final String FUNC_TYPE = "funcType";
    public static final String APPLICATION = "application";
    public static final String REMARK = "remark";

    public void save(Func transientInstance) {
        log.debug("saving Func instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Func persistentInstance) {
        log.debug("deleting Func instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        } 
    }

    public Func findById(java.lang.String id) {
        log.debug("getting Func instance with id: " + id);
        try {
            Func instance = (Func) getSession().get(
                    "com.sitexa.sys.data.Func", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Func instance) {
        log.debug("finding Func instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.sys.data.Func").add(
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
        log.debug("finding Func instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Func as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByFuncName(Object funcName) {
        return findByProperty(FUNC_NAME, funcName);
    }

    public List findByFuncType(Object funcType) {
        return findByProperty(FUNC_TYPE, funcType);
    }

    public List findByApplication(Object application) {
        return findByProperty(APPLICATION, application);
    }

    public List findByRemark(Object remark) {
        return findByProperty(REMARK, remark);
    }

    public List findAll() {
        log.debug("finding all Func instances");
        try {
            String queryString = "from Func";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Func merge(Func detachedInstance) {
        log.debug("merging Func instance");
        try {
            Func result = (Func) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Func instance) {
        log.debug("attaching dirty Func instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Func instance) {
        log.debug("attaching clean Func instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}