package com.sitexa.user.service;

import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.sys.Sequence;
import com.sitexa.user.data.LeaveMessage;
import com.sitexa.user.data.LeaveMessageDAO;
import com.sitexa.user.data.Member;
import com.sitexa.user.data.MemberDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-4-2
 * Time: 21:01:21
 */
public class LeaveMessageService {
    private static Log log = LogFactory.getLog(LeaveMessageService.class);

    public static void main(String[] args) {

    }

    public static LeaveMessage findById(String id) {
        LeaveMessageDAO dao = new LeaveMessageDAO();
        return dao.findById(id);
    }

    public static void saveReply(LeaveMessage leaveMessage) {
        LeaveMessageDAO dao = new LeaveMessageDAO();
        MemberDAO mDao = new MemberDAO();
        Member sender = mDao.findById(leaveMessage.getSender().getMemberId());
        Member receiver = mDao.findById(leaveMessage.getReceiver().getMemberId());
        if (sender == null || receiver == null) return;

        LeaveMessage parent = dao.findById(leaveMessage.getParent().getId());
        String relateId = Sequence.getId();
        //存入发件人的发件箱
        LeaveMessage lm = new LeaveMessage();
        lm.setId(Sequence.getId());
        lm.setRelateId(relateId);
        lm.setTitle(leaveMessage.getTitle());
        lm.setSender(sender);
        lm.setReceiver(receiver);
        lm.setMessage(leaveMessage.getMessage());
        lm.setSendDate(new Date());
        lm.setReadTag(0);
        lm.setParent(parent);
        lm.setInOutTag(1);
        dao.save(lm);
        //存入收件人的收件箱
        LeaveMessage lm1 = new LeaveMessage();
        lm1.setId(Sequence.getId());
        lm1.setRelateId(relateId);
        lm1.setTitle(lm.getTitle());
        lm1.setSender(lm.getSender());
        lm1.setReceiver(lm.getReceiver());
        lm1.setMessage(lm.getMessage());
        lm1.setSendDate(lm.getSendDate());
        lm1.setReadTag(lm.getReadTag());
        lm1.setParent(dao.findByRelateIdAndInOutTag(parent.getRelateId(), 1));
        lm1.setInOutTag(0);
        dao.save(lm1);
    }

    /**
     * @param member
     * @param type   0：收件箱；1：发件箱
     * @return
     */
    public static List<LeaveMessage> getleaveMessagesByPage(Member member, int type, Page page) {
        List<LeaveMessage> result = new ArrayList<LeaveMessage>();
        String sql = "";
        if (type == 0) {
            sql = " from LeaveMessage where receiver=? and inOutTag=0 order by sendDate desc";
        } else {
            sql = " from LeaveMessage where sender=? and inOutTag=1 order by sendDate desc";
        }
        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createQuery(sql).setParameter(0, member);

            int t = query.list().size();
            int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
            page.setTotal(tot);

            query.setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            query.setMaxResults(page.getPageSize());

            result = query.list();
        } catch (HibernateException he) {
            log.error(he);
        }
        return result;
    }

    public static boolean delete(String[] ids) {
        if (ids == null || ids.length <= 0) return false;
        boolean success = false;
        LeaveMessageDAO dao = new LeaveMessageDAO();
        LeaveMessage leaveMessage;
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        tx.begin();
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] != null && !ids[i].equals("")) {
                leaveMessage = dao.findById(ids[i]);
                if (leaveMessage != null) {
                    dao.delete(leaveMessage);
                }
            }
        }
        success = true;
        tx.commit();
        return success;
    }

    public static int getleaveMessageCount(Member member, int type) {
        int count = 0;
        String sql = "";
        if (type == 0) {
            sql = "select count(*) from LeaveMessage where receiver=? and inOutTag=0";
        } else {
            sql = "select count(*)  from LeaveMessage where sender=? and inOutTag=1";
        }

        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createQuery(sql).setParameter(0, member);
            count = Integer.parseInt(query.list().get(0).toString());
        } catch (HibernateException he) {
            log.error(he);
        }
        return count;
    }

    public static int getNewLeaveMessageCount(Member member) {
        int count = 0;
        String sql = "select count(*)  from LeaveMessage where receiver=? and inOutTag=0 and readTag=0";
        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createQuery(sql).setParameter(0, member);
            count = Integer.parseInt(query.list().get(0).toString());
        } catch (HibernateException he) {
            log.error(he);
        }
        return count;
    }
    
    public static boolean read(String id) {
        boolean success = false;
        LeaveMessageDAO dao = new LeaveMessageDAO();
        LeaveMessage lm = dao.findById(id);
        if (lm != null) {
            lm.setReadTag(1);
            dao.save(lm);

            LeaveMessage lm1 = dao.findByRelateIdAndInOutTag(lm.getRelateId(), 1);
            if(lm1 != null){
	            lm1.setReadTag(1);
	            dao.save(lm1);
            }
            success = true;
        }
        return success;
    }

    public static void saveByReceivers(LeaveMessage leaveMessage, String ids) {
        LeaveMessageDAO dao = new LeaveMessageDAO();
        MemberDAO mDao = new MemberDAO();
        Member sender = mDao.findById(leaveMessage.getSender().getMemberId());
        List<Member> receiverList = mDao.findByIds(ids);
        if (sender == null || receiverList == null) return;

        LeaveMessage lmIn = new LeaveMessage();
        LeaveMessage lmOut = new LeaveMessage();

        leaveMessage.setSender(sender);
        leaveMessage.setSendDate(new Date());
        leaveMessage.setReadTag(0);

        String relateId;
        Member receiver;
        Iterator<Member> it = receiverList.iterator();
        while (it.hasNext()) {
            receiver = it.next();
            relateId = Sequence.getId();

            lmIn = leaveMessage.clone();
            lmIn.setId(Sequence.getId());
            lmIn.setRelateId(relateId);
            lmIn.setReceiver(receiver);
            lmIn.setInOutTag(0);
            dao.save(lmIn);

            lmOut = lmIn.clone();
            lmOut.setId(Sequence.getId());
            lmOut.setInOutTag(1);
            dao.save(lmOut);
        }
    }
}