package com.sitexa.post.service;

import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.sys.Sequence;
import com.sitexa.post.data.*;
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
 * Date: 2009-4-13
 * Time: 22:31:11
 */
public class AffairService extends PostService {
    private static Log log = LogFactory.getLog(AffairService.class);

    public void create(Object obj) {
        Affair affair = (Affair) obj;
        Post post = affair.getPost();

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

            affair.setId(id);
            post.setId(id);
            post.setCategory(category);
            post.setCreator(creator);
            post.setSite(site);
            post.setParent(parent);
            post.setCreateDate(new Date());

            FilterService.filter(post);

            Session session = HibernateSessionFactory.getSession();
            Transaction tx = session.beginTransaction();
            try {
                tx.begin();
                session.save(post);
                session.save(affair);
                tx.commit();
            } catch (RuntimeException re) {
                log.error(re);
                tx.rollback();
                throw re;
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static void update(Object obj) {
        Post post = (Post) obj;

        FilterService.filter(post);

        PostDAO dao = new PostDAO();
        try {
            Post post2 = dao.findById(post.getId());
            post2.setName(post.getName());
            post2.setSubject(post.getSubject());
            post2.setContent(post.getContent());

            dao.update(post2);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static Affair getAffair(String id) {
        AffairDAO dao = new AffairDAO();
        try {
            return dao.findById(id);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }
}
