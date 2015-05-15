package com.sitexa.farm.data;

import com.sitexa.framework.hibernate.BaseDAO;
import com.sitexa.user.data.Member;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * FarmOwner entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.farm.data.FarmOwner
 */

public class FarmOwnerDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(FarmOwnerDAO.class);
    // property constants
    public static final String REMARK = "remark";

    public void save(FarmOwner transientInstance) {
        log.debug("saving FarmOwner instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(FarmOwner persistentInstance) {
        log.debug("deleting FarmOwner instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public void update(FarmOwner fo) {
        log.debug("update FarmOwner instance");
        try {
            super.update(fo);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public void removeOwners(Farm farm) {
        log.debug("removeOwners FarmOwner instance");
        try {
            String sql = "delete FarmOwner as model where model.farm=? ";
            Query query = getSession().createQuery(sql).setParameter(0, farm);
            query.executeUpdate();
            log.debug("removeOwners successful");
        } catch (RuntimeException re) {
            log.error("removeOwners failed", re);
            throw re;
        }
    }

    public void removeOwner(Farm farm, Member member) {
        try {
            String sql = " delete FarmOwner as model where model.farm=? and model.member=?";
            Query query = getSession().createQuery(sql).setParameter(0, farm).setParameter(1, member);
            query.executeUpdate();
        } catch (RuntimeException re) {
            log.error("removeOwner failed", re);
            throw re;
        }
    }


    public FarmOwner findByFarmAndMember(Farm farm, Member member) {
        String sql = " from FarmOwner as model where model.farm=? and model.member=?";
        try {
            Query query = getSession().createQuery(sql).setParameter(0, farm).setParameter(1, member);
            return (FarmOwner) query.uniqueResult();
        } catch (RuntimeException re) {
            log.error("findByFarmAndMember failed", re);
            throw re;
        }
    }

    public FarmOwner findById(java.lang.String id) {
        log.debug("getting FarmOwner instance with id: " + id);
        try {
            FarmOwner instance = (FarmOwner) getSession().get(
                    "com.sitexa.farm.data.FarmOwner", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(FarmOwner instance) {
        log.debug("finding FarmOwner instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.farm.data.FarmOwner").add(
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
        log.debug("finding FarmOwner instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from FarmOwner as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByRemark(Object remark) {
        return findByProperty(REMARK, remark);
    }

    public List findAll() {
        log.debug("finding all FarmOwner instances");
        try {
            String queryString = "from FarmOwner";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public FarmOwner merge(FarmOwner detachedInstance) {
        log.debug("merging FarmOwner instance");
        try {
            FarmOwner result = (FarmOwner) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FarmOwner instance) {
        log.debug("attaching dirty FarmOwner instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(FarmOwner instance) {
        log.debug("attaching clean FarmOwner instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}