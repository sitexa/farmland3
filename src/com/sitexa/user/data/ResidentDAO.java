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
 * Resident entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see Resident
 */

public class ResidentDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(ResidentDAO.class);
    // property constants
    public static final String NAME = "name";
    public static final String GENDER = "gender";
    public static final String CCID = "ccid";
    public static final String CAREER = "career";
    public static final String HOMEPHONE = "homephone";

    public void save(Resident transientInstance) {
        log.debug("saving Resident instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Resident persistentInstance) {
        log.debug("deleting Resident instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Resident findById(java.lang.String id) {
        log.debug("getting Resident instance with id: " + id);
        try {
            Resident instance = (Resident) getSession().get(
                    "com.sitexa.user.data.Resident", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Resident instance) {
        log.debug("finding Resident instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.user.data.Resident").add(
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
        log.debug("finding Resident instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Resident as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByName(Object name) {
        return findByProperty(NAME, name);
    }

    public List findByGender(Object gender) {
        return findByProperty(GENDER, gender);
    }

    public List findByCcid(Object ccid) {
        return findByProperty(CCID, ccid);
    }

    public List findByCareer(Object career) {
        return findByProperty(CAREER, career);
    }

    public List findByHomephone(Object homephone) {
        return findByProperty(HOMEPHONE, homephone);
    }

    public List findAll() {
        log.debug("finding all Resident instances");
        try {
            String queryString = "from Resident";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Resident merge(Resident detachedInstance) {
        log.debug("merging Resident instance");
        try {
            Resident result = (Resident) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Resident instance) {
        log.debug("attaching dirty Resident instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Resident instance) {
        log.debug("attaching clean Resident instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}