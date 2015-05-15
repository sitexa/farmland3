package com.sitexa.post.service;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.Land;
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
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-1-7
 * Time: 20:01:42
 */
public class FarmPostService extends PostService {

    private static Log log = LogFactory.getLog(FarmPostService.class);

    public void create(Object obj) {
        FarmPost msg = (FarmPost) obj;

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

            msg.setId(id);
            post.setId(id);
            post.setCategory(category);
            post.setCreator(creator);
            post.setSite(site);
            post.setParent(parent);
            post.setCreateDate(new Date());

            FilterService.filter(post);

            try {
                PostDAO dao = new PostDAO();
                dao.save(post);
                FarmPostDAO dao2 = new FarmPostDAO();
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

    public static FarmPost getById(String id) {
        FarmPostDAO dao = new FarmPostDAO();
        return dao.findById(id);
    }

    @SuppressWarnings("unchecked")
    public static List<Post> search(Page page, Farm farm, String words) {

        String[] strArr = words.split("[,+;+.+銆�+]|(\\s)+|(\\p{Punct})+");
        List<String> wordList = new ArrayList<String>();
        for (String s : strArr) {
            if (s != null && !s.equals(""))
                wordList.add(s);
        }

        String hql = "select * from v_farm_post where parentId is null ";
        hql += " and farmId ='" + farm.getFarmId() + "'";
        hql += " and cateId='farmpost'";

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

    @SuppressWarnings("unchecked")
    public static List<Post> getNewPosts(int cnt) {
        List<Post> result = new ArrayList<Post>();

        String sql = "select * from v_farm_post where parentId is null order by createDate desc ";

        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(sql).addEntity(Post.class).setMaxResults(cnt);
            result = query.list();
        } catch (HibernateException he) {
            log.error(he);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static List<FarmPost> getNewFarmPosts(Land land, int cnt) {
        System.out.println("FarmPostService.getNewFarmPosts");
        List<FarmPost> result = new ArrayList<FarmPost>();

        String sql;
        if (land == null)
            sql = "select * from v_farm_post where parentId is null order by createDate desc ";
        else {
            Iterator it = land.getFarms().iterator();
            String IDs = "";
            Farm farm1;
            while (it.hasNext()) {
                farm1 = (Farm) it.next();
                if (!IDs.equals("")) IDs += ",";
                IDs += farm1.getFarmId();
            }
            sql = "select * from v_farm_post where parentId is null and farmId in (" + IDs + ") order by createDate desc ";
        }
        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(sql).addEntity(FarmPost.class).setMaxResults(cnt);
            result = query.list();
        } catch (HibernateException he) {
            log.error(he);
            throw he;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static List<Post> getNewPosts(Farm farm, String typeId, int cnt) {
        List<Post> result = new ArrayList<Post>();

        String sql;

        if (farm == null) {
            if (typeId == null) {
                sql = "select * from v_farm_post where parentId is null order by createDate desc ";
            } else {
                sql = "select * from v_farm_post where parentId is null and typeId='" + typeId + "'order by createDate desc ";
            }
        } else {
            if (typeId == null) {
                sql = "select * from v_farm_post where parentId is null and farmId='" + farm.getFarmId() + "' order by createDate desc ";
            } else {
                sql = "select * from v_farm_post where parentId is null and farmId='" + farm.getFarmId() + "' and typeId='" + typeId + "'order by createDate desc ";
            }
        }

        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(sql).addEntity(Post.class).setMaxResults(cnt);
            result = query.list();
        } catch (HibernateException he) {
            log.error(he);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static List<Post> getFarmPosts(Farm farm, Page page) {
        String hql = "select * from v_farm_post where parentId is null ";
        if (farm != null)
            hql += " and farmId ='" + farm.getFarmId() + "'";
        hql += " and cateId='farmpost'";
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

    @SuppressWarnings("unchecked")
    public static List<Post> getFarmPosts(Farm farm, Page page, String typeId) {
        String hql = "select * from v_farm_post where parentId is null ";
        if (farm != null)
            hql += " and farmId ='" + farm.getFarmId() + "'";
        if (typeId != null && !"".equals(typeId))
            hql += " and typeId='" + typeId + "'";
        hql += " and cateId='farmpost'";
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

    public static List<Post> getFarmPosts(Land land, Page page, String typeId) {
        List<Post> result = new ArrayList<Post>();
        if (land != null) {
            String farmIds = "select farmId from t_farm where landId='" + land.getLandId() + "')";
            String posts = "select * from v_farm_post where parentId is null";
            if (typeId != null && !"".equals(typeId)) {
                posts += " and typeId='" + typeId + "'";
            }
            posts = " and farmId in (" + farmIds + ")";
            posts += " order by createDate desc";
            try {
                Session session = HibernateSessionFactory.getSession();
                Query query = session.createSQLQuery(posts).addEntity(Post.class);

                int t = query.list().size();
                int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
                page.setTotal(tot);

                query.setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
                query.setMaxResults(page.getPageSize());

                result = query.list();
            } catch (HibernateException e) {
                log.error(e.getMessage());
            }
        }

        return result;
    }

    public static List<Post> getFarmPosts(Site site, Page page, String typeId) {
        List<Post> result = new ArrayList<Post>();
        if (site != null) {
            String landIds = "select landId from v_land_site ";
            String siteTypeStr = getSiteTypeStr(site);
            if (!"".equals(siteTypeStr)) {
                landIds += " where " + siteTypeStr + "='" + site.getSiteId() + "'";
            }
            String farmIds = "select farmId from t_farm where landId in (" + landIds + ")";
            String posts = "select * from v_farm_post where parentId is null";
            if (typeId != null && !"".equals(typeId)) {
                posts += " and typeId='" + typeId + "'";
            }
            posts += " and farmId in (" + farmIds + ")";
            posts += " order by createDate desc";
            try {
                Session session = HibernateSessionFactory.getSession();
                Query query = session.createSQLQuery(posts).addEntity(Post.class);

                int t = query.list().size();
                int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
                page.setTotal(tot);

                query.setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
                query.setMaxResults(page.getPageSize());

                result = query.list();
            } catch (HibernateException e) {
                log.error(e.getMessage());
            }
        }

        return result;
    }


}
