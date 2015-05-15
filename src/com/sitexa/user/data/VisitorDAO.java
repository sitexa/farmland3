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
 * Visitor entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.user.data.Visitor
 */

public class VisitorDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(VisitorDAO.class);
    // property constants
    public static final String TIMES = "times";

    public void save(Visitor transientInstance) {
        log.debug("saving Visitor instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void saveOrUpdate(Visitor visitor) {
        super.saveOrUpdate(visitor);
    }

    public void update(Visitor visitor) {
        super.update(visitor);
    }

    public void delete(Visitor persistentInstance) {
        log.debug("deleting Visitor instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Visitor findById(java.lang.String id) {
        log.debug("getting Visitor instance with id: " + id);
        try {
            Visitor instance = (Visitor) getSession().get(
                    "com.sitexa.user.data.Visitor", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public Visitor findByGuest(Member member, Member guest) {
        System.out.println("VisitorDAO.findByGuest");
        try {
            String queryString = "from Visitor as model where model.member=? and model.guest=?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, member).setParameter(1, guest);
            return (Visitor) queryObject.uniqueResult();
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error("findByGuest failed", re);
            throw re;
        }
    }

    public List findByExample(Visitor instance) {
        log.debug("finding Visitor instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.user.data.Visitor").add(
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
        log.debug("finding Visitor instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Visitor as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByTimes(Object times) {
        return findByProperty(TIMES, times);
    }

    public List findAll() {
        log.debug("finding all Visitor instances");
        try {
            String queryString = "from Visitor";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Visitor merge(Visitor detachedInstance) {
        log.debug("merging Visitor instance");
        try {
            Visitor result = (Visitor) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Visitor instance) {
        log.debug("attaching dirty Visitor instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Visitor instance) {
        log.debug("attaching clean Visitor instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}