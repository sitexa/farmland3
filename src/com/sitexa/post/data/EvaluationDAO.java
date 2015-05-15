package com.sitexa.post.data;

import com.sitexa.framework.exception.BaseException;
import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * Evaluation entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.post.data.Evaluation
 */

public class EvaluationDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(EvaluationDAO.class);
    public static final String CLASS_NAME = "Evaluation";
    public static final String ID_NAME = "evaId";

    public EvaluationDAO() {
        super(CLASS_NAME, ID_NAME);
    }

    // property constants
    public static final String COMMENT = "comment";
    public static final String SCORE = "score";
    public static final String USER_ID = "userId";

    public void save(Evaluation transientInstance) throws BaseException {
        super.save(transientInstance);
    }

    public void delete(Evaluation persistentInstance) {
        log.debug("deleting Evaluation instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Evaluation findById(java.lang.String id) {
        log.debug("getting Evaluation instance with id: " + id);
        try {
            Evaluation instance = (Evaluation) getSession().get(
                    "com.sitexa.post.data.Evaluation", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Evaluation instance) {
        log.debug("finding Evaluation instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.post.data.Evaluation").add(
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
        log.debug("finding Evaluation instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Evaluation as model where model."
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

    public List findByScore(Object score) {
        return findByProperty(SCORE, score);
    }

    public List findByUserId(Object userId) {
        return findByProperty(USER_ID, userId);
    }

    public List findAll() {
        log.debug("finding all Evaluation instances");
        try {
            String queryString = "from Evaluation";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Evaluation merge(Evaluation detachedInstance) {
        log.debug("merging Evaluation instance");
        try {
            Evaluation result = (Evaluation) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Evaluation instance) {
        log.debug("attaching dirty Evaluation instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Evaluation instance) {
        log.debug("attaching clean Evaluation instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}