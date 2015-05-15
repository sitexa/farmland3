package com.sitexa.post.service;

import com.sitexa.framework.exception.BaseException;
import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.post.data.*;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.sys.Sequence;
import com.sitexa.user.data.Member;
import com.sitexa.user.data.User;
import com.sitexa.user.service.MemberService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-4-8
 * Time: 19:50:57
 */
@SuppressWarnings("deprecation,unchecked")
public abstract class PostService extends PostPictureService {

    private static Log log = LogFactory.getLog(PostService.class);

    public static void main(String[] args) {
        PostPicture pic = new PostPicture(null, null, "test", "test");
        pic.setAbbrev(null);
        pic.setPicUrl("testurl");
        pic.setPicId(Sequence.newId("post"));

        PostPictureDAO dao = new PostPictureDAO();
        dao.save(pic);


    }

    public static List<Post> getHotPosts(Site site) {
        List posts;
        Session session = HibernateSessionFactory.getSession();
        try {
            Criteria c = session.createCriteria(Post.class);
            c.add(Restrictions.isNull("parent"));
            c.add(Restrictions.sqlRestriction("datediff(dayofyear,createdate,getdate())<3"));
            if (site != null)
                c.add(Restrictions.eq("site", site));
            c.addOrder(Order.desc("vwCnt"));
            c.setMaxResults(10);
            posts = c.list();
        } catch (RuntimeException re) {
            log.error(re);
            throw re;
        }
        return posts;
    }

    public static List<Post> getTopPosts(Site site) {
        List posts;
        Session session = HibernateSessionFactory.getSession();
        try {
            Criteria c = session.createCriteria(Post.class);
            c.add(Restrictions.isNull("parent"));
            c.add(Restrictions.sqlRestriction("datediff(dayofyear,createdate,getdate())<20"));
            if (site != null)
                c.add(Restrictions.eq("site", site));
            c.add(Restrictions.sqlRestriction("TopTag=1 or EliteTag=1"));
            c.addOrder(Order.desc("vwCnt"));
            c.setMaxResults(10);
            posts = c.list();
        } catch (RuntimeException re) {
            log.error(re);
            throw re;
        }
        return posts;
    }

    public static List<Post> getTagPosts(String tags) {
        String sqlTags = "Tags like '%" + tags + "%'";
        List posts;
        Session session = HibernateSessionFactory.getSession();
        try {
            Criteria c = session.createCriteria(Post.class);
            c.add(Restrictions.isNull("parent"));
            c.add(Restrictions.sqlRestriction("datediff(dayofyear,createdate,getdate())<20"));
            c.add(Restrictions.sqlRestriction(sqlTags));
            c.addOrder(Order.desc("vwCnt"));
            c.setMaxResults(10);
            posts = c.list();
        } catch (RuntimeException re) {
            log.error(re);
            throw re;
        }
        return posts;
    }

