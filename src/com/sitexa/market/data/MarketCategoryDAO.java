package com.sitexa.market.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * MarketCategory entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.market.data.MarketCategory
 */

public class MarketCategoryDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(MarketCategoryDAO.class);
    // property constants
    public static final String CATE_NAME = "cateName";

    public void save(MarketCategory transientInstance) {
        log.debug("saving MarketCategory instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(MarketCategory persistentInstance) {
        log.debug("deleting MarketCategory instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public MarketCategory findById(java.lang.String id) {
        log.debug("getting MarketCategory instance with id: " + id);
        try {
            MarketCategory instance = (MarketCategory) getSession().get(
                    "com.sitexa.market.data.MarketCategory", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(MarketCategory instance) {
        log.debug("finding MarketCategory instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.market.data.MarketCategory").add(
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
        log.debug("finding MarketCategory instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from MarketCategory as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByCateName(Object cateName) {
        return findByProperty(CATE_NAME, cateName);
    }

    public List findAll() {
        log.debug("finding all MarketCategory instances");
        try {
            String queryString = "from MarketCategory";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public MarketCategory merge(MarketCategory detachedInstance) {
        log.debug("merging MarketCategory instance");
        try {
            MarketCategory result = (MarketCategory) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MarketCategory instance) {
        log.debug("attaching dirty MarketCategory instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(MarketCategory instance) {
        log.debug("attaching clean MarketCategory instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}