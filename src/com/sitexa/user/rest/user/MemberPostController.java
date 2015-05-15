package com.sitexa.user.rest.user;

import com.sitexa.post.service.CsaService;
import com.sitexa.post.service.PostService;
import com.sitexa.user.action.MemberPostAction;
import com.sitexa.user.service.MemberService;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * User: xnpeng
 * Date: 2009-5-6
 * Time: 22:03:42
 */
public class MemberPostController extends MemberPostAction {

    public HttpHeaders index() {
        return show();
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders show() {
        member = MemberService.getMember(id);
        if (member != null) {
            //posts.addAll(member.getPosts());
            posts = PostService.getPostByMember(member, page);
            site = member.getSite();
        }
        return new DefaultHttpHeaders("");
    }

    public HttpHeaders edit() {
        return index();
    }

    public HttpHeaders editNew() {
        return index();
    }

    public HttpHeaders create() {
        return index();
    }

    public HttpHeaders update() {
        return index();
    }

    public HttpHeaders destroy() {
        return index();
    }
}
