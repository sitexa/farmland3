package com.sitexa.site.data;

import com.sitexa.framework.hibernate.BaseDAO;
import com.sitexa.user.data.Member;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * SiteMember entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.site.data.SiteMember
 */

public class SiteMemberDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(SiteMemberDAO.class);
    // property constants
    public static final String TYPE = "type";
    public static final String REMARK = "remark";
    public static final String STATUS = "status";

    public void save(SiteMember transientInstance) {
        log.debug("saving SiteMember instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void update(SiteMember transientInstance) {
        log.debug("saving SiteMember instance");
        try {
            super.update(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void saveOrUpdate(List<SiteMember> members) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            int i = 0;
            for (SiteMember member : members) {
                session.merge(member);
                if (++i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            session.flush();
            session.clear();
            tx.commit();
        } catch (HibernateException e) {
            log.error(e);
            tx.rollback();
            throw e;
        }
    }

    public void delete(SiteMember persistentInstance) {
        log.debug("deleting SiteMember instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public SiteMember findById(java.lang.String id) {
        log.debug("getting SiteMember instance with id: " + id);
        try {
            SiteMember instance = (SiteMember) getSession().get(
                    "com.sitexa.site.data.SiteMember", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public SiteMember findBySiteAndMember(Site site, Member member) {
        try {
            String queryString = "from SiteMember as model where model.site=? and model.member = ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, site).setParameter(1, member);
            return (SiteMember) queryObject.uniqueResult();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByExample(SiteMember instance) {
        log.debug("finding SiteMember instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.site.data.SiteMember").add(
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
        log.debug("finding SiteMember instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from SiteMember as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByType(Object type) {
        return findByProperty(TYPE, type);
    }

    public List findByRemark(Object remark) {
        return findByProperty(REMARK, remark);
    }

    public List findByStatus(Object status) {
        return findByProperty(STATUS, status);
    }

    public List findFriendSite(Member member) {
        try {
            String sql = " from SiteMember as model where model.member=? and model.type='1'";
            return getSession().createQuery(sql).setParameter(0, member).list();
        } catch (RuntimeException re) {
            log.error(re);
            throw re;
        }
    }

    public List findAll() {
        log.debug("finding all SiteMember instances");
        try {
            String queryString = "from SiteMember";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public SiteMember merge(SiteMember detachedInstance) {
        log.debug("merging SiteMember instance");
        try {
            SiteMember result = (SiteMember) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(SiteMember instance) {
        log.debug("attaching dirty SiteMember instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(SiteMember instance) {
        log.debug("attaching clean SiteMember instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

}