package com.sitexa.user.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

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

public class LeaveMessageDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(LeaveMessageDAO.class);

    public void save(LeaveMessage transientInstance) {
        log.debug("saving LeaveMessage instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error("save failed", re);
            throw re;
        }
    }

    public void update(LeaveMessage leaveMessage) {
        log.debug("saving LeaveMessage instance");
        try {
            super.update(leaveMessage);
            log.debug("save successful");
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(LeaveMessage leaveMessage) {
        log.debug("deleting LeaveMessage instance");
        try {
            super.delete(leaveMessage);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public LeaveMessage findById(java.lang.String id) {
        log.debug("getting LeaveMessage instance with id: " + id);
        try {
            LeaveMessage instance = (LeaveMessage) getSession().get("com.sitexa.user.data.LeaveMessage", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public LeaveMessage findByRelateIdAndInOutTag(String relateId, int inOutTag) {
        log.debug("getting LeaveMessage instance with relateId: " + relateId + "and inOutTag:" + inOutTag);
        String sql = " from LeaveMessage where relateId=? and inOutTag=?";
        try {
            Session session = getSession();
            Query query = session.createQuery(sql).setParameter(0, relateId).setParameter(1, inOutTag);
            List list = query.list();
            if (list != null && list.size() > 0) {
                return (LeaveMessage) query.list().get(0);
            }
        } catch (HibernateException he) {
            log.error(he);
        }
        return null;
    }
}