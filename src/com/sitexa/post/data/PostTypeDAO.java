package com.sitexa.post.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;


public class PostTypeDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(PostTypeDAO.class);
    // property constants
    public static final String NAME = "name";
    public static final String OBJECT_ID = "objectId";

    public void save(PostType transientInstance) {
        log.debug("saving PostType instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(PostType persistentInstance) {
        log.debug("deleting PostType instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public PostType findById(java.lang.String id) {
        log.debug("getting PostType instance with id: " + id);
        try {
            PostType instance = (PostType) getSession().get(
                    "com.sitexa.post.data.PostType", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public PostType findByObjectAndName(String objectId, String name) {
        try {
            String hql = "from PostType where objectId=? and name=?";
            return (PostType) getSession().createQuery(hql).setParameter(0, objectId).setParameter(1, name).uniqueResult();
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(PostType instance) {
        log.debug("finding PostType instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.post.data.PostType").add(
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
        log.debug("finding PostType instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from PostType as model where model."
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

    public List findByObjectId(Object objectId) {
        return findByProperty(OBJECT_ID, objectId);
    }

    public List findAll() {
        log.debug("finding all PostType instances");
        try {
            String queryString = "from PostType";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public PostType merge(PostType detachedInstance) {
        log.debug("merging PostType instance");
        try {
            PostType result = (PostType) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PostType instance) {
        log.debug("attaching dirty PostType instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(PostType instance) {
        log.debug("attaching clean PostType instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}