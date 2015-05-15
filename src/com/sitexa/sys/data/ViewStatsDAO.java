package com.sitexa.sys.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * ViewStats entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.sys.data.ViewStats
 */

@SuppressWarnings("deprecation")
public class ViewStatsDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(ViewStatsDAO.class);
    // property constants
    public static final String REMOTE_ADDR = "remoteAddr";
    public static final String REQUEST_URI = "requestUri";

    public void save(ViewStats transientInstance) {
        log.debug("saving ViewStats instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public Long getTotal() {
        Long t = 0L;
        try {
            String hql = "select count(*) from T_View_Stats ";
            Connection conn = getSession().connection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(hql);
            while (rs.next()) {
                t = rs.getLong(1);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    public void delete(ViewStats persistentInstance) {
        log.debug("deleting ViewStats instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public ViewStats findById(java.lang.Long id) {
        log.debug("getting ViewStats instance with id: " + id);
        try {
            ViewStats instance = (ViewStats) getSession().get(
                    "com.sitexa.sys.data.ViewStats", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(ViewStats instance) {
        log.debug("finding ViewStats instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.sys.data.ViewStats").add(
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
        log.debug("finding ViewStats instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from ViewStats as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByRemoteAddr(Object remoteAddr) {
        return findByProperty(REMOTE_ADDR, remoteAddr);
    }

    public List findByRequestUri(Object requestUri) {
        return findByProperty(REQUEST_URI, requestUri);
    }

    public List findAll() {
        log.debug("finding all ViewStats instances");
        try {
            String queryString = "from ViewStats";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public ViewStats merge(ViewStats detachedInstance) {
        log.debug("merging ViewStats instance");
        try {
            ViewStats result = (ViewStats) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ViewStats instance) {
        log.debug("attaching dirty ViewStats instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(ViewStats instance) {
        log.debug("attaching clean ViewStats instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}