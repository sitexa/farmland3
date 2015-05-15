package com.sitexa.site.service;

import com.sitexa.framework.exception.BaseException;
import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.sys.Sequence;
import com.sitexa.site.data.Site;
import com.sitexa.site.data.SiteMember;
import com.sitexa.site.data.SiteMemberDAO;
import com.sitexa.user.data.Member;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-5-5
 * Time: 16:59:11
 * description: 社区成员与友好社区是相互的,用户可以加某个社区为友好社区,这时SiteMember的status没设置.
 * 当社区管理员将SiteMember的status置为启用(1)的时候,该用户具有了该社区的发文权限;否则,该用户没有在该社区发文的权限;
 * 从用户角度看,只要加了某个社区为友好社区,该社区就在他的友好社区名单里了;
 * 该记录是管理员和用户共同维护的,即社区管理员可以增加/删除这条记录,用户也可以这么做.
 * 这一点跟好友不同,好友记录是单方面维护的,A用户加B用户为好友,B用户可同意,也可以不同意,但该用户总是在A用户的好友名单里了.
 * 如果B用户同意了,表示A与B互为好友.
 */
public class SiteMemberService {
    private static Log log = LogFactory.getLog(SiteMemberService.class);

    public static SiteMember getMember(String id) {
        SiteMemberDAO dao = new SiteMemberDAO();
        return dao.findById(id);
    }

    public static void updateMembers(List<SiteMember> members) throws BaseException {
        SiteMemberDAO dao = new SiteMemberDAO();
        dao.saveOrUpdate(members);
    }

    public static void delete(SiteMember member) throws BaseException {
        SiteMemberDAO dao = new SiteMemberDAO();
        try {
            dao.delete(member);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static void create(SiteMember siteMember) {
        System.out.println("SiteMemberService.create");
        SiteMemberDAO dao = new SiteMemberDAO();
        if (siteMember.getId() == null || "".equals(siteMember.getId()))
            siteMember.setId(Sequence.newId("post"));
        if (siteMember.getRemark().length() > 200) {
            siteMember.setRemark(siteMember.getRemark().substring(0, 200));
        }
        SiteMember sm = dao.findBySiteAndMember(siteMember.getSite(), siteMember.getMember());
        if (sm != null) return;
        dao.save(siteMember);
    }

    public static void update(SiteMember siteMember) {
        System.out.println("SiteMemberService.update");
        SiteMemberDAO dao = new SiteMemberDAO();
        if (siteMember.getRemark().length() > 200) {
            siteMember.setRemark(siteMember.getRemark().substring(0, 200));
        }
        SiteMember sm = dao.findById(siteMember.getId());
        if (sm == null) return;
        sm.setStatus(siteMember.getStatus());
        sm.setRemark(siteMember.getRemark());
        dao.update(sm);
    }

    public static SiteMember getSiteMemberBySiteAndMember(Site site, Member member) {
        SiteMemberDAO dao = new SiteMemberDAO();
        return dao.findBySiteAndMember(site, member);
    }

    @SuppressWarnings("unchecked")
    public static List<SiteMember> getSiteMemberByMember(Member member) {
        SiteMemberDAO dao = new SiteMemberDAO();
        return dao.findFriendSite(member);
    }

    @SuppressWarnings("unchecked")
    public static List<Member> getSiteMemberBySite(Site site) {
        List<Member> list = new ArrayList<Member>();
        if (site == null) return list;
        String typeId = site.getType().getTypeId();

        String hql;
        if ("1".equals(typeId) || "2".equals(typeId) || "3".equals(typeId)) {
            hql = " from SiteMember order by joinDate desc ";
        } else {
            List<Site> siteList = SiteService.getOpenedSubSite(site);
            String siteIdString = "'" + site.getSiteId() + "'";
            for (Site site1 : siteList) {
                siteIdString += ",'" + site1.getSiteId() + "'";
            }
            hql = " from SiteMember where site.siteId in (" + siteIdString + ") order by joinDate desc ";
        }

        List<SiteMember> list2 = new ArrayList<SiteMember>();
        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createQuery(hql);
            list2 = query.list();
        } catch (HibernateException he) {
            log.error(he);
        }

        if (list2.size() > 0) {
            HashMap hm = new HashMap();
            for (SiteMember siteMember : list2) {
                hm.put(siteMember.getMember().getMemberId(), siteMember.getMember());
            }
            list.addAll(hm.values());
        }

        return list;
    }

}
