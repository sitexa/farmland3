package com.sitexa.user.rest.user;

import com.sitexa.framework.AppContext;
import com.sitexa.framework.Constants;
import com.sitexa.user.action.ControlAction;
import com.sitexa.user.data.Member;
import com.sitexa.user.service.MemberService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;

/**
 * User: xnpeng
 * Date: 2009-10-15
 * Time: 17:36:52
 */
public class ControlController extends ControlAction {
    public HttpHeaders index() {
        System.out.println("ControlController.index");
        member = getProfile();
        if (member != null) {
            properties.addAll(member.getProperties());
            pictures.addAll(member.getPictures());
            return new DefaultHttpHeaders("index").disableCaching();
        } else {
            try {
                ServletActionContext.getResponse()
                        .sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
            } catch (IOException ignored) {
            }
            return null;
        }
    }

    public HttpHeaders show() {
        member = MemberService.getMember(id);
        if (member != null) {
            properties.addAll(member.getProperties());
            pictures.addAll(member.getPictures());
            if ((getProfile() != null) && !member.getMemberId().equals(getProfile().getMemberId())) {
                MemberService.visit(member, getProfile());
            }
            return new DefaultHttpHeaders("show").disableCaching();
        } else {
            return index();
        }
    }

    public HttpHeaders edit() {
        if (!haveRight()) return show();
        member = MemberService.getMember(id);
        if (member != null) {
            properties.addAll(member.getProperties());
            pictures.addAll(member.getPictures());
            return new DefaultHttpHeaders("edit").disableCaching();
        } else {
            return show();
        }
    }

    public HttpHeaders editNew() {
        return index();
    }

    public HttpHeaders create() {
        return index();
    }

    public HttpHeaders update() {
        if (!haveRight()) return show();
        if (member != null) {
            try {
                MemberService.update(member);
                if (member.getSite() != null) {
                    if (member.getMemberId().equals(getProfile().getMemberId())) {
                        AppContext ac = new AppContext(member.getMemberId(), member.getSite().getSiteId());
                        getSession().put(Constants.APP_CONTEXT, ac);
                    }
                }
                addActionMessage("修改成功");
            } catch (Exception e) {
                addActionError("修改会员出错!原因:" + e.getMessage());
            }
        } else {
            return show();
        }
        return edit();
    }

    public HttpHeaders destroy() {
        return index();
    }

    protected boolean haveRight() {
        Member profile = getProfile();
        Member member = MemberService.getMember(id);
        return (profile != null && member != null && profile.getMemberId().equals(member.getMemberId()));
    }

}
