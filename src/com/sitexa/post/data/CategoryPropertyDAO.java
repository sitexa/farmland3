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
 * CategoryProperty entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.post.data.CategoryProperty
 */

public class CategoryPropertyDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(CategoryPropertyDAO.class);
    public static final String CLASS_NAME = "CategoryProperty";
    public static final String ID_NAME = "propId";

    public CategoryPropertyDAO() {
        super(CLASS_NAME, ID_NAME);
    }

    // property constants
    public static final String PROP_NAME = "propName";
    public static final String PROP_VALUE = "propValue";

    public void save(CategoryProperty transientInstance) {
        super.save(transientInstance);
    }

    public void update(CategoryProperty property){
        super.update(property);
    }
    public void delete(CategoryProperty persistentInstance) {
        log.debug("deleting CategoryProperty instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public CategoryProperty findById(java.lang.String id) {
        log.debug("getting CategoryProperty instance with id: " + id);
        try {
            CategoryProperty instance = (CategoryProperty) getSession().get(
                    "com.sitexa.post.data.CategoryProperty", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(CategoryProperty instance) {
        log.debug("finding CategoryProperty instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.post.data.CategoryProperty").add(
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
        log.debug("finding CategoryProperty instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from CategoryProperty as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByPropName(Object propName) {
        return findByProperty(PROP_NAME, propName);
    }

    public List findByPropValue(Object propValue) {
        return findByProperty(PROP_VALUE, propValue);
    }

    public List findAll() {
        log.debug("finding all CategoryProperty instances");
        try {
            String queryString = "from CategoryProperty";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public CategoryProperty merge(CategoryProperty detachedInstance) {
        log.debug("merging CategoryProperty instance");
        try {
            CategoryProperty result = (CategoryProperty) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(CategoryProperty instance) {
        log.debug("attaching dirty CategoryProperty instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(CategoryProperty instance) {
        log.debug("attaching clean CategoryProperty instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}