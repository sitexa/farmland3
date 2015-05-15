package com.sitexa.farm.service;

import com.sitexa.farm.data.*;
import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.user.data.LeaveMessage;
import com.sitexa.user.data.Member;
import com.sitexa.user.service.LeaveMessageService;
import com.sitexa.user.service.MemberCreditBean;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-11-17
 * Time: 10:55:33
 */
@SuppressWarnings("unchecked")
public class FarmingService {

    public static List<Farming> getFarmings(Farm farm) {
        String hql = " from Farming where farm=? order by startTime desc";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setParameter(0, farm);

        return query.list();
    }

    public static List<Farming> getFarmingActive(Farm farm, Page page) {
        String hql = " select f.* from t_farming f,t_crops c where f.farmid=c.farmId and f.seedId = c.seedId and f.farmId = ? order by f.startTime desc";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createSQLQuery(hql).addEntity(Farming.class).setParameter(0, farm.getFarmId());

        int t = query.list().size();
        int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
        page.setTotal(tot);

        query.setMaxResults(page.getPageSize()).setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));

        return query.list();
    }

    public static List<Farming> getFarming(Farm farm, Page page) {
        String hql = " from Farming where farm=? order by startTime desc";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setParameter(0, farm);

        int t = query.list().size();
        int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
        page.setTotal(tot);

        query.setMaxResults(page.getPageSize()).setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));

        return query.list();
    }

    public static List<Farming> getFarming(Farm farm, Faction faction) {
        String hql = " from Farming where farm=? and faction=? order by startTime desc";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setParameter(0, farm).setParameter(1, faction);
        return query.list();
    }

    public static List<Farming> getFarmingActive(String farmId, String seedId, Page page) {
        String hql = " select f.* from t_farming f,t_crops c where f.farmid=c.farmId and f.seedId = c.seedId and f.farmId = ? and f.seedId=? order by f.startTime desc";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createSQLQuery(hql).addEntity(Farming.class).setParameter(0, farmId).setParameter(1, seedId);

        int t = query.list().size();
        int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
        page.setTotal(tot);

        query.setMaxResults(page.getPageSize()).setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));

        return query.list();
    }

    public static List<Farming> getFarming(String farmId, String seedId, Page page) {
        SeedDAO dao = new SeedDAO();
        Seed seed = dao.findById(seedId);
        FarmDAO dao1 = new FarmDAO();
        Farm farm = dao1.findById(farmId);
        if (seed != null && farm != null) {
            String hql = " from Farming where farm=? and seed=? order by startTime desc";
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createQuery(hql).setParameter(0, farm).setParameter(1, seed);

            int t = query.list().size();
            int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
            page.setTotal(tot);

            query.setMaxResults(page.getPageSize()).setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            return query.list();
        } else {
            return null;
        }
    }

    public static Farming getById(String id) {
        if (id == null || "".equals(id)) return null;
        FarmingDAO dao = new FarmingDAO();
        return dao.findById(id);
    }

    public static void destroy(Farming farming) {
        if (farming == null) return;
        FarmingDAO dao = new FarmingDAO();
        dao.delete(farming);
    }

    public static void sendMessage(Member member, Farming farming, String message) {
        LeaveMessage leaveMessage = new LeaveMessage();
        leaveMessage.setSender(member);
        leaveMessage.setTitle("取消命令信息");
        leaveMessage.setMessage(message);
        LeaveMessageService.saveByReceivers(leaveMessage, farming.getFarmer().getMemberId());
    }

    public static List getLandList(Member member) {
        if (member == null || "".equals(member.getMemberId())) return null;
        String sql = "select l.landId,l.landName,l.landType,l.address,ct.count from t_land as l" +
                " left join " +
                " (select landId,count(*) as count from (select l.landId,fa.farmingId from t_land as l left join t_farm as f on f.landId=l.landId and l.lordId=? left join t_farming as fa on fa.farmId=f.farmId and fa.state='1') as ct where farmingId is not null group by landId) as ct" +
                " on l.lordId=? and ct.landId=l.landId";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createSQLQuery(sql).setParameter(0, member.getMemberId()).setParameter(1, member.getMemberId());
        return query.list();
    }

    public static List getFarmList(Land land, Page page) {
        if (land == null || "".equals(land.getLandId())) return null;
        String sql = "select f.farmId,f.farmNo,f.farmName,m.realName,f.rentDate,ct.count from t_farm as f" +
                " left join" +
                " (select farmId,count(*) as count from (select f.farmId,fa.farmingId from t_farm as f left join t_farming as fa on f.landId=? and fa.farmId=f.farmId and fa.state='1') as ct where farmingId is not null group by farmId) as ct" +
                "  on f.landId=? and ct.farmId=f.farmId" +
                " left join t_member as m on f.memberId=m.memberId order by count desc";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createSQLQuery(sql).setParameter(0, land.getLandId()).setParameter(1, land.getLandId());

        int t = query.list().size();
        int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
        page.setTotal(tot);

        query.setMaxResults(page.getPageSize()).setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
        return query.list();
    }

    /**
     * 更新台帐，如果完成操作，则完成支付。
     * 如果操作人为场长，则由农庄长支付；否则由操作人支付。
     *
     * @param farming;
     */
    public static void update(Farming farming) {
        if (farming == null || farming.getFarmingId() == null) return;

        FarmingDAO dao = new FarmingDAO();
        Farming f = dao.findById(farming.getFarmingId());

        if (f == null) return;

        f.setAmount(farming.getAmount());
        f.setRemark(farming.getRemark());
        f.setState(farming.getState());

        try {
            dao.update(f);
            if (farming.getState().equals("1")) {

                MemberCreditBean mcb;
                Member farmer = f.getFarmer();
                Member user = f.getFarm().getMember();
                Member lord = f.getFarm().getLand().getLord();

                //如果操作人是场长，则农庄主支付；
                if (farmer.getMemberId().equals(lord.getMemberId())) {
                    mcb = new MemberCreditBean(user);
                } else
                //如果操作人不是场长，则由操作人支付；
                {
                    mcb = new MemberCreditBean(f.getFarmer());
                }

                int amt = Integer.parseInt(f.getAmount());
                String reason = f.getFarm().getFarmName() + " " + f.getFaction().getActionName();
                if (f.getSeed() != null) reason += " " + f.getSeed().getSeedName();
                mcb.pay(lord, amt, reason);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
