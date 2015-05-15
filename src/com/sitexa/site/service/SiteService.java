package com.sitexa.site.service;

import com.sitexa.framework.config.AppConfig;
import com.sitexa.framework.exception.BaseException;
import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.sys.Sequence;
import com.sitexa.framework.xmltree.XmlTree;
import com.sitexa.site.data.*;
import com.sitexa.user.data.Member;
import com.sitexa.user.service.MemberService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-5
 * Time: 8:54:08
 */
@SuppressWarnings("unchecked,deprecation")
public class SiteService {

    private static Log log = LogFactory.getLog(SiteService.class);
    private static HashSet hset = new HashSet();

    static {
        String hosts = AppConfig.getProperty("bannedhost");
        String[] ips = hosts.split(",");
        hset.addAll(Arrays.asList(ips));
    }

    public static void main(String[] args) throws BaseException {
        Site site = getRoot();
        System.out.println("site.getSiteId() = " + site.getSiteId());
        List list = getChildren(site);
        System.out.println("list.size() = " + list.size());
        String s = getSites(site.getSiteId());
        System.out.println("s = " + s);

    }

    public static Site getRoot() {
        SiteDAO dao = new SiteDAO();
        return dao.findById("100");
    }

    //todo...暂时允许将社区挂在县区一级,县区挂在省市一级(直辖市).                       
    public static boolean validate(Site site) {
        System.out.println("SiteService.validate");
        SiteType siteType = SiteTypeService.getSiteType(site.getType().getTypeId());
        SiteType parentType = getSite(site.getParent().getSiteId()).getType();

        return ("9".equals(siteType.getTypeId()) && ("6".equals(parentType.getTypeId())))
                || ("9".equals(siteType.getTypeId()) && ("5".equals(parentType.getTypeId())))
                || ("6".equals(siteType.getTypeId()) && ("5".equals(parentType.getTypeId())))
                || ("5".equals(siteType.getTypeId()) && ("4".equals(parentType.getTypeId())))
                || ("4".equals(siteType.getTypeId()) && ("3".equals(parentType.getTypeId())))
                || ("4".equals(siteType.getTypeId()) && ("2".equals(parentType.getTypeId())))
                || ("3".equals(siteType.getTypeId()) && ("2".equals(parentType.getTypeId())))
                || ("2".equals(siteType.getTypeId()) && ("1".equals(parentType.getTypeId())));
    }

    public static void checkLength(Site site) {
        if (site.getName() != null && site.getName().length() > 100) {
            site.setName(site.getName().substring(0, 100));
        }
        if (site.getAddress() != null && site.getAddress().length() > 250) {
            site.setAddress(site.getAddress().substring(0, 250));
        }
        if (site.getIntroduction() != null && site.getIntroduction().length() > 500) {
            site.setIntroduction(site.getIntroduction().substring(0, 500));
        }
    }

    public static void create(Site site) throws BaseException {
        System.out.println("SiteService.create");
        if (site == null) return;
        if (!validate(site)) BaseException.threw("上下级关系错误!");
        if (site.getSiteId() == null || site.getSiteId().equals(""))
            site.setSiteId(Sequence.getId());

        checkLength(site);

        site.setCreateDate(new Date());
        site.setStatus(true);
        SiteDAO dao = new SiteDAO();
        dao.save(site);
    }

    public static void createByMember(Site site, Member member) throws BaseException {
        if (site == null) return;
        if (!validate(site)) BaseException.threw("上下级关系错误!");
        if (site.getSiteId() == null || site.getSiteId().equals(""))
            site.setSiteId(Sequence.getId());

        member = MemberService.getMember(member.getMemberId());

        if (member == null) {
            BaseException.threw("会员为空!");
            return;
        }

        //1,新社区的管理员为创建者.
        site.setGovernor(member);
        site.setCreateDate(new Date());
        site.setStatus(true);

        checkLength(site);

        SiteDAO dao = new SiteDAO();
        dao.save(site);

        //2,自动添加创建者为社区会员.
        SiteMember siteMember = new SiteMember();
        siteMember.setId(Sequence.getId());
        siteMember.setMember(member);
        siteMember.setJoinDate(new Date());
        siteMember.setRemark("原始创建人");
        siteMember.setStatus(SiteMember.STATUS_ACT);
        siteMember.setType(SiteMember.TYPE_INNER);
        siteMember.setSite(site);

        SiteMemberDAO dao3 = new SiteMemberDAO();
        dao3.save(siteMember);

/*
        //3,如果该会员以前所加入的社区中是内部会员,则自动转为外部会员.
        SiteMember siteMember2 = SiteMemberService.getSiteMemberBySiteAndMember(member.getDefaultSite(), member);
        siteMember2.setType(SiteMember.TYPE_OUTTER);
        siteMember2.setRemark("该会员创建了新社区");
        dao3.update(siteMember2);

        //4,该会员所属社区改为新建社区.
        MemberDAO dao2 = new MemberDAO();
        member = dao2.findById(member.getMemberId());
        member.setSite(site);
        dao2.update(member);
*/

    }

