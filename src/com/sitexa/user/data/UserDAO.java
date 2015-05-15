package com.sitexa.user.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for User
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.user.data.User
 */

public class UserDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(UserDAO.class);
    public static final String CLASS_NAME = "User";
    public static final String ID_NAME = "userId";

    // property constants
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String STATUS = "status";

    public UserDAO() {
        super(CLASS_NAME, ID_NAME);
    }

    public void save(User transientInstance)  {
        log.debug("saving User instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error("save failed", re);
            throw re;
        }
    }

    public void update(User user)  {
        log.debug("saving User instance");
        try {
            super.update(user);
            log.debug("save successful");
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(User persistentInstance){
        log.debug("deleting User instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public User findById(java.lang.String id) {
        log.debug("getting User instance with id: " + id);
        try {
            User instance = (User) getSession().get(
                    "com.sitexa.user.data.User", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(User instance) {
        log.debug("finding User instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.user.data.User").add(
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
        log.debug("finding User instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from User as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByUsername(Object username) {
        return findByProperty(USERNAME, username);
    }

    public List findByPassword(Object password) {
        return findByProperty(PASSWORD, password);
    }

    public List findByStatus(Object status) {
        return findByProperty(STATUS, status);
    }

    public List findAll() {
        log.debug("finding all User instances");
        try {
            String queryString = "from User order by registerDate desc";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public User merge(User detachedInstance) {
        log.debug("merging User instance");
        try {
            User result = (User) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(User instance) {
        log.debug("attaching dirty User instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(User instance) {
        log.debug("attaching clean User instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}