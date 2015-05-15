package com.sitexa.post.data;

import com.sitexa.framework.hibernate.BaseDAO;
import com.sitexa.framework.exception.BaseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-4-8
 * Time: 20:11:39
 */
public class PostPictureDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(PostPictureDAO.class);
    // property constants
    public static final String PIC_TITLE = "picTitle";
    public static final String ABBREV = "abbrev";
    public static final String PIC_URL = "picUrl";
    public static final String DESCRIPTION = "description";

    public PostPictureDAO() {
        super("PostPicture", "picId");
    }

    public void save(PostPicture transientInstance) {
        log.debug("saving PostPicture instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void update(PostPicture transientInstance) {
        log.debug("saving PostPicture instance");
        try {
            super.update(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(String picId) throws BaseException {
        delete(findById(picId));
    }

    public void delete(PostPicture persistentInstance) {
        log.debug("deleting PostPicture instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public PostPicture findById(java.lang.String id) {
        log.debug("getting PostPicture instance with id: " + id);
        try {
            PostPicture instance = (PostPicture) getSession().get(
                    "com.sitexa.post.data.PostPicture", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(PostPicture instance) {
        log.debug("finding PostPicture instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.post.data.PostPicture").add(
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
        log.debug("finding PostPicture instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from PostPicture as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByPicTitle(Object picTitle) {
        return findByProperty(PIC_TITLE, picTitle);
    }

    public List findByAbbrev(Object abbrev) {
        return findByProperty(ABBREV, abbrev);
    }

    public List findByPicUrl(Object picUrl) {
        return findByProperty(PIC_URL, picUrl);
    }

    public List findByDescription(Object description) {
        return findByProperty(DESCRIPTION, description);
    }

    public List findAll() {
        log.debug("finding all PostPicture instances");
        try {
            String queryString = "from PostPicture";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public PostPicture merge(PostPicture detachedInstance) {
        log.debug("merging PostPicture instance");
        try {
            PostPicture result = (PostPicture) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PostPicture instance) {
        log.debug("attaching dirty PostPicture instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(PostPicture instance) {
        log.debug("attaching clean PostPicture instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
