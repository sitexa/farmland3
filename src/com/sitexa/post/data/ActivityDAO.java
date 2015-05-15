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
 * Activity entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.sitexa.post.data.Activity
 */

public class ActivityDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(ActivityDAO.class);

    public static final String CLASS_NAME = "Activity";
    public static final String ID_NAME = "id";

    public ActivityDAO() {
        super(CLASS_NAME, ID_NAME);
    }
    
    // property constants
    public static final String CONTACT = "contact";
    public static final String EXPENSE = "expense";
    public static final String ADDRESS = "address";

    public void save(Activity transientInstance) {
        log.debug("saving Activity instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void saveOrUpdate(Activity activity){
        try{
            super.saveOrUpdate(activity);
        } catch (RuntimeException e) {
            log.error(e);
            throw e;
        }
    }

    public void delete(Activity persistentInstance) {
        log.debug("deleting Activity instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Activity findById(java.lang.String id) {
        log.debug("getting Activity instance with id: " + id);
        try {
            Activity instance = (Activity) getSession().get(
                    "com.sitexa.post.data.Activity", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Activity instance) {
        log.debug("finding Activity instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.post.data.Activity").add(
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
        log.debug("finding Activity instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Activity as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByContact(Object contact) {
        return findByProperty(CONTACT, contact);
    }

    public List findByExpense(Object expense) {
        return findByProperty(EXPENSE, expense);
    }

    public List findByAddress(Object address) {
        return findByProperty(ADDRESS, address);
    }

    public List findAll() {
        log.debug("finding all Activity instances");
        try {
            String queryString = "from Activity";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Activity merge(Activity detachedInstance) {
        log.debug("merging Activity instance");
        try {
            Activity result = (Activity) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Activity instance) {
        log.debug("attaching dirty Activity instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Activity instance) {
        log.debug("attaching clean Activity instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}