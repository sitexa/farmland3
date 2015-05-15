package com.sitexa.user.service;

import com.sitexa.framework.exception.BaseRuntime;
import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.site.data.Site;
import com.sitexa.site.data.SiteMember;
import com.sitexa.site.data.SiteMemberDAO;
import com.sitexa.site.data.SiteType;
import com.sitexa.site.service.SiteMemberService;
import com.sitexa.site.service.SiteService;
import com.sitexa.site.service.SiteTypeService;
import com.sitexa.sys.Sequence;
import com.sitexa.user.data.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.*;

/**
 * User: xnpeng
 * Date: 2009-4-3
 * Time: 21:02:46
 */
public class MemberService {

    private static Log log = LogFactory.getLog(MemberService.class);

    public static void main(String[] args) {
        Member m = getMember("1000088");
        Set set = m.getFriends();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Friend friend = (Friend) it.next();
            String s = friend.getFellow().getRealname();
            System.out.println("s = " + s);
        }
    }

    public static Member getMember(String id) {
        return (new MemberDAO()).findById(id);
    }

    //小区会员(不含外部)

    @SuppressWarnings("unchecked")
    public static List<MemberPicture> getMemberPictures(Site site, int num) {
        List<MemberPicture> list = new ArrayList<MemberPicture>();
        if (site == null) return list;
        String typeId = site.getType().getTypeId();

        String sql;
        if ("1".equals(typeId) || "2".equals(typeId) || "3".equals(typeId)) {
            sql = "select * from (select * ,row_number() over (partition by memberid order by memberid) as rn from v_member) as t where rn=1 order by memberid desc ";
        } else {
            String idList = "'" + site.getSiteId() + "'";
            List<Site> siteList = SiteService.getOpenedSubSite(site);
            for (Site site1 : siteList) {
                idList += ",'" + site1.getSiteId() + "'";
            }
            sql = "select * from (select * ,row_number() over (partition by memberid order by memberid) as rn from v_member) as t " +
                    " where rn=1 and siteid in (" + idList + ") order by memberid desc ";
        }

        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(sql).addEntity(MemberPicture.class);
            query.setMaxResults(num);
            list = query.list();
        } catch (HibernateException he) {
            log.error(he);
        }
        return list;
    }

    //小区所有会员(含外部会员)

    @SuppressWarnings("unchecked")
    public static List<MemberPicture> getSiteMemberPictures(Site site, int num) {
        List<MemberPicture> list = new ArrayList<MemberPicture>();
        if (site == null) return list;

        String idList = "''";
        List<Member> list2 = SiteMemberService.getSiteMemberBySite(site);
        for (Object aList2 : list2) {
            Member siteMember = (Member) aList2;
            idList += ",'" + siteMember.getMemberId() + "'";
        }

        String sql = "select * from (select * ,row_number() over (partition by memberid order by memberid) as rn from v_member) as t " +
                "where rn=1  and memberid in (" + idList + ") order by memberid desc ";
        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(sql).addEntity(MemberPicture.class);
            query.setMaxResults(num);
            list = query.list();
        } catch (HibernateException he) {
            log.error(he);
        }
        return list;

    }

    @SuppressWarnings("unchecked")
    public static List<Member> getInnerSiteMember(Site site, int num) {
        List<Member> result = new ArrayList<Member>();
        if (site == null) return result;
        String typeId = site.getType().getTypeId();

        String sql;
        if ("1".equals(typeId) || "2".equals(typeId) || "3".equals(typeId)) {
            sql = " from Member order by updateDate ";
        } else {
            List<Site> siteList = SiteService.getOpenedSubSite(site);
            String siteIdString = "'" + site.getSiteId() + "'";
            for (Site site1 : siteList) {
                siteIdString += ",'" + site1.getSiteId() + "'";
            }
            sql = " from Member where site.siteId in (" + siteIdString + ") order by updateDate ";
        }

        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createQuery(sql).setMaxResults(num);
            result = query.list();
        } catch (HibernateException he) {
            log.error(he);
        }

        return result;
    }

    public static List<Member> getAllSiteMember(Site site, int num) {
        List<Member> result = new ArrayList<Member>();
        if (site == null) return result;
        String typeId = site.getType().getTypeId();

        if ("1".equals(typeId) || "2".equals(typeId) || "3".equals(typeId)) {
            return getInnerSiteMember(site, num);
        } else {
            result = SiteMemberService.getSiteMemberBySite(site);
            HashMap<String, Member> hm = new HashMap<String, Member>();
        }
        result = result.size() <= num ? result : result.subList(0, num);
        return result;
    }

    public static void checkLength(Member member) {
        if (member.getNickname() != null && member.getNickname().length() > 50) member.setNickname(member.getNickname().substring(0, 50));
        if (member.getRealname() != null && member.getRealname().length() > 25) member.setRealname(member.getRealname().substring(0, 25));
        if (member.getTelephone() != null && member.getTelephone().length() > 25) member.setTelephone(member.getTelephone().substring(0, 25));
        if (member.getMobilephone() != null && member.getMobilephone().length() > 25) member.setMobilephone(member.getMobilephone().substring(0, 25));

    }

    //创建会员时,自动加入所属社区.但....删除,或者变更的时候呢? delete cascade? 当更换社区时?

    public static void create(Member member) {
        try {
            //todo...暂时只允许创建普通会员.
            if (!"1".equals(member.getType().getTypeId())) {
                BaseRuntime.threw(MemberService.class, "只能创建普通会员");
            }

            checkLength(member);

            MemberDAO dao = new MemberDAO();
            dao.save(member);

            if (member.getSite() != null) {
                SiteMember siteMember = new SiteMember();
                siteMember.setId(Sequence.getId());
                siteMember.setSite(member.getSite());
                siteMember.setMember(member);
                siteMember.setType(SiteMember.TYPE_INNER);
                siteMember.setJoinDate(new Date());
                siteMember.setRemark("注册自动加入.");
                SiteMemberDAO dao2 = new SiteMemberDAO();
                dao2.save(siteMember);
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    //todo...删除会员...
    //删除会员时,关联表得不到关联删除.所以,删除会员几乎是不可能的.只能禁用.

    public static void delete(Member member) {
        MemberDAO dao = new MemberDAO();
        dao.delete(member);
    }

    //更新会员时,不管别的表,但一定要更新其所在社区的成员表.SiteMember,在外层逻辑完成.

    public static void update(Member member) {
        System.out.println("MemberService.update");
        //todo...暂时只允许创建普通会员.
        if (!"1".equals(member.getType().getTypeId())) {
            BaseRuntime.threw(MemberService.class, "会员类型只能是普通会员");
        }

        Site site = null, site2;
        SiteType siteType = null, siteType2 = null;

        MemberDAO dao = new MemberDAO();

        Member member2 = dao.findById(member.getMemberId());

        if (member2 == null) return;

        site2 = member2.getSite();
        if (site2 != null && site2.getSiteId() != null) {
            siteType2 = SiteTypeService.getSiteType(site2.getSiteId());
        }

        member2.setRealname(member.getRealname());
        member2.setNickname(member.getNickname());
        member2.setGender(member.getGender());
        member2.setMobilephone(member.getMobilephone());
        member2.setTelephone(member.getTelephone());
        member2.setBirthday(member.getBirthday());
        member2.setUpdateDate(new Date());
        member2.setIntroduction(member.getIntroduction());

        if (member.getSite() != null && member.getSite().getSiteId() != null) {
            site = SiteService.getSite(member.getSite().getSiteId());
            siteType = SiteTypeService.getSiteType(site.getType().getTypeId());

            if (siteType != null && siteType2 != null) {
                if (!siteType.getTypeId().equalsIgnoreCase(siteType2.getTypeId())) {
                    if ("1".equals(siteType.getTypeId())
                            || "2".equals(siteType.getTypeId())
                            || "3".equals(siteType.getTypeId())) {
                        BaseRuntime.threw(MemberService.class, "只能加入乡、镇、街道及以下级别的社区");
                    }
                }
            } else if (siteType != null) {
                if ("1".equals(siteType.getTypeId())
                        || "2".equals(siteType.getTypeId())
                        || "3".equals(siteType.getTypeId())) {
                    BaseRuntime.threw(MemberService.class, "只能加入乡、镇、街道及以下级别的社区");
                }
            }
        }

        member2.setSite(site);
        member2.setStatus(member.getStatus());

        checkLength(member2);

        dao.update(member2);

        if (site != null) {
            if (site2 != null) {
                if (!site.getSiteId().equals(site2.getSiteId())) {
                    //1,改变原始社区会员类型;
                    SiteMember siteMember = SiteMemberService.getSiteMemberBySiteAndMember(site2, member2);
                    if (siteMember != null) {
                        siteMember.setType(SiteMember.TYPE_OUTTER);
                        siteMember.setRemark("改变社区自动改变");
                        SiteMemberService.update(siteMember);
                    }
                }
            }
            //2,加入新社区会员;
            SiteMember siteMember2 = new SiteMember();
            siteMember2.setType(SiteMember.TYPE_INNER);
            siteMember2.setSite(site);
            siteMember2.setMember(member2);
            siteMember2.setJoinDate(new Date());
            siteMember2.setRemark("改变社区自动加入!");
            siteMember2.setStatus(SiteMember.STATUS_ACT);
            SiteMemberService.create(siteMember2);
        }
    }

    //更新会员时,不管别的表,但一定要更新其所在社区的成员表.SiteMember,在外层逻辑完成.
    //管理员修改用户时，不受用户类型和社区级别限制。

    public static void adminUpdate(Member member) {
        System.out.println("MemberService.adminUpdate");
        //todo...暂时只允许创建普通会员.

        Site site = null, site2;

        MemberDAO dao = new MemberDAO();

        Member member2 = dao.findById(member.getMemberId());

        if (member2 == null) return;

        site2 = member2.getSite();

        member2.setRealname(member.getRealname());
        member2.setNickname(member.getNickname());
        member2.setGender(member.getGender());
        member2.setMobilephone(member.getMobilephone());
        member2.setTelephone(member.getTelephone());
        member2.setBirthday(member.getBirthday());
        member2.setUpdateDate(new Date());
        member2.setIntroduction(member.getIntroduction());
        member2.setStarTag(member.getStarTag());
        member2.setType(member.getType());

        if (member.getSite() != null && member.getSite().getSiteId() != null) {
            site = SiteService.getSite(member.getSite().getSiteId());
        }

        member2.setSite(site);

        checkLength(member2);

        dao.update(member2);

        if (site != null) {
            if (site2 != null) {
                if (!site.getSiteId().equals(site2.getSiteId())) {
                    //1,改变原始社区会员类型;
                    SiteMember siteMember = SiteMemberService.getSiteMemberBySiteAndMember(site2, member2);
                    if (siteMember != null) {
                        siteMember.setType(SiteMember.TYPE_OUTTER);
                        siteMember.setRemark("改变社区自动改变");
                        SiteMemberService.update(siteMember);
                    }
                }
            }
            //2,加入新社区会员;
            SiteMember siteMember2 = new SiteMember();
            siteMember2.setType(SiteMember.TYPE_INNER);
            siteMember2.setSite(site);
            siteMember2.setMember(member2);
            siteMember2.setJoinDate(new Date());
            siteMember2.setRemark("改变社区自动加入!");
            siteMember2.setStatus(SiteMember.STATUS_ACT);
            SiteMemberService.create(siteMember2);
        }
    }

    public static void setStarAndIntroduct(Member member) {
        if (member == null) return;
        MemberDAO dao = new MemberDAO();
        Member member2 = dao.findById(member.getMemberId());

        member2.setIntroduction(member.getIntroduction());
        member2.setStarTag(member.getStarTag());

        dao.update(member2);
    }

    public static void visit(Member member, Member guest) {
        VisitorDAO dao = new VisitorDAO();
        Visitor visitor = dao.findByGuest(member, guest);
        if (visitor == null) {
            visitor = new Visitor();
            visitor.setId(Sequence.getId());
            visitor.setMember(member);
            visitor.setGuest(guest);
            visitor.setVisitDate(new Date());
            visitor.setTimes(1);
            try {
                dao.save(visitor);
            } catch (Exception e) {
                log.error(e);
            }
        } else {
            visitor.setVisitDate(new Date());
            visitor.setTimes(visitor.getTimes() + 1);
            try {
                dao.update(visitor);
            } catch (Exception e) {
                log.error(e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Site> getFriendSite(Member member) {
        List<Site> sites = new ArrayList<Site>();
        SiteMemberDAO dao = new SiteMemberDAO();
        List<SiteMember> smList = dao.findFriendSite(member);
        for (SiteMember sm : smList) {
            sites.add(sm.getSite());
        }
        return sites;
    }

    public static MemberType getMemberType(String id) {
        MemberTypeDAO dao = new MemberTypeDAO();
        return dao.findById(id);
    }

    @SuppressWarnings("unchecked")
    public static List<MemberType> getMemberTypeList() {
        MemberTypeDAO dao = new MemberTypeDAO();
        return dao.findAll();
    }

    @SuppressWarnings("unchecked")
    public static List<Member> getNewMembers(int cnt) {
        List result = new ArrayList();
        String hql = "from User order by registerDate desc";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setMaxResults(cnt);
        List list = query.list();
        for (Object aList : list) {
            User user = (User) aList;
            result.add(user.getMember());
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static List<Member> getNewMembers(boolean status, int cnt) {
        List result = new ArrayList();
        String hql = "from User where status=? order by registerDate desc";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setParameter(0, status).setMaxResults(cnt);
        List list = query.list();
        for (Object aList : list) {
            User user = (User) aList;
            result.add(user.getMember());
        }
        return result;
    }

    public static List<Member> search(String userName, Page page) {
    	if(userName==null)	userName="";
        String hql = "from User where username like ? order by registerDate desc";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setParameter(0, "%" + userName + "%");

        int t = query.list().size();
        int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
        page.setTotal(tot);

        query.setMaxResults(page.getPageSize()).setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
        return query.list();
    }
    
    public static boolean applyLander(Member member) {
    	if(member==null) return false;
    	MemberDAO dao = new MemberDAO();
    	Member member2 = dao.findById(member.getMemberId());
    	if(member2==null) return false;
    	member2.setStatus("x");
    	dao.update(member2);
    	return true;
    }
    
}
