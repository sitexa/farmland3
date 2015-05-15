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
 * FarmPlan entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.farm.data.FarmPlan
 */

public class FarmPlanDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(FarmPlanDAO.class);
    // property constants
    public static final String PLAN_NAME = "planName";
    public static final String DESCRIPTION = "description";
    public static final String PRICE = "price";
    public static final String REMARK = "remark";
    public static final String STATUS = "status";

    public void save(FarmPlan transientInstance) {
        log.debug("saving FarmPlan instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void update(FarmPlan transientInstance) {
        log.debug("update FarmPlan instance");
        try {
            super.update(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
    public void delete(FarmPlan persistentInstance) {
        log.debug("deleting FarmPlan instance");
        try {
        	super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public FarmPlan findById(java.lang.String id) {
        log.debug("getting FarmPlan instance with id: " + id);
        try {
            FarmPlan instance = (FarmPlan) getSession().get(
                    "com.sitexa.farm.data.FarmPlan", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(FarmPlan instance) {
        log.debug("finding FarmPlan instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.farm.data.FarmPlan").add(
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
        log.debug("finding FarmPlan instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from FarmPlan as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByPlanName(Object planName) {
        return findByProperty(PLAN_NAME, planName);
    }

    public List findByDescription(Object description) {
        return findByProperty(DESCRIPTION, description);
    }

    public List findByPrice(Object price) {
        return findByProperty(PRICE, price);
    }

    public List findByRemark(Object remark) {
        return findByProperty(REMARK, remark);
    }

    public List findByStatus(Object status) {
        return findByProperty(STATUS, status);
    }

    public List findAll() {
        log.debug("finding all FarmPlan instances");
        try {
            String queryString = "from FarmPlan";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public FarmPlan merge(FarmPlan detachedInstance) {
        log.debug("merging FarmPlan instance");
        try {
            FarmPlan result = (FarmPlan) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FarmPlan instance) {
        log.debug("attaching dirty FarmPlan instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(FarmPlan instance) {
        log.debug("attaching clean FarmPlan instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}