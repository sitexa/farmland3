package com.sitexa.user.service;

import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.user.data.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-12-1
 * Time: 10:07:14
 */
public class MemberCreditService {

    private static Log log = LogFactory.getLog(MemberCreditService.class);

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        int y = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONDAY);
        int d = cal.get(Calendar.DAY_OF_MONTH);
        int h = cal.get(Calendar.HOUR_OF_DAY);
        int mm = cal.get(Calendar.MINUTE);
        int s = cal.get(Calendar.SECOND);
        System.out.println("y = " + y);
        System.out.println("m = " + m);
        System.out.println("d = " + d);

        System.out.println("h = " + h);
        System.out.println("mm = " + mm);
        System.out.println("s = " + s);

    }

    /**
     * 获取信用卡，如果不存在，则创建。
     *
     * @param id;
     * @return MemberCredit;
     */
    public static MemberCredit getMemberCredit(String id) {
        if (id == null) return null;
        MemberCredit mc = null;
        MemberCreditDAO dao = new MemberCreditDAO();
        mc = dao.findById(id);
        if (mc == null) {
            Member m = MemberService.getMember(id);
            if (m == null) return null;
            mc = new MemberCredit();
            mc.setMember(m);
            mc.setMemberId(m.getMemberId());
            mc.setCredit(0);
            mc.setPassword(m.getUser().getPassword());
            dao.save(mc);
        }
        return mc;
    }

    public static void create(MemberCredit mc) {
        if (mc == null) return;
        if (mc.getMemberId() == null || "".equals(mc.getMemberId())) return;
        try {
            MemberCreditDAO dao = new MemberCreditDAO();
            dao.save(mc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void topup(Member member, int point) {
        MemberCreditDAO dao = new MemberCreditDAO();
        MemberCredit mc1 = dao.findById(member.getMemberId());
        if (mc1 != null) {
            mc1.setCredit(mc1.getCredit() + point);
            dao.update(mc1);

            MemberCreditLog log = new MemberCreditLog();
            log.setMember(member);
            log.setAmount(point);
            log.setContents("topup");
            log.setEventDate(new Date());
            log.setIncDec(1);

            addLog(log);
        }
    }

    public static void refund(Member member, int point) {
        MemberCreditDAO dao = new MemberCreditDAO();
        MemberCredit mc1 = dao.findById(member.getMemberId());
        if (mc1 != null) {
            mc1.setCredit(mc1.getCredit() + point);
            dao.update(mc1);
            MemberCreditLog log = new MemberCreditLog();
            log.setMember(member);
            log.setAmount(point);
            log.setContents("refund");
            log.setEventDate(new Date());
            log.setIncDec(1);
            addLog(log);
        }
    }

    public static void addLog(MemberCreditLog log) {
        MemberCreditLogDAO dao = new MemberCreditLogDAO();
        dao.save(log);
    }

    @SuppressWarnings("unchecked")
    public static List<MemberCreditLog> getLog(Member member) {
        MemberCreditLogDAO dao = new MemberCreditLogDAO();
        return dao.findByProperty("member", member);
    }

    @SuppressWarnings("unchecked")
    public static List<MemberCreditLog> searchLog(Member member, Page page) {
        List result = new ArrayList<MemberCreditLog>();
        if (member == null) return result;
        String sql = "from MemberCreditLog as model where model.member=? order by model.eventDate desc";
        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createQuery(sql).setParameter(0, member);
            int t = query.list().size();
            int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
            page.setTotal(tot);

            query.setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            query.setMaxResults(page.getPageSize());

            result = query.list();
        } catch (Exception e) {
            log.error(e);
        }
        return result;
    }
}


