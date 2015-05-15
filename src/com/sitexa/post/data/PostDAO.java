package com.sitexa.post.data;

import com.sitexa.framework.hibernate.BaseDAO;
import com.sitexa.site.data.Site;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for Post
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.sitexa.post.data.Post
 */

public class PostDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(PostDAO.class);
    public static final String CLASS_NAME = "Post";
    public static final String ID_NAME = "id";

    public PostDAO() {
        super(CLASS_NAME, ID_NAME);
    }

    // property constants
    public static final String NAME = "name";
    public static final String STATUS = "status";
    public static final String CONTENT = "content";

    public void save(Post transientInstance) {
        log.debug("saving Post instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void update(Post post) {
        try {
            super.update(post);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }

    }

    public void saveOrUpdate(Post post) {
        try {
            super.saveOrUpdate(post);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }

    }

    public void delete(Post persistentInstance) {
        log.debug("deleting Post instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Post findById(java.lang.String id) {
        log.debug("getting Post instance with id: " + id);
        try {
            Post instance = (Post) getSession().get(
                    "com.sitexa.post.data.Post", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Post instance) {
        log.debug("finding Post instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.post.data.Post").add(
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
        log.debug("finding Post instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Post as model where model."
                    + propertyName + "= ? order by createDate desc";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findBySite(Site site) {
        try {
            String hql = " from Post  where site =? and parent is null order by createDate desc";
            Query query = getSession().createQuery(hql).setParameter(0, site);
            return query.list();
        } catch (RuntimeException re) {
            log.error("findBySiteAndCategory failed", re);
            throw re;
        }
    }

    public List findByCategory(Category category) {
        try {
            String hql = " from Post  where category=? and parent is null order by createDate desc";
            Query query = getSession().createQuery(hql).setParameter(0, category);
            return query.list();
        } catch (RuntimeException re) {
            log.error("findBySiteAndCategory failed", re);
            throw re;
        }
    }

    public List findBySiteAndCategory(Site site, Category category) {
        try {
            String hql = " from Post  where site =? and category=? and parent is null order by createDate desc";
            Query query = getSession().createQuery(hql).setParameter(0, site).setParameter(1, category);
            return query.list();
        } catch (RuntimeException re) {
            log.error("findBySiteAndCategory failed", re);
            throw re;
        }
    }

    public List findByName(Object name) {
        return findByProperty(NAME, name);
    }

    public List findByStatus(Object status) {
        return findByProperty(STATUS, status);
    }

    public List findByContent(Object content) {
        return findByProperty(CONTENT, content);
    }

    public List findAll() {
        log.debug("finding all Post instances");
        try {
            String queryString = "from Post order by createDate desc";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Post merge(Post detachedInstance) {
        log.debug("merging Post instance");
        try {
            Post result = (Post) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Post instance) {
        log.debug("attaching dirty Post instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Post instance) {
        log.debug("attaching clean Post instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}