package com.sitexa.rest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sitexa.post.data.Post;
import com.sitexa.post.service.CsaService;
import com.sitexa.post.service.PostService;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * User: Administrator
 * Date: 2010-11-1
 * Time: 11:25:17
 */
public class NewsController extends ActionSupport implements ModelDriven<Object> {

    private String id;
    private Post post;
    private NewsObject model = new NewsObject();
    private Collection<NewsObject> list = null;

    public HttpHeaders index() {
        List l = CsaService.getNewPosts("110", 1);
        if (l.size() > 0) {
            Post p = (Post) l.get(0);
            String url = "play/play/" + p.getId();
            NewsObject n = new NewsObject(p.getId(), p.getName(), p.getCreateDate() + "",
                    p.getContent(), p.getCreator().getRealname(), url, "1");
            list = new ArrayList<NewsObject>();
            list.add(n);
        }

        List l2 = CsaService.getNewPosts("9", 6);
        for (int i = 0; i < l2.size(); i++) {
            Post p = (Post) l2.get(i);
            String url = "play/play/" + p.getId();
            NewsObject n = new NewsObject(p.getId(), p.getName(), p.getCreateDate() + "",
                    p.getContent(), p.getCreator().getRealname(), url, "2");
            if (list == null) list = new ArrayList<NewsObject>();
            list.add(n);
        }

        return new DefaultHttpHeaders("index").disableCaching();
    }

    public HttpHeaders show() {
        post = PostService.getPost(id);
        return new DefaultHttpHeaders("show").disableCaching();
    }

    public void setId(String id) {
        if (id != null) {
            post = PostService.getPost(id);
            String url = "play/play/" + post.getId();
            model = new NewsObject(post.getId(), post.getName(), post.getCreateDate() + "",
                    post.getContent(), post.getCreator().getRealname(), url, "0");
        }
        this.id = id;
    }

    public Object getModel() {
        return (list != null ? list : model);
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
