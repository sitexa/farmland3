package com.sitexa.post.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * PollVote entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.sitexa.post.data.PollVote
 */

public class PollVoteDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(PollVoteDAO.class);
    public static final String CLASS_NAME = "PollVote";
    public static final String ID_NAME = "id";

    public PollVoteDAO() {
        super(CLASS_NAME, ID_NAME);
    }
    
    // property constants
    public static final String OPTION_INDEX = "optionIndex";
    public static final String COMMENT = "comment";

    public void save(PollVote transientInstance) {
        log.debug("saving PollVote instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(PollVote persistentInstance) {
        log.debug("deleting PollVote instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public PollVote findById(com.sitexa.post.data.PollVoteId id) {
        log.debug("getting PollVote instance with id: " + id);
        try {
            PollVote instance = (PollVote) getSession().get(
                    "com.sitexa.post.data.PollVote", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(PollVote instance) {
        log.debug("finding PollVote instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.post.data.PollVote").add(
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
        log.debug("finding PollVote instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from PollVote as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByOptionIndex(Object optionIndex) {
        return findByProperty(OPTION_INDEX, optionIndex);
    }

    public List findByComment(Object comment) {
        return findByProperty(COMMENT, comment);
    }

    public List findAll() {
        log.debug("finding all PollVote instances");
        try {
            String queryString = "from PollVote";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public PollVote merge(PollVote detachedInstance) {
        log.debug("merging PollVote instance");
        try {
            PollVote result = (PollVote) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PollVote instance) {
        log.debug("attaching dirty PollVote instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(PollVote instance) {
        log.debug("attaching clean PollVote instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}