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
 * Member entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.user.data.Member
 */

public class MemberDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(MemberDAO.class);
    public static final String CLASS_NAME = "Member";
    public static final String ID_NAME = "memberId";

    // property constants
    public static final String NICKNAME = "nickname";
    public static final String REALNAME = "realname";
    public static final String TELEPHONE = "telephone";
    public static final String MOBILEPHONE = "mobilephone";
    public static final String GENDER = "gender";

    public MemberDAO() {
        super(CLASS_NAME, ID_NAME);
    }

    public void save(Member transientInstance) {
        log.debug("saving Member instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void update(Member transientInstance) {
        log.debug("saving Member instance");
        try {
            super.update(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void saveOrUpdate(Member transientInstance) {
        log.debug("saving Member instance");
        try {
            super.saveOrUpdate(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        } 
    }

    public void delete(Member persistentInstance) {
        log.debug("deleting Member instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Member findById(java.lang.String id) {
        log.debug("getting Member instance with id: " + id);
        try {
            Member instance = (Member) getSession().get(
                    "com.sitexa.user.data.Member", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Member instance) {
        log.debug("finding Member instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.user.data.Member").add(
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
        log.debug("finding Member instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Member as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByNickname(Object nickname) {
        return findByProperty(NICKNAME, nickname);
    }

    public List findByRealname(Object realname) {
        return findByProperty(REALNAME, realname);
    }

    public List findByTelephone(Object telephone) {
        return findByProperty(TELEPHONE, telephone);
    }

    public List findByMobilephone(Object mobilephone) {
        return findByProperty(MOBILEPHONE, mobilephone);
    }

    public List findByGender(Object gender) {
        return findByProperty(GENDER, gender);
    }

    public List findAll() {
        log.debug("finding all Member instances");
        try {
            String queryString = "from Member";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Member merge(Member detachedInstance) {
        log.debug("merging Member instance");
        try {
            Member result = (Member) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Member instance) {
        log.debug("attaching dirty Member instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Member instance) {
        log.debug("attaching clean Member instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /**
     * 2010.4.9
     * @param names 收件人名称
     * @return
     */
    public List<Member> findByNames(String[] names) {
        log.debug("finding Member instance with names: " + names);
        try {
            String sql = "from Member where 1=2 ";
            for(int i=0; i<names.length; i++){
            	sql += " or realname='"+names[i]+"'";
            }
            Query queryObject = getSession().createQuery(sql);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by names failed", re);
            throw re;
        }
    }
    public List<Member> findByIds(String ids) {
        log.debug("finding Member instance with ids: " + ids);
        try {
            String sql = "from Member where memberId in (" + ids + ")";
            Query queryObject = getSession().createQuery(sql);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by names failed", re);
            throw re;
        }
    }
}