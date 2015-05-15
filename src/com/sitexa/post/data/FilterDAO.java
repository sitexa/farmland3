package com.sitexa.post.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * Filter entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.post.data.Filter
 */

public class FilterDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(FilterDAO.class);
    // property constants
    public static final String WORDS = "words";

    public void save(Filter transientInstance) {
        log.debug("saving Filter instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void update(Filter transientInstance) {
        log.debug("update Filter instance");
        try {
            super.update(transientInstance);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public void saveOrUpdate(Filter filter) {
        log.debug("saveOrUpdate Filter instance");
        try {
            super.saveOrUpdate(filter);
            log.debug("saveOrUpdate successful");
        } catch (RuntimeException re) {
            log.error("saveOrUpdate failed", re);
            throw re;
        }
    }

    public void delete(Filter persistentInstance) {
        log.debug("deleting Filter instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public List findKeywords() {
        try {
            String hql = "from Filter where status = '1'";
            Query query = getSession().createQuery(hql);
            return query.list();
        } catch (RuntimeException re) {
            log.error("findKeywords failed", re);
            throw re;
        }
    }

    public Filter findById(java.lang.Integer id) {
        log.debug("getting Filter instance with id: " + id);
        try {
            Filter instance = (Filter) getSession().get(
                    "com.sitexa.post.data.Filter", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Filter instance) {
        log.debug("finding Filter instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.post.data.Filter").add(
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
        log.debug("finding Filter instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Filter as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByWords(Object words) {
        return findByProperty(WORDS, words);
    }

    public List findAll() {
        log.debug("finding all Filter instances");
        try {
            String queryString = "from Filter";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Filter merge(Filter detachedInstance) {
        log.debug("merging Filter instance");
        try {
            Filter result = (Filter) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Filter instance) {
        log.debug("attaching dirty Filter instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Filter instance) {
        log.debug("attaching clean Filter instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}