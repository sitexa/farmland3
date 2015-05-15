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
 * Friend entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.user.data.Friend
 */

public class FriendDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(FriendDAO.class);
    // property constants
    public static final String COMMENT = "comment";
    public static final String REMARK = "remark";

    public void save(Friend transientInstance) {
        log.debug("saving Friend instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void update(Friend friend) {
        super.update(friend);
    }

    public void delete(Friend persistentInstance) {
        log.debug("deleting Friend instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Friend findById(java.lang.String id) {
        log.debug("getting Friend instance with id: " + id);
        try {
            Friend instance = (Friend) getSession().get(
                    "com.sitexa.user.data.Friend", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public Friend findByMemberAndFellow(Member member, Member fellow) {
        try {
            String hql = "from Friend as model where model.member=? and fellow=? and status='1'";
            return (Friend) getSession().createQuery(hql).setParameter(0, member).setParameter(1, fellow).uniqueResult();
        } catch (RuntimeException re) {
            log.error("findByMemberAndFellow failed", re);
            throw re;
        }
    }

    public List findApplyByMember(Member member) {
        try {
            String hql = "from Friend as model where model.fellow=? ";
            return getSession().createQuery(hql).setParameter(0, member).list();
        } catch (RuntimeException re) {
            log.error("findApplyByMember failed", re);
            throw re;
        }
    }

    public List findByExample(Friend instance) {
        log.debug("finding Friend instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.user.data.Friend").add(
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
        log.debug("finding Friend instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Friend as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByComment(Object comment) {
        return findByProperty(COMMENT, comment);
    }

    public List findByRemark(Object remark) {
        return findByProperty(REMARK, remark);
    }

    public List findAll() {
        log.debug("finding all Friend instances");
        try {
            String queryString = "from Friend";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Friend merge(Friend detachedInstance) {
        log.debug("merging Friend instance");
        try {
            Friend result = (Friend) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Friend instance) {
        log.debug("attaching dirty Friend instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Friend instance) {
        log.debug("attaching clean Friend instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}