package com.sitexa.post.service;

import com.sitexa.sys.Sequence;
import com.sitexa.post.data.*;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;
import com.sitexa.user.service.MemberService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

/**
 * User: xnpeng
 * Date: 2009-4-13
 * Time: 15:08:39
 */
public class MessageService extends PostService {
    private static Log log = LogFactory.getLog(MessageService.class);

    public static void main(String[] args) {
    }

    public static Message getMessage(String id) {
        try {
            return (new MessageDAO()).findById(id);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public void create(Object obj) {
        Message msg = (Message) obj;
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
                MessageDAO dao2 = new MessageDAO();
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

    public static void update(Object obj) {
        Post post = (Post) obj;
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

}
