package com.sitexa.post.service;

import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.sys.Sequence;
import com.sitexa.post.data.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-4-28
 * Time: 23:57:30
 */
public class ActivityService extends PostService {

    private static Log log = LogFactory.getLog(ActivityService.class);

    public void create(Object obj) {
        System.out.println("ActivityService.create");

        Activity act = (Activity) obj;
        Post post = act.getPost();
        try {
            String id = Sequence.newId("post");

            act.setId(id);
            post.setId(id);
            post.setCreateDate(new Date());

            FilterService.filter(post);

            Session session = HibernateSessionFactory.getSession();
            Transaction tx = session.beginTransaction();
            try {
                tx.begin();
                session.save(post);
                session.save(act);
                tx.commit();
            } catch (RuntimeException re) {
                tx.rollback();
                log.error(re);
                throw re;
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static void update(Object obj) {
        Activity act = (Activity) obj;
        Post post = act.getPost();

        FilterService.filter(post);

        Post post2 = PostService.getPost(post.getId());
        post2.setName(post.getName());
        post2.setSubject(post.getSubject());
        post2.setContent(post.getContent());

        Activity act2 = getActivity(act.getId());

        act2.setStartDate(act.getStartDate());
        act2.setEndDate(act.getEndDate());
        act2.setJoinEndDate(act.getJoinEndDate());
        act2.setContact(act.getContact());
        act2.setExpense(act.getExpense());
        act2.setAddress(act.getAddress());

        act2.setPost(post2);

        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            session.update(post2);
            session.update(act2);
            tx.commit();
            session.close();
        } catch (RuntimeException re) {
            tx.rollback();
            log.error(re);
            throw re;
        }
    }

    public static Activity getActivity(String id) {
        ActivityDAO dao = new ActivityDAO();
        try {
            return dao.findById(id);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Participant> getParticipants(Activity activity) {
        ParticipantDAO dao = new ParticipantDAO();
        try {
            return dao.findByProperty("activity", activity);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static Participant getParticipant(String id) {
        ParticipantDAO dao = new ParticipantDAO();
        try {
            return dao.findById(id);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static void addParticipant(Participant participant) {
        System.out.println("ActivityService.addParticipant");
        if (participant != null) {
            if (participant.getId() == null)
                participant.setId(Sequence.newId("post"));
            ParticipantDAO dao = new ParticipantDAO();
            try {
                dao.save(participant);
            } catch (RuntimeException re) {
                re.printStackTrace();
                log.error(re);
                throw re;
            }
        }
    }
}
