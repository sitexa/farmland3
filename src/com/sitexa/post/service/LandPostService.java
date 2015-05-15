package com.sitexa.post.service;

import com.sitexa.farm.data.Land;
import com.sitexa.farm.service.LandService;
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
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-12-23
 * Time: 10:22:35
 */
public class LandPostService extends PostService {

    private static Log log = LogFactory.getLog(LandPostService.class);

    public static void main(String[] args) {
        Land land = LandService.getById("1000445");
        Page page = new Page(10);

        List list = LandPostService.getLandPosts(land, page, "12");
        System.out.println("list.size() = " + list.size());

    }

    @Override
    public void create(Object obj) {
        LandPost msg = (LandPost) obj;

        System.out.println("msg.getStarLand().getLandId() = " + msg.getLand().getLandId());

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
                LandPostDAO dao2 = new LandPostDAO();
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

    public static LandPost getById(String id) {
        LandPostDAO dao = new LandPostDAO();
        return dao.findById(id);
    }

    public static List<Post> search(Page page, Land land, String words) {

        String[] strArr = words.split("[,+;+.+。+]|(\\s)+|(\\p{Punct})+");
        List<String> wordList = new ArrayList<String>();
        for (String s : strArr) {
            if (s != null && !s.equals(""))
                wordList.add(s);
        }

        String hql = "select * from v_land_post where parentId is null ";
        hql += " and landId ='" + land.getLandId() + "'";
        hql += " and cateId='landpost'";

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

    public static List<Post> getNewPosts(int cnt) {
        List<Post> result = new ArrayList<Post>();

        String sql = "select * from v_land_post where parentId is null order by createDate desc ";

        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(sql).addEntity(Post.class).setMaxResults(cnt);
            result = query.list();
        } catch (HibernateException he) {
            log.error(he);
        }
        return result;
    }

    public static List<LandPost> getNewLandPosts(Site site, int cnt) {
        System.out.println("LandPostService.getNewLandPosts");
        List<Post> posts = PostService.getNewPosts(site, "landpost", cnt);
        List<LandPost> lps = new ArrayList<LandPost>();
        for (Post post : posts) {
            LandPost lp = LandPostService.getById(post.getId());
            lps.add(lp);
        }
        return lps;
    }

    public static List<Post> getLandPosts(Land land, Page page, String typeId) {
        System.out.println("LandPostService.getLandPosts");
        String hql = "select * from v_land_post where parentId is null ";
        if (land != null)
            hql += " and landId ='" + land.getLandId() + "'";
        if(typeId!=null && !"".equals(typeId)){
        	hql += " and typeId='" + typeId + "'";
        }
        hql += " and cateId='landpost'";
        hql += " order by createDate desc";

        Session session = HibernateSessionFactory.getSession();
        try {
            Query query = session.createSQLQuery(hql).addEntity(Post.class);

            int t = query.list().size();
            int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
            page.setTotal(tot);

            query.setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            query.setMaxResults(page.getPageSize());
            System.out.println("query.list().size() = " + query.list().size());
            return query.list();
        } catch (RuntimeException re) {
            log.error(re);
            throw re;
        }

    }
    
    public static List<Post> getLandPosts(Site site, Page page, String typeId) {
    	List<Post> result = new ArrayList<Post>();
    	if(site!=null){
            String landIds = "select landId from v_land_site";
            String siteTypeStr = getSiteTypeStr(site);
            if(!"".equals(siteTypeStr)){
            	landIds += " where " + siteTypeStr + "='" + site.getSiteId() + "'";
            }
            String posts = "select * from v_land_post where parentId is null and landId in ("+ landIds +")";
            if (typeId != null && !"".equals(typeId)){
            	posts += " and typeId='" + typeId + "'";
            }
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
