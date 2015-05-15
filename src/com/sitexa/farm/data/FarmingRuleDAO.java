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
 * FarmingRule entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.farm.data.FarmingRule
 */

public class FarmingRuleDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(FarmingRuleDAO.class);
    // property constants
    public static final String RULE_VALUE = "ruleValue";
    public static final String REMARK = "remark";

    public void save(FarmingRule transientInstance) {
        log.debug("saving FarmingRule instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(FarmingRule persistentInstance) {
        log.debug("deleting FarmingRule instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public FarmingRule findById(java.lang.String id) {
        log.debug("getting FarmingRule instance with id: " + id);
        try {
            FarmingRule instance = (FarmingRule) getSession().get(
                    "com.sitexa.farm.data.FarmingRule", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(FarmingRule instance) {
        log.debug("finding FarmingRule instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.farm.data.FarmingRule").add(
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
        log.debug("finding FarmingRule instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from FarmingRule as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByRuleValue(Object ruleValue) {
        return findByProperty(RULE_VALUE, ruleValue);
    }

    public List findByRemark(Object remark) {
        return findByProperty(REMARK, remark);
    }

    public List findAll() {
        log.debug("finding all FarmingRule instances");
        try {
            String queryString = "from FarmingRule";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public FarmingRule merge(FarmingRule detachedInstance) {
        log.debug("merging FarmingRule instance");
        try {
            FarmingRule result = (FarmingRule) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FarmingRule instance) {
        log.debug("attaching dirty FarmingRule instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(FarmingRule instance) {
        log.debug("attaching clean FarmingRule instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}