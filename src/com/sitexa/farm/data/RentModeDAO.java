package com.sitexa.farm.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import java.util.List;

/**
 * User: Oscar Peng, xnpeng@163.com
 * Date: 11-1-24
 * Time: 下午11:34
 */
public class RentModeDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(LandDAO.class);

    public void save(RentMode transientInstance) {
        log.debug("saving RentMode instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void update(RentMode rentMode) {
        super.update(rentMode);
    }

    public void delete(RentMode persistentInstance) {
        log.debug("deleting RentMode instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public RentMode findById(java.lang.String id) {
        log.debug("getting RentMode instance with id: " + id);
        try {
            RentMode instance = (RentMode) getSession().get("com.sitexa.farm.data.RentMode",
                    id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findAll() {
        log.debug("finding all RentMode instances");
        try {
            String queryString = "from RentMode";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List findByLand(Land land) {
        try {
            String queryString = "from RentMode as mode where mode.land.landId=? ";
            Query queryObject = getSession().createQuery(queryString).setParameter(0, land.getLandId());
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
}