    public static List<Post> search(Page page, String words, Site site, Category category) {

        String[] strArr = words.split("[,+;+.+。+]|(\\s)+|(\\p{Punct})+");
        List<String> wordList = new ArrayList<String>();
        for (String s : strArr) {
            if (s != null && !s.equals(""))
                wordList.add(s);
        }

        String hql = "select * from v_post_site where parentId is null ";

        if (site != null) {
            String siteType = site.getType().getTypeId();
            String siteId = site.getSiteId();

            if ("1".equals(siteType)) {
                hql += " and country = '" + siteId + "'";
            } else if ("2".equals(siteType)) {
                hql += " and state = '" + siteId + "'";
            } else if ("3".equals(siteType)) {
                hql += " and city = '" + siteId + "'";
            } else if ("4".equals(siteType)) {
                hql += " and county = '" + siteId + "'";
            } else if ("5".equals(siteType)) {
                hql += " and town = '" + siteId + "'";
            } else if ("6".equals(siteType)) {
                hql += " and village = '" + siteId + "'";
            } else if ("9".equals(siteType)) {
                hql += " and siteId = '" + siteId + "'";
            }
        }

        if (category != null) hql += " and cateId='" + category.getCateId() + "'";

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

    public static List<Post> search(String words, Page page) {
        List<Post> list;
        String[] strArr = words.split("[,+;+.+。+]|(\\s)+|(\\p{Punct})+");
        List<String> wordList = new ArrayList<String>();
        for (String s : strArr) {
            if (s != null && !s.equals(""))
                wordList.add(s);
        }
        try {
            Session sess = HibernateSessionFactory.getSession();

            String hql = " from Post where parent is null";
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

            Query query = sess.createQuery(hql);

            int t = query.list().size();
            int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
            page.setTotal(tot);

            query.setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            query.setMaxResults(page.getPageSize());

            list = query.list();
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
        return list;
    }

    public static Post getPost(String id) {
        Post post = null;
        try {
            post = (new PostDAO()).findById(id);
        } catch (RuntimeException re) {
            log.error(re);
        }
        return post;
    }

    public static List<Post> searchPosts(Page page) {
        List<Post> list;
        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createQuery(" from Post where  parent is null order by createDate desc");

            int t = query.list().size();
            int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
            page.setTotal(tot);

            query.setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            query.setMaxResults(page.getPageSize());
            list = query.list();
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
        return list;
    }

    public static List<Post> getChildren(Post parent) {
        try {
            return (new PostDAO()).findByProperty("parent", parent);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static List<Post> getPostsByCreator(User creator) {
        return (new PostDAO()).findByProperty("creator", creator);
    }

    public static List<Post> getPostsBySite(Site site) {
        try {
            return (new PostDAO()).findBySite(site);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static List<PostPicture> getNewPictures(Site site, int num) {
        List<PostPicture> result = new ArrayList<PostPicture>();

        if (site == null) return result;

        String siteType = site.getType().getTypeId();
        String siteId = site.getSiteId();

        String sql = "select * from (select *,row_number() over (partition by postid order by postid) as rn from v_post_pic) as t " +
                " where rn=1 ";

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

        Session session = HibernateSessionFactory.getSession();
        try {
            Query query = session.createSQLQuery(sql).addEntity(PostPicture.class);
            query.setMaxResults(num);
            result = query.list();
        } catch (RuntimeException re) {
            log.error(re);
            throw re;
        }
        return result;
    }

    public static List<Post> getNewPosts(Site site, int num) {
        List<Post> result = new ArrayList<Post>();
        if (site == null) return result;
        String siteType = site.getType().getTypeId();
        String siteId = site.getSiteId();

        String sql = "select * from v_post_site where parentId is null ";

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
            Query query = session.createSQLQuery(sql).addEntity(Post.class).setMaxResults(num);
            result = query.list();
        } catch (HibernateException he) {
            log.error(he);
        }

        return result;
    }

    public static Post getFocus(Site site) {
        Post post = null;
        if (site == null) return null;

        String siteId = site.getSiteId();
        String siteType = site.getType().getTypeId();

        String sql = "select * from v_post_site where (eliteTag = 1 or topTag = 1) ";

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
            Query query = session.createSQLQuery(sql).addEntity(Post.class).setMaxResults(1);
            List<Post> list = query.list();
            if (list.size() > 0)
                post = list.get(0);
        } catch (HibernateException he) {
            log.error(he);
        }

        return post;
    }

    public static List<Post> getNewPosts(Site site, String catId, int num) {
        List<Post> result = new ArrayList<Post>();
        if (site == null || catId == null) return result;
        String siteType = site.getType().getTypeId();
        String siteId = site.getSiteId();

        String sql = " select * from v_post_site where parentId is null and cateId = '" + catId + "' ";
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
            Query query = session.createSQLQuery(sql).addEntity(Post.class);
            query.setMaxResults(num);

            result = query.list();

        } catch (HibernateException e) {
            log.error(e.getMessage());
            throw e;
        }

        return result;
    }

    public static List<Post> searchBySite(Site site, Page page) {
        System.out.println("PostService.searchBySite");
        List<Post> list;
        try {
            Session session = HibernateSessionFactory.getSession();

            Query query = session.createQuery(" from Post where parent is null and (site.siteId =? or site.parent.siteId like ? ) order by createDate desc");
            query.setParameter(0, site.getSiteId());
            query.setParameter(1, site.getSiteId() + "%");

            int t = query.list().size();
            int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
            page.setTotal(tot);

            query.setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            query.setMaxResults(page.getPageSize());
            list = query.list();
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
        return list;
    }

    public static List<Post> getPostsByCategory(Category category) {
        try {
            return (new PostDAO()).findByCategory(category);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static List<Post> searchByCategory(Category category, Page page) {
        List<Post> list;
        try {
            Session session = HibernateSessionFactory.getSession();

            Query query = session.createQuery(" from Post where parent is null and category =?  order by createDate desc");
            query.setParameter(0, category);

            int t = query.list().size();
            int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
            page.setTotal(tot);

            query.setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            query.setMaxResults(page.getPageSize());
            list = query.list();
            return list;
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static List<Post> getPostsByCreateDate(Date createDate) {
        try {
            return (new PostDAO()).findByProperty("createDate", createDate);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static List<Post> getPostsByModifyDate(Date modifyDate) {
        try {
            return (new PostDAO()).findByProperty("modifyDate", modifyDate);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static List<Post> getPostsByStatus(String status) {
        try {
            return (new PostDAO()).findByProperty("status", status);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static List<Post> getPostsBySiteAndCategory(Site site, Category category) {
        System.out.println("PostService.getPostsBySiteAndCategory");
        PostDAO dao = new PostDAO();
        Post post = new Post();
        try {
            post.setCategory(category);
            post.setSite(site);
            return dao.findBySiteAndCategory(site, category);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static List<Post> searchBySiteAndCategory(Site site, Category category, Page page) {
        List<Post> list;
        try {
            Session session = HibernateSessionFactory.getSession();

            Query query = session.createQuery(" from Post where parent is null and  category =? and (site.siteId =? or site.parent.siteId like ? )  order by createDate desc");
            query.setParameter(0, category);
            query.setParameter(1, site.getSiteId());
            query.setParameter(2, site.getSiteId() + "%");

            int t = query.list().size();
            int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
            page.setTotal(tot);

            query.setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            query.setMaxResults(page.getPageSize());
            list = query.list();
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
        return list;
    }

    public static void incVwCnt(Post post) {
        if (post != null) {

            PostDAO dao = new PostDAO();
            try {
                post = dao.findById(post.getId());
                if (post.getVwCnt() == 0) post.setVwCnt(1);
                else
                    post.setVwCnt(post.getVwCnt() + 1);
                dao.save(post);
            } catch (RuntimeException re) {
                re.printStackTrace();
                log.error(re);
                throw re;
            }
        }
    }

    public static void updateTag(Post post) {
        if (post != null) {
            PostDAO dao = new PostDAO();
            try {
                dao.save(post);
            } catch (RuntimeException re) {
                re.printStackTrace();
                log.error(re);
                throw re;
            }
        }
    }

    public static void comment(Post cmnt) {
        System.out.println("PostService.comment");
        try {
            Member creator = MemberService.getMember(cmnt.getCreator().getMemberId());
            Site site = SiteService.getSite(cmnt.getSite().getSiteId());
            Category category = CategoryService.getCategory(cmnt.getCategory().getCateId());
            Post parent = null;
            if (cmnt.getParent() != null
                    && cmnt.getParent().getId() != null
                    && !cmnt.getParent().getId().equals("")) {
                parent = getPost(cmnt.getParent().getId());
            }
            String id = Sequence.newId("post");

            cmnt.setId(id);
            cmnt.setCategory(category);
            cmnt.setCreator(creator);
            cmnt.setSite(site);
            cmnt.setParent(parent);
            cmnt.setCreateDate(new Date());

            FilterService.filter(cmnt);

            PostDAO dao = new PostDAO();
            try {
                dao.save(cmnt);
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

    public static PostPicture getPicture(String picId) {
        PostPictureDAO dao = new PostPictureDAO();
        try {
            return dao.findById(picId);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static List<PostPicture> getPictures(List<String> picIds) {
        System.out.println("PostService.getPictures");
        if (picIds == null || picIds.size() == 0) return new ArrayList<PostPicture>();

        try {
            Session session = HibernateSessionFactory.getSession();

            Criteria c = session.createCriteria(PostPicture.class);
            c.add(Restrictions.in("picId", picIds));
            return c.list();
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static List<PostPicture> getPicturesByPost(Post post) {
        PostPictureDAO dao = new PostPictureDAO();
        try {
            return dao.findByProperty("post", post);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static void toggleTop(String idList) {
        System.out.println("PostService.toggleTop");
        if (idList.indexOf(",") == 0)
            idList = idList.substring(1, idList.length());
        if (idList.lastIndexOf(",") == idList.length() - 1)
            idList = idList.substring(0, idList.length() - 1);
        String[] ids = idList.split(",");
        PostDAO dao = new PostDAO();
        try {
            for (String id : ids) {
                Post post = dao.findById(id);
                post.setTopTag(post.getTopTag() == null || !post.getTopTag());
                dao.save(post);
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static void toggleElite(String idList) {
        System.out.println("PostService.toggleElite");
        if (idList.indexOf(",") == 0)
            idList = idList.substring(1, idList.length());
        if (idList.lastIndexOf(",") == idList.length() - 1)
            idList = idList.substring(0, idList.length() - 1);
        String[] ids = idList.split(",");
        PostDAO dao = new PostDAO();
        try {
            for (String id : ids) {
                Post post = dao.findById(id);
                post.setEliteTag(post.getEliteTag() == null || !post.getEliteTag());
                dao.save(post);
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static void updateStatus(String idList, String status) {
        System.out.println("PostService.updateStatus");
        if (idList.indexOf(",") == 0)
            idList = idList.substring(1, idList.length());
        if (idList.lastIndexOf(",") == idList.length() - 1)
            idList = idList.substring(0, idList.length() - 1);
        String[] ids = idList.split(",");
        PostDAO dao = new PostDAO();
        try {
            for (String id : ids) {
                Post post = dao.findById(id);
                post.setStatus(status);
                dao.update(post);
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static void moveToSite(String postIdList, String siteId) {
        if (postIdList.indexOf(",") == 0)
            postIdList = postIdList.substring(1, postIdList.length());
        if (postIdList.lastIndexOf(",") == postIdList.length() - 1)
            postIdList = postIdList.substring(0, postIdList.length() - 1);
        String[] ids = postIdList.split(",");

        Site site = SiteService.getSite(siteId);
        if (site == null) return;
        try {
            PostDAO dao = new PostDAO();
            for (String id : ids) {
                Post post = dao.findById(id);
                if (post == null) break;
                post.setSite(site);
                dao.update(post);
            }
        } catch (RuntimeException re) {
            log.error(re.getMessage());
        }

    }

    //t_post,t_message,t_activity,t_affair,t_business,t_scenery;

    public static void moveToCategory(String postIdList, String cateId) {
        System.out.println("PostService.moveToCategory");
        if (postIdList.indexOf(",") == 0)
            postIdList = postIdList.substring(1, postIdList.length());
        if (postIdList.lastIndexOf(",") == postIdList.length() - 1)
            postIdList = postIdList.substring(0, postIdList.length() - 1);
        String[] ids = postIdList.split(",");

        Category cat = CategoryService.getCategory(cateId);
        if (cat == null) return;

        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            for (String id : ids) {
                Post post = (Post) session.get(Post.class, id);
                if (post == null) break;

                Category cat0 = post.getCategory();
                if (cat0.getCateId().equals(cat.getCateId())) break;

                session.createQuery("update Post set category.cateId ='" + cat.getCateId() + "' where id='" + id + "' or parent.id='" + id + "'").executeUpdate();

                if (Category.ACTIVITY.equalsIgnoreCase(cat0.getCateId())) {
                    session.delete(session.get(Activity.class, id));
                    session.createQuery("delete from Participant where activity.id ='" + id + "'");
                    System.out.println("delete.ACTIVITY..");
                } else if (Category.AFFAIR.equalsIgnoreCase(cat0.getCateId())) {
                    session.delete(session.get(Affair.class, id));
                    System.out.println("delete.AFFAIR..");
                } else if (Category.BUSINESS.equalsIgnoreCase(cat0.getCateId())) {
                    session.delete(session.get(Business.class, id));
                    System.out.println("delete.BUSINESS..");
                } else if (Category.MESSAGE.equalsIgnoreCase(cat0.getCateId())) {
                    session.delete(session.get(Message.class, id));
                    System.out.println("delete.MESSAGE..");
                } else if (Category.SCENERY.equalsIgnoreCase(cat0.getCateId())) {
                    session.delete(session.get(Scenery.class, id));
                    System.out.println("delete.SCENERY..");
                }

                if (Category.ACTIVITY.equalsIgnoreCase(cat.getCateId())) {
                    PostType type = (new PostTypeDAO()).findById("1");
                    Activity act = new Activity(post.getId(), type, post, post.getCreateDate());
                    session.save(act);
                    System.out.println("add.ACTIVITY.." + post.getId());
                } else if (Category.AFFAIR.equalsIgnoreCase(cat.getCateId())) {
                    PostType type = (new PostTypeDAO()).findById("1");
                    Affair aff = new Affair(post.getId(), type, post);
                    session.save(aff);
                    System.out.println("add.AFFAIR.." + post.getId());
                } else if (Category.BUSINESS.equalsIgnoreCase(cat.getCateId())) {
                    PostType type = (new PostTypeDAO()).findById("1");
                    Business biz = new Business(post.getId(), type, post);
                    session.save(biz);
                    System.out.println("add.BUSINESS.." + post.getId());
                } else if (Category.MESSAGE.equalsIgnoreCase(cat.getCateId())) {
                    PostType type = (new PostTypeDAO()).findById("1");
                    Message msg = new Message(post.getId(), type, post);
                    session.save(msg);
                    System.out.println("add.MESSAGE.." + post.getId());
                } else if (Category.SCENERY.equalsIgnoreCase(cat.getCateId())) {
                    Scenery scn = new Scenery(post.getId(), post);
                    session.save(scn);
                    System.out.println("add.SCENERY.." + post.getId());
                }
            }
            tx.commit();
            session.close();
        } catch (RuntimeException re) {
            log.error(re);
        }

    }

    /**
     * 批量删除文章.维护存储过程.
     *
     * @param idList - commas seperated id string,"1,2,3,4,5",or ",1,2,3,4,5",or "1,2,3,4,5,"
     * @throws Exception - sp error.
     */
    public static void delete2(String idList) {
        System.out.println("PostService.delete2");
        if (idList.indexOf(",") == 0)
            idList = idList.substring(1, idList.length());
        if (idList.lastIndexOf(",") == idList.length() - 1)
            idList = idList.substring(0, idList.length() - 1);

        Session session = HibernateSessionFactory.getSession();
        Connection conn = session.connection();
        Transaction tx = session.beginTransaction();
        try {
            CallableStatement stmt = conn.prepareCall(" exec SP_DelPost ? ");
            stmt.setString(1, idList);
            stmt.executeUpdate();
            tx.commit();
            deletePictures(idList);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        } catch (SQLException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ignored) {

            }
        }
    }

    public static void delete(String idList) throws BaseException {
        System.out.println("PostService.delete");
        if (idList.indexOf(",") == 0)
            idList = idList.substring(1, idList.length());
        if (idList.lastIndexOf(",") == idList.length() - 1)
            idList = idList.substring(0, idList.length() - 1);
        String[] ids = idList.split(",");
        try {
            for (String id : ids) {
                Post post = getPost(id);
                delete(post);
            }
            deletePictures(idList);
        } catch (BaseException e) {
            log.error(e);
            throw e;
        }
    }

    /**
     * 删除文章.删除与该文章相关的图片,文章,以及关联表的所有记录.
     *
     * @param post - post
     * @throws BaseException -exception
     */
    public static void delete(Post post) throws BaseException {
        System.out.println("PostService.delete");
        String id = post.getId();
        List<Post> list = new ArrayList<Post>();
        list.addAll(post.getChildren());
        if (list.size() > 0) {
            for (Post post1 : list) {
                delete(post1);
            }
        }

        List<PostPicture> pics = getPicturesByPost(post);
        for (PostPicture picture : pics) {
            deleteFromFileSystem(picture);
        }

        String sql_children = "delete from t_post where parentId= '" + id + "'";
        String sql_post = "delete from t_post where id= '" + id + "'";
        String sql_prop = "delete from t_post_property where postId='" + id + "'";
        String sql_pic = "delete from t_post_picture where postId='" + id + "'";
        String sql_msg = "delete from t_message where id='" + id + "'";
        String sql_participant = "delete from t_participant where activityId='" + id + "'";
        String sql_activity = "delete from t_activity where id='" + id + "'";
        String sql_affair = "delete from t_affair where id='" + id + "'";
        String sql_business = "delete from t_business where id='" + id + "'";
        String sql_pollOption = "delete from t_poll_option where pollId='" + id + "'";
        String sql_pollVote = "delete from t_poll_vote where pollId='" + id + "'";
        String sql_poll = "delete from t_poll where id='" + id + "'";
        String sql_announce = "delete from t_announce where id='" + id + "'";
        String sql_resident = "delete from t_resident where houseId='" + id + "'";
        String sql_house = "delete from t_house where id='" + id + "'";
        String sql_evaluation = "delete from t_evaluation where sceneryId='" + id + "'";
        String sql_scenery = "delete from t_scenery where id='" + id + "'";
        String sql_case = "delete from t_case where id='" + id + "'";
        String sql_farmpost = "delete from t_farm_post where id='" + id + "'";
        String sql_landpost = "delete from t_land_post where id='" + id + "'";
        String sql_csa = "delete from t_csa where id='" + id + "'";

        Session session = HibernateSessionFactory.getSession();
        Connection conn = session.connection();
        try {
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();

            if (post.getCategory().getCateId().equalsIgnoreCase(Category.ACTIVITY)) {
                stmt.addBatch(sql_participant);
                stmt.addBatch(sql_activity);
            } else if (post.getCategory().getCateId().equalsIgnoreCase(Category.AFFAIR)) {
                stmt.addBatch(sql_affair);

            } else if (post.getCategory().getCateId().equalsIgnoreCase(Category.ANNOUNCE)) {
                stmt.addBatch(sql_announce);

            } else if (post.getCategory().getCateId().equalsIgnoreCase(Category.BUSINESS)) {
                stmt.addBatch(sql_business);

            } else if (post.getCategory().getCateId().equalsIgnoreCase(Category.CASE)) {
                stmt.addBatch(sql_case);

            } else if (post.getCategory().getCateId().equalsIgnoreCase(Category.HOUSE)) {
                stmt.addBatch(sql_resident);
                stmt.addBatch(sql_house);

            } else if (post.getCategory().getCateId().equalsIgnoreCase(Category.MESSAGE)) {
                stmt.addBatch(sql_msg);

            } else if (post.getCategory().getCateId().equalsIgnoreCase(Category.POLL)) {
                stmt.addBatch(sql_pollOption);
                stmt.addBatch(sql_pollVote);
                stmt.addBatch(sql_poll);

            } else if (post.getCategory().getCateId().equalsIgnoreCase(Category.SCENERY)) {
                stmt.addBatch(sql_evaluation);
                stmt.addBatch(sql_scenery);
            } else if (post.getCategory().getCateId().equalsIgnoreCase(Category.FARMPOST)) {
                stmt.addBatch(sql_farmpost);
            } else if (post.getCategory().getCateId().equalsIgnoreCase(Category.LANDPOST)) {
                stmt.addBatch(sql_landpost);
            } else if (post.getCategory().getCateId().equalsIgnoreCase(Category.CSA)) {
                stmt.addBatch(sql_csa);
            }

            stmt.addBatch(sql_pic);
            stmt.addBatch(sql_prop);
            stmt.addBatch(sql_children);
            stmt.addBatch(sql_post);

            stmt.executeBatch();
            session.clear();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                log.error(e);
            }
            log.error(e);
            BaseException.threw(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                log.error(e);
            }
        }
    }

    public abstract void create(Object obj);

    public static void update(Object obj) {
        Post post = (Post) obj;
        //todo....filter name,subject and content.
        FilterService.filter(post);
        PostDAO dao = new PostDAO();
        try {
            Post p = dao.findById(post.getId());
            p.setName(post.getName());
            p.setSubject(post.getSubject());
            p.setContent(post.getContent());
            p.setModifyDate(new Date());

            dao.update(p);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static List<Post> getPostByMember(Member member, Page page) {
        List<Post> result;
        String hql = "select * from t_post where creator = ? order by createDate desc ";
        Session session = HibernateSessionFactory.getSession();
        try {
            Query query = session.createSQLQuery(hql).addEntity(Post.class).setParameter(0, member);

            int t = query.list().size();
            int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
            page.setTotal(tot);
            query.setMaxResults(page.getPageSize()).setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            result = query.list();
        } catch (RuntimeException re) {
            log.error(re);
            throw re;
        }
        return result;
    }

    public static String getSiteTypeStr(Site site) {
        String siteTypeStr = "";
        String siteType = site.getType().getTypeId();
        if ("1".equals(siteType)) {
            siteTypeStr = "country";
        } else if ("2".equals(siteType)) {
            siteTypeStr = "state";
        } else if ("3".equals(siteType)) {
            siteTypeStr = "city";
        } else if ("4".equals(siteType)) {
            siteTypeStr = "county";
        } else if ("5".equals(siteType)) {
            siteTypeStr = "town";
        } else if ("6".equals(siteType)) {
            siteTypeStr = "village";
        } else if ("9".equals(siteType)) {
            siteTypeStr = "siteId";
        }
        return siteTypeStr;
    }
}
