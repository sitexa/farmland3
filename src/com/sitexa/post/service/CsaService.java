package com.sitexa.post.service;

import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.post.data.*;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.sys.Sequence;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-12-3
 * Time: 10:41:23
 */
public class CsaService extends PostService {
    private static Log log = LogFactory.getLog(CsaService.class);
    private static Category CSA_CAT = CategoryService.getCategory("csa");

    public static Csa getCsa(String id) {
        CsaDAO dao = new CsaDAO();
        return dao.findById(id);
    }

    public void create(Object obj) {
        Csa msg = (Csa) obj;
        Post post = msg.getPost();
        try {
            Member creator = MemberService.getMember(post.getCreator().getMemberId());
            Site site = SiteService.getSite(post.getSite().getSiteId());
            Category category = CategoryService.getCategory(post.getCategory().getCateId());
            Post parent = null;
            if (post.getParent() != null
                    && post.getParent().getId() != null
                    && !post.getParent().getId().equals("")) {
                parent = getPost(post.getParent().getId());
            }

            String id = Sequence.newId("post");
            post.setId(id);
            post.setCategory(category);
            post.setCreator(creator);
            post.setSite(site);
            post.setParent(parent);
            post.setCreateDate(new Date());

            msg.setId(id);

            FilterService.filter(post);

            try {
                PostDAO dao = new PostDAO();
                dao.save(post);
                CsaDAO dao2 = new CsaDAO();
                dao2.save(msg);
            } catch (RuntimeException re) {
                log.error(re);
                throw re;
            }

        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static List<Post> search(Page page, String words, PostType csaType) {

        String[] strArr = words.split("[,+;+.+。+]|(\\s)+|(\\p{Punct})+");
        List<String> wordList = new ArrayList<String>();
        for (String s : strArr) {
            if (s != null && !s.equals(""))
                wordList.add(s);
        }

        String hql = "select * from v_csa where parentId is null ";
        hql += " and cateId='csa'";
        if (csaType != null)
            hql += " and csaType= '" + csaType.getTypeId() + "'";

        for (int i = 0; i < wordList.size(); i++) {
            String w = wordList.get(i);
            if (i == 0) {
                hql += " and (content like '%" + w + "%'";
            } else {
                hql += " or content like '%" + w + "%'";
            }
        }
        if (wordList.size() > 0)
            hql += ")";

        hql += " order by createDate desc";

        Session session = HibernateSessionFactory.getSession();
        try {
            Query query = session.createSQLQuery(hql).addEntity(Post.class);

            int t = query.list().size();
            int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
            page.setTotal(tot);

            query.setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            query.setMaxResults(page.getPageSize());

            return query.list();
        } catch (RuntimeException re) {
            log.error(re);
            throw re;
        }
    }

    public static List<Post> getNewNotice(int cnt) {
        List<Post> result = new ArrayList<Post>();

        String sql = "select * from v_csa where parentId is null and csatype='110' and datediff(day,createdate,getdate())<30 ";
        sql += " order by createDate desc";

        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(sql).addEntity(Post.class).setMaxResults(cnt);
            result = query.list();
        } catch (HibernateException he) {
            log.error(he);
        }
        return result;
    }

    public static List<Post> getNewPosts(int cnt) {
        String sql = "select * from v_csa where csaType<>'110' and  parentId is null  order by createDate desc ";
        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(sql).addEntity(Post.class).setMaxResults(cnt);
            return query.list();
        } catch (HibernateException he) {
            log.error(he);
            throw he;
        }
    }

    public static List<Post> getNewPosts(String csaType, int cnt) {
        System.out.println("CsaService.getNewPosts");
        List<Post> result = new ArrayList<Post>();

        String sql = "select * from v_csa where parentId is null and csatype=? ";
        sql += " order by createDate desc";

        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(sql).addEntity(Post.class).setParameter(0, csaType).setMaxResults(cnt);
            result = query.list();
        } catch (HibernateException he) {
            log.error(he);
            throw he;
        }
        return result;
    }

    public static List<PostPicture> getNewPostPictures(int cnt) {
        System.out.println("CsaService.getNewPostPictures");
        List<PostPicture> result;

        String sql = "select * from (select *,row_number() over (partition by postid order by postid) as rn from v_post_pic) as t " +
                " where rn=1 and cateId='csa' ";

        sql += " order by createDate desc";

        Session session = HibernateSessionFactory.getSession();
        try {
            Query query = session.createSQLQuery(sql).addEntity(PostPicture.class);
            query.setMaxResults(cnt);
            result = query.list();
        } catch (RuntimeException re) {
            log.error(re);
            throw re;
        }
        return result;
    }

    public static List<PostPicture> getNewPostPictures(Site site, int cnt) {
        List<PostPicture> result;

        String sql = "select * from (select *,row_number() over (partition by postid order by postid) as rn from v_post_pic) as t " +
                " where rn=1 and cateId='csa' ";
        if (site != null)
            sql += " and siteId='" + site.getSiteId() + "'";

        sql += " order by createDate desc";

        Session session = HibernateSessionFactory.getSession();
        try {
            Query query = session.createSQLQuery(sql).addEntity(PostPicture.class);
            query.setMaxResults(cnt);
            result = query.list();
        } catch (RuntimeException re) {
            log.error(re);
            throw re;
        }
        return result;
    }

    public static List<Post> getHotPosts(Site site, int cnt) {
        List posts;
        Session session = HibernateSessionFactory.getSession();
        try {
            Criteria c = session.createCriteria(Post.class);
            c.add(Restrictions.isNull("parent"));
            c.add(Restrictions.eq("category", CSA_CAT));
            c.add(Restrictions.sqlRestriction("datediff(dayofyear,createdate,getdate())<100"));
            if (site != null)
                c.add(Restrictions.eq("site", site));
            c.addOrder(Order.desc("vwCnt"));
            c.setMaxResults(cnt);
            posts = c.list();
        } catch (RuntimeException re) {
            log.error(re);
            throw re;
        }
        return posts;
    }

    public static List<Post> getNotice(int cnt) {
        System.out.println("CsaService.getNotice");
        List<Post> result = new ArrayList<Post>();
        String sql = "select * from v_csa where parentId is null and csaType=? order by createDate desc ";
        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(sql).addEntity(Post.class).setParameter(0, "110").setMaxResults(cnt);
            result = query.list();
        } catch (HibernateException he) {
            log.error(he);
            throw he;
        }
        return result;
    }

    public static List<Post> getNotice(Site site, int cnt) {
        List<Post> result = new ArrayList<Post>();
        String sql = "select * from v_csa where parentId is null and csaType=? ";

        if (site == null) return result;
        String siteType = site.getType().getTypeId();
        String siteId = site.getSiteId();

        if ("1".equals(siteType)) {
            sql += " and country = '" + siteId + "'";
        } else if ("2".equals(siteType)) {
            sql += " and state = '" + siteId + "'";
        } else if ("3".equals(siteType)) {
            sql += " and city = '" + siteId + "'";
        } else if ("4".equals(siteType)) {
            sql += " and county = '" + siteId + "'";
        } else if ("5".equals(siteType)) {
            sql += " and town = '" + siteId + "'";
        } else if ("6".equals(siteType)) {
            sql += " and village = '" + siteId + "'";
        } else if ("9".equals(siteType)) {
            sql += " and siteId = '" + siteId + "'";
        }
        sql += " order by createDate desc";
        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(sql).addEntity(Post.class).setParameter(0, "110");
            query.setMaxResults(cnt);
            result = query.list();
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }

        return result;
    }

}