    public static void delete(Site site) {
        SiteDAO dao = new SiteDAO();
        dao.delete(site);
    }

    public static void adminUpdate(Site site) throws BaseException {

        if (!validate(site)) BaseException.threw("上下级关系错误!");

        SiteDAO dao = new SiteDAO();
        Site site2 = dao.findById(site.getSiteId());

        site2.setName(site.getName());
        if (site.getParent() != null && site.getParent().getSiteId() != null) {
            Site p = SiteService.getSite(site.getParent().getSiteId());
            site2.setParent(p);
        } else {
            site2.setParent(null);
        }

        if (site.getType() != null && site.getType().getTypeId() != null) {
            SiteType t = SiteTypeService.getSiteType(site.getType().getTypeId());
            site2.setType(t);
        }
        site2.setAddress(site.getAddress());
        site2.setStatus(site.getStatus());
        if (site.getGovernor() != null && site.getGovernor().getMemberId() != null) {
            Member m = MemberService.getMember(site.getGovernor().getMemberId());
            site2.setGovernor(m);
        }
        site2.setIntroduction(site.getIntroduction());
        site2.setLatitude(site.getLatitude());
        site2.setLongitude(site.getLongitude());
        site2.setZoom(site.getZoom());

        checkLength(site2);

        dao.update(site2);
    }

    public static void update(Site site) throws BaseException {

        if (!validate(site)) BaseException.threw("上下级关系错误!");

        SiteDAO dao = new SiteDAO();
        Site site2 = dao.findById(site.getSiteId());

        site2.setName(site.getName());
        if (site.getParent() != null && site.getParent().getSiteId() != null) {
            Site p = SiteService.getSite(site.getParent().getSiteId());
            site2.setParent(p);
        } else {
            site2.setParent(null);
        }

        if (site.getType() != null && site.getType().getTypeId() != null) {
            if ("1".equals(site.getType().getTypeId())
                    || "2".equals(site.getType().getTypeId())) {
                BaseException.threw("您不能修改省级及以上级别的社区!!");
            }
            SiteType t = SiteTypeService.getSiteType(site.getType().getTypeId());
            site2.setType(t);
        }
        site2.setAddress(site.getAddress());
        site2.setStatus(site.getStatus());
        if (site.getGovernor() != null && site.getGovernor().getMemberId() != null) {
            Member m = MemberService.getMember(site.getGovernor().getMemberId());
            site2.setGovernor(m);
        }
        site2.setIntroduction(site.getIntroduction());
        site2.setLatitude(site.getLatitude());
        site2.setLongitude(site.getLongitude());
        site2.setZoom(site.getZoom());

        checkLength(site2);

        dao.update(site2);
    }

    public static Site getSite(String siteId) {
        if(siteId==null) return null;
        SiteDAO dao = new SiteDAO();
        return dao.findById(siteId);
    }

    public static boolean isBannedHost(String host) {
        return hset.contains(host);
    }

    public static List<Site> searchSiteByName(String name) {
        List<Site> result = new ArrayList<Site>();

        if (name == null || name.equals("")) return result;
        SiteDAO dao = new SiteDAO();
        return dao.findByName(name);
    }

    public static Site searchSiteByCityName(String cityname) {
        Site site = null;
        List<Site> list;
        Session sess = HibernateSessionFactory.getSession();
        try {
            Criteria c = sess.createCriteria(Site.class);
            c.add(Restrictions.like("fullname", "%" + cityname));
            c.addOrder(Order.asc("type"));
            list = c.list();
            for (Site aList : list) {
                site = aList;
                String typeid = site.getType().getTypeId();
                if (typeid.equals("3") || typeid.equals("4")) {
                    break;
                }
            }
        } catch (RuntimeException e) {
            log.error(e);
            throw e;
        }
        return site;
    }

