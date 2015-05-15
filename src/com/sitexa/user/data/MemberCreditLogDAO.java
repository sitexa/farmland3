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
 * MemberCreditLog entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.user.data.MemberCreditLog
 */

public class MemberCreditLogDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(MemberCreditLogDAO.class);
    // property constants
    public static final String CONTENTS = "contents";
    public static final String AMOUNT = "amount";
    public static final String INC_DEC = "incDec";

    public void save(MemberCreditLog transientInstance) {
        log.debug("saving MemberCreditLog instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(MemberCreditLog persistentInstance) {
        log.debug("deleting MemberCreditLog instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public MemberCreditLog findById(java.lang.String id) {
        log.debug("getting MemberCreditLog instance with id: " + id);
        try {
            MemberCreditLog instance = (MemberCreditLog) getSession().get(
                    "com.sitexa.user.data.MemberCreditLog", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public MemberCreditLog findByMemberAndOrderNo(Member member, String orderNo) {
        try {
            String sql = " from MemberCreditLog as model where model.member=? and orderNo=?";
            return (MemberCreditLog) getSession().createQuery(sql).setParameter(0, member).setParameter(1, orderNo).uniqueResult();
        } catch (RuntimeException re) {
            log.error("findByMemberAndOrderNo failed", re);
            throw re;
        }
    }

    public List findByExample(MemberCreditLog instance) {
        log.debug("finding MemberCreditLog instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.user.data.MemberCreditLog").add(
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
        log.debug("finding MemberCreditLog instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from MemberCreditLog as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByContents(Object contents) {
        return findByProperty(CONTENTS, contents);
    }

    public List findByAmount(Object amount) {
        return findByProperty(AMOUNT, amount);
    }

    public List findByIncDec(Object incDec) {
        return findByProperty(INC_DEC, incDec);
    }

    public List findAll() {
        log.debug("finding all MemberCreditLog instances");
        try {
            String queryString = "from MemberCreditLog";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public MemberCreditLog merge(MemberCreditLog detachedInstance) {
        log.debug("merging MemberCreditLog instance");
        try {
            MemberCreditLog result = (MemberCreditLog) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MemberCreditLog instance) {
        log.debug("attaching dirty MemberCreditLog instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(MemberCreditLog instance) {
        log.debug("attaching clean MemberCreditLog instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}