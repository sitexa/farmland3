package com.sitexa.user.rest.user;

import com.sitexa.site.service.SiteMemberService;
import com.sitexa.user.action.MemberSiteAction;
import com.sitexa.user.data.Member;
import com.sitexa.user.service.MemberService;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * User: xnpeng
 * Date: 2009-5-14
 * Time: 9:15:24
 */
public class MemberSiteController extends MemberSiteAction {
    public void prepare() {
        super.prepare();
        site = getHome();
    }

    private boolean haveRight() {
        Member member = MemberService.getMember(id);
        Member profile = getProfile();
        return profile != null && member != null && profile.getMemberId().equals(member.getMemberId());
    }

    public HttpHeaders index() {
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders show() {
        System.out.println("MemberSiteController.show");
        member = MemberService.getMember(id);
        if (member != null) {
            memberSites = SiteMemberService.getSiteMemberByMember(member);
        }
        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders edit() {
        System.out.println("MemberSiteController.edit");
        if (!haveRight()) return show();
        member = MemberService.getMember(id);
        if (member != null) {
            memberSites = SiteMemberService.getSiteMemberByMember(member);
        }
        return new DefaultHttpHeaders("edit");
    }

    public HttpHeaders editNew() {
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders create() {
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders update() {
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders destroy() {
        return new DefaultHttpHeaders("index");
    }
}