    public static List<Site> getAllSite() {
        SiteDAO dao = new SiteDAO();
        return dao.findAll();
    }

    @SuppressWarnings("unchecked")
    public static List<Site> search(Page page, String word) {
        List<Site> result;
        Session sess = HibernateSessionFactory.getSession();
        try {
            Criteria c = sess.createCriteria(Site.class);
            c.add(Restrictions.like("name", "%" + word + "%"));

            int t = c.list().size();
            int tot = t / Page.SIZE_TWENTY + ((t % Page.SIZE_TWENTY) == 0 ? 0 : 1);
            page.setTotal(tot);

            c.setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            c.setMaxResults(page.getPageSize());
            result = c.list();
        } catch (RuntimeException e) {
            log.error(e);
            throw e;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static List<Site> search(Page page, String word, SiteType type) {
        List<Site> result;
        Session sess = HibernateSessionFactory.getSession();
        try {
            Criteria c = sess.createCriteria(Site.class);
            c.add(Restrictions.like("name", "%" + word + "%"));
            c.add(Restrictions.eq("type", type));

            int t = c.list().size();
            int tot = t / Page.SIZE_TWENTY + ((t % Page.SIZE_TWENTY) == 0 ? 0 : 1);
            page.setTotal(tot);

            c.setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            c.setMaxResults(page.getPageSize());
            result = c.list();
        } catch (RuntimeException e) {
            log.error(e);
            throw e;
        }
        return result;
    }

    public static List<Site> search(Page page, String word, SiteType type, boolean withChildren) throws BaseException {
        return searchWithChildren2(page, word, type.getTypeId());
    }

    @SuppressWarnings("deprecation")
    private static List<Site> searchWithChildren2(Page page, String word, String typeId) throws BaseException {
        List<Site> result = new ArrayList<Site>();
        Session session = HibernateSessionFactory.getSession();
        Connection conn = null;
        String sql = "select SiteId,Name,ParentId,TypeId,CreateDate,Address,Status from t_site where name like ? and typeId=? ";
        sql += "union select SiteId,Name,ParentId,TypeId,CreateDate,Address,Status from t_site where parentId in (select siteId from t_site where name like ? and typeId=? ) ";
        sql += "union select SiteId,Name,ParentId,TypeId,CreateDate,Address,Status from t_site where parentId in (select siteId from t_site where parentId in (select siteId from t_site where name like ? and typeId=? ))";
        sql += "union select SiteId,Name,ParentId,TypeId,CreateDate,Address,Status from t_site where parentId in (select siteId from t_site where parentId in (select siteId from t_site where parentId in (select siteId from t_site where name like ? and typeId=? ))) ";
        String sql2 = "select count(*) from (" + sql + ") as _tmp";
        try {
            conn = session.connection();
            PreparedStatement pst = conn.prepareStatement(sql2);
            pst.setString(1, "%" + word + "%");
            pst.setString(2, typeId);
            pst.setString(3, "%" + word + "%");
            pst.setString(4, typeId);
            pst.setString(5, "%" + word + "%");
            pst.setString(6, typeId);
            pst.setString(7, "%" + word + "%");
            pst.setString(8, typeId);

            ResultSet rs = pst.executeQuery();
            int tot = 0;
            while (rs.next()) {
                tot = rs.getInt(1);
            }
            int total = tot / Page.SIZE_TWENTY + ((tot % Page.SIZE_TWENTY) == 0 ? 0 : 1);
            page.setTotal(total);

            String sql3 = "select top " + page.getPageSize() + " * from (select *, row_number() over (order by siteId) as rowno from (";
            sql3 += sql;
            sql3 += ") as _tmp) as _tmp2 where rowno > " + (page.getCurrent() - 1) * (page.getPageSize());

            pst = conn.prepareStatement(sql3);
            pst.setString(1, "%" + word + "%");
            pst.setString(2, typeId);
            pst.setString(3, "%" + word + "%");
            pst.setString(4, typeId);
            pst.setString(5, "%" + word + "%");
            pst.setString(6, typeId);
            pst.setString(7, "%" + word + "%");
            pst.setString(8, typeId);

            rs = pst.executeQuery();

            while (rs.next()) {
                String siteId = rs.getString(1);
                String name = rs.getString(2);
                String parentId = rs.getString(3);
                Site p = (new SiteDAO()).findById(parentId);
                String typeId2 = rs.getString(4);
                SiteType t = (new SiteTypeDAO()).findById(typeId2);
                Date createDate = rs.getDate(5);
                String address = rs.getString(6);
                boolean status = rs.getBoolean(7);
                Site s = new Site(siteId, t, p, name, createDate, address, status);
                result.add(s);
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error(e);
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e);
            BaseException.threw(e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ignored) {
            }
            HibernateSessionFactory.closeSession(session);
        }
        return result;
    }

    /**
     * Hibernate can not do union select.replace with native select "searchWithChild2".
     *
     * @param page Page
     * @param word String
     * @param type SiteType
     * @return List<Site>
     * @throws BaseException exception
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    private static List<Site> searchWithChildren(Page page, String word, SiteType type) throws BaseException {
        List<Site> result = new ArrayList<Site>();
        Session session = HibernateSessionFactory.getSession();
        try {
            String hql = "from Site where name like ? and type=? ";
            hql += " union from Site where parent in(from Site where name like ? and type=?)";
            hql += " union from Site where parent in(from Site where parent in(from Site where name like ? and type=?))";
            hql += " union from Site where parent in(from Site where parent in(from Site where parent in(from Site where name like ? and type=?)))";

            Query query = session.createQuery(hql);
            query.setParameter(1, "%" + word + "%").setParameter(2, type)
                    .setParameter(3, "%" + word + "%").setParameter(4, type)
                    .setParameter(5, "%" + word + "%").setParameter(6, type)
                    .setParameter(7, "%" + word + "%").setParameter(8, type);

            int t = query.list().size();
            int tot = t / Page.SIZE_TWENTY + ((t % Page.SIZE_TWENTY) == 0 ? 0 : 1);
            page.setTotal(tot);

            query.setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            query.setMaxResults(page.getPageSize());

            result = query.list();

        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
            BaseException.threw(e);
        }
        return result;
    }

    //所有下级
    public static List<Site> getSubSite(Site site, int num, int dir) {
        List<Site> list = new ArrayList<Site>();
        if (site == null) return list;
        String siteId = site.getSiteId();
        String typeId = site.getType().getTypeId();
        String idLike;
        if ("2".equals(typeId)) {
            idLike = siteId.substring(0, 3);
        } else if ("3".equals(typeId)) {
            idLike = siteId.substring(0, 4);
        } else {
            idLike = siteId;
        }

        String typeString = "'5','6','9'";

        if (dir == 1)
            typeString = "'2','3','4'";

        String sql = "select * from t_site where typeId in(" + typeString + ") and siteId like '" + idLike + "%' order by typeId";

        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(sql).addEntity(Site.class);
            query.setMaxResults(num);
            list = query.list();
        } catch (HibernateException he) {
            log.error(he);
        }

        return list;
    }

    //所有下级
    public static List<Site> getOpenedSubSite(Site site) {
        List<Site> result = new ArrayList<Site>();
        if (site == null) return result;

        String typeId = site.getType().getTypeId();

        String sql;

        if ("1".equals(typeId) || "2".equals(typeId) || "3".equals(typeId)) {
            sql = "select * from t_site where governor is not null";
        } else if ("4".equals(typeId)) {
            List<Site> siteList1 = getOpenedChildSite(site);
            String idList1 = getIdList(siteList1);
            List<Site> siteList2 = getOpenedChildSite(idList1);
            siteList1.addAll(siteList2);
            String idList2 = getIdList(siteList2);
            List<Site> siteList3 = getOpenedChildSite(idList2);
            siteList1.addAll(siteList3);
            return siteList1;
        } else if ("5".equals(typeId)) {
            List<Site> siteList1 = getOpenedChildSite(site);
            String idList1 = getIdList(siteList1);
            List<Site> siteList2 = getOpenedChildSite(idList1);
            siteList1.addAll(siteList2);
            return siteList1;
        } else {
            sql = " select * from t_site where parentid='" + site.getSiteId() + "' and governor is not null ";
        }

        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(sql).addEntity(Site.class);
            result = query.list();
        } catch (HibernateException he) {
            log.error(he);
        }
        return result;
    }

    private static String getIdList(List<Site> siteList) {
        String idList = "";
        if (siteList == null || siteList.size() == 0) return idList;
        for (Site site : siteList) {
            idList += ",'" + site.getSiteId() + "'";
        }

        return idList.substring(1, idList.length());
    }

    //下一级(含自己)
    public static List<Site> getOpenedChildSite(Site site) {
        List<Site> result = new ArrayList<Site>();
        if (site == null) return result;

        String sql = "from Site where (siteId=? or parent=?) and governor is not null ";
        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createQuery(sql);
            query.setParameter(0, site.getSiteId()).setParameter(1, site);
            result = query.list();
        } catch (HibernateException he) {
            log.error(he);
        }
        return result;
    }

    //下一级(不含自己)
    public static List<Site> getOpenedChildSite(String idList) {
        List<Site> result = new ArrayList<Site>();
        if (idList == null || "".equals(idList)) return result;

        String sql = "select * from t_site where parentId in(" + idList + ") and governor is not null ";
        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(sql).addEntity(Site.class);
            result = query.list();
        } catch (HibernateException he) {
            log.error(he);
        }
        return result;

    }

    public static List<Site> getChildren(Site site) {
        SiteDAO dao = new SiteDAO();
        return dao.findByProperty("parent", site);
    }

    @SuppressWarnings("unchecked")
    public static List<Site> getSibling(Site site) {
        SiteDAO dao = new SiteDAO();
        Site parent = site.getParent();

        if (parent == null) {
            Query query;
            String hql = "from Site  where parent =? ";
            query = dao.getSession().createQuery(hql);
            query.setParameter(0, site.getParent());
            return query.list();
        } else {
            return getChildren(parent);
        }

    }

    @SuppressWarnings("unchecked")
    public static List<Site> getFriends(String siteId) {
        return new ArrayList();
    }

    @SuppressWarnings("unchecked")
    public static List<Site> getSiteByType(String typeId) {
        return new ArrayList();
    }

    @SuppressWarnings("unchecked")
    public static List<SiteType> getAllSiteTypes() {
        SiteTypeDAO dao = new SiteTypeDAO();
        return dao.findAll();
    }

    public static String getSiteTree(String siteId) {
        SiteTree root = new SiteTree(siteId);
        return root.getSubTree(root);
    }

    public static String getSites(String siteId) {
        String result = "";
        SiteDAO dao = new SiteDAO();
        Site site;

        if (siteId != null && siteId.length() != 0)
            site = dao.findById(siteId);
        else site = getRoot();

        List list = dao.findByProperty("parent", site);
        for (int i = 0; i < list.size(); i++) {
            Site s = (Site) list.get(i);
            if (i == 0) result += s.getSiteId() + "," + s.getName();
            else result += ";" + s.getSiteId() + "," + s.getName();
        }
        return result;
    }

    public static String getSiteTypes() {
        String result = "";
        SiteTypeDAO dao = new SiteTypeDAO();
        List list = dao.findAll();
        for (int i = 0; i < list.size(); i++) {
            SiteType type = (SiteType) list.get(i);
            if (i == 0) result += type.getTypeId() + "," + type.getTypeName();
            else result += ";" + type.getTypeId() + "," + type.getTypeName();
        }
        return result;
    }

    public static List<Site> getHotSites(Site site) {
        SiteDAO dao = new SiteDAO();
        try {
            return dao.findHot(10);
        } catch (BaseException ignored) {
        }
        return null;
    }

    public static List<Site> getHotSites() {
        SiteDAO dao = new SiteDAO();
        try {
            return dao.findHot(200);
        } catch (BaseException ignored) {
        }
        return null;
    }

    public static void incVwCnt(Site site) {
        if (site != null) {
            SiteDAO dao = new SiteDAO();
            try {
                site = dao.findById(site.getSiteId());
                if (site.getVwCnt() == null) site.setVwCnt(1);
                else site.setVwCnt(site.getVwCnt() + 1);
                dao.update(site);
            } catch (RuntimeException re) {
                re.printStackTrace();
                throw re;
            }
        }
    }
}

class SiteTree extends XmlTree {

    protected SiteTree(String id) {
        super.setId(id);
    }

    protected SiteTree(String id, String parentId, String text) {
        super.setId(id);
        super.setParentId(parentId);
        super.setText(text);
    }

    protected TreeSet<XmlTree> getData() {

        if (this.getId() == null) return null;

        TreeSet<XmlTree> set = new TreeSet<XmlTree>();

        Site site = (new SiteDAO()).findById(this.getId());
        Set<Site> children = site.getChildren();
        for (Site s : children) {
            XmlTree t = new SiteTree(s.getSiteId(), s.getParent().getSiteId(), s.getName() + "(" + s.getSiteId() + ")");
            set.add(t);
        }
        return set;
    }

}
