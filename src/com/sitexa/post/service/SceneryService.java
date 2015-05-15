package com.sitexa.post.service;

import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.sys.Sequence;
import com.sitexa.post.data.Category;
import com.sitexa.post.data.Post;
import com.sitexa.post.data.Scenery;
import com.sitexa.post.data.SceneryDAO;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;
import com.sitexa.user.service.MemberService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

/**
 * User: xnpeng
 * Date: 2009-4-30
 * Time: 20:49:32
 */
public class SceneryService extends PostService {

    private static Log log = LogFactory.getLog(SceneryService.class);

    public void create(Object obj) {
        Scenery scenery = (Scenery) obj;
        Post post = scenery.getPost();

        Member creator = MemberService.getMember(post.getCreator().getMemberId());
        Site site = SiteService.getSite(post.getSite().getSiteId());
        Category category = CategoryService.getCategory(post.getCategory().getCateId());

        Post parent = null;
        if (post.getParent() != null)
            parent = PostService.getPost(post.getParent().getId());

        String id = Sequence.newId("post");

        post.setId(id);
        scenery.setId(id);
        post.setCategory(category);
        post.setCreator(creator);
        post.setSite(site);
        post.setParent(parent);
        post.setCreateDate(new Date());

        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            session.save(post);
            session.save(scenery);
            tx.commit();
            session.close();
        } catch (RuntimeException e) {
            e.printStackTrace();
            tx.rollback();
            log.error(e);
            throw e;
        }
    }

    public static void update(Object obj) {
        Scenery scenery = (Scenery) obj;
        Post post = scenery.getPost();

        FilterService.filter(post);

        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {

            Post post2 = (Post) session.get(Post.class, post.getId());
            post2.setName(post.getName());
            post2.setSubject(post.getSubject());
            post2.setContent(post.getContent());
            post2.setModifyDate(new Date());

            Scenery scene = (Scenery) session.get(Scenery.class, scenery.getId());
            scene.setAddress(scenery.getAddress());
            scene.setCharge(scenery.getCharge());
            scene.setLevel(scenery.getLevel());
            scene.setPost(post2);

            tx.begin();
            session.update(post2);
            session.update(scene);
            tx.commit();
            session.close();
        } catch (RuntimeException e) {
            tx.rollback();
            log.error(e);
            throw e;
        }
    }

    public static Scenery getScenery(String id) {
        return (new SceneryDAO()).findById(id);
    }
}
