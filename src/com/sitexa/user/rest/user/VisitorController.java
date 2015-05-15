package com.sitexa.user.rest.user;

import com.sitexa.user.action.VisitorAction;
import com.sitexa.user.service.MemberService;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * User: xnpeng
 * Date: 2009-5-6
 * Time: 21:13:28
 */
public class VisitorController extends VisitorAction {

    public void prepare() {
        super.prepare();
        site = getHome();
    }

    public HttpHeaders index() {
        return show();
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders show() {
        member = MemberService.getMember(id);
        if (member != null) {
            visitors.addAll(member.getVisitors());
        }
        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders edit() {
        return show();
    }

    public HttpHeaders editNew() {
        return show();
    }

    public HttpHeaders create() {
        return show();
    }

    public HttpHeaders update() {
        return show();
    }

    public HttpHeaders destroy() {
        return show();
    }
}
