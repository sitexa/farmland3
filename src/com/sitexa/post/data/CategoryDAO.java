package com.sitexa.post.data;

import com.sitexa.framework.hibernate.BaseDAO;
import com.sitexa.site.data.Site;
import com.sitexa.user.data.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * Category entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.post.data.Category
 */

public class CategoryDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(CategoryDAO.class);
    public static final String CLASS_NAME = "Category";
    public static final String ID_NAME = "cateId";

    public CategoryDAO() {
        super(CLASS_NAME, ID_NAME);
    }

    // property constants
    public static final String NAME = "name";

    public void save(Category transientInstance) {
        super.save(transientInstance);
    }

    public void update(Category category){
        super.update(category);
    }

    public void update(List<Category> categories) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            int i = 0;
            for (Category category : categories) {
                session.update(category);
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

    public void delete(Category persistentInstance) {
        log.debug("deleting Category instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Category findById(java.lang.String id) {
        log.debug("getting Category instance with id: " + id);
        try {
            Category instance = (Category) getSession().get(
                    "com.sitexa.post.data.Category", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Category instance) {
        log.debug("finding Category instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.post.data.Category").add(
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
        log.debug("finding Category instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Category as model where model."
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

    public List findAll() {
        log.debug("finding all Category instances");
        try {
            String queryString = "from Category order by ord ";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List findAllInUse() {
        log.debug("finding all Category instances");
        try {
            String queryString = "from Category where status='1' order by ord ";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List findAllforCommon() {
        try {
            String queryString = "from Category where type='1' and status='1' order by ord ";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List findBySite(Site site) {
        try {
            String queryString = "from Category where type='2' and site=? order by ord ";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, site);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List findByUser(User user) {
        try {
            String queryString = "from Category where type='3' and user=? order by ord ";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, user);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Category merge(Category detachedInstance) {
        log.debug("merging Category instance");
        try {
            Category result = (Category) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Category instance) {
        log.debug("attaching dirty Category instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Category instance) {
        log.debug("attaching clean Category instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}