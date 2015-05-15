package com.sitexa.farm.service;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.FarmVisit;
import com.sitexa.farm.data.FarmVisitDAO;
import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.sys.Sequence;
import com.sitexa.user.data.Member;
import com.sitexa.user.service.MemberService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-3-17
 * Time: 9:27:39
 */
public class FarmVisitService {
    private static Log log = LogFactory.getLog(FarmVisitService.class);

    public static FarmVisit getById(String id) {
        if (id == null || "".equals(id)) return null;
        FarmVisitDAO dao = new FarmVisitDAO();
        return dao.findById(id);
    }

    public static void create(FarmVisit visit) {
        if (visit == null) return;
        String farmId = visit.getFarm().getFarmId();
        String visitorId = visit.getVisitor().getMemberId();
        Farm farm = FarmService.getById(farmId);
        if (farm == null) return;
        Member visitor = MemberService.getMember(visitorId);
        if (visitor == null) return;

        visit.setFarm(farm);
        visit.setVisitor(visitor);
        visit.setVisitId(Sequence.getId());
        visit.setCreateTime(new Date());

        FarmVisitDAO dao = new FarmVisitDAO();
        dao.save(visit);
    }

    @SuppressWarnings("unchecked")
    public static List<FarmVisit> getFarmVisitByFarm(Farm farm) {
        List<FarmVisit> result = new ArrayList<FarmVisit>();
        if (farm != null) {
            String hql = "from FarmVisit where farm.farmId=?";
            Session session = HibernateSessionFactory.getSession();
            result = session.createQuery(hql).setParameter(0, farm.getFarmId()).list();
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static List<Member> getVisitorByFarm(Farm farm, int cnt) {
        System.out.println("FarmVisitService.getVisitorByFarm");
        List<Member> result = new ArrayList<Member>();
        if (farm != null) {
            String hql = "select distinct model.visitor from FarmVisit as model where model.farm=?";
            Session session = HibernateSessionFactory.getSession();
            result = session.createQuery(hql).setParameter(0, farm).setMaxResults(cnt).list();
        }
        System.out.println("result.size() = " + result.size());
        return result;
    }

}
