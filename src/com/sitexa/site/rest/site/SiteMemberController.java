package com.sitexa.site.rest.site;

import com.sitexa.framework.exception.BaseException;
import com.sitexa.service.RightService;
import com.sitexa.site.action.SiteMemberAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.data.SiteMember;
import com.sitexa.site.service.SiteMemberService;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;
import com.sitexa.user.service.MemberService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.Date;

/**
 * User: xnpeng
 * Date: 2009-5-5
 * Time: 9:51:04
 */
public class SiteMemberController extends SiteMemberAction {
    private static Log log = LogFactory.getLog(SiteMemberController.class);

    public HttpHeaders index() {
        Member profile = getProfile();
        Site home = getHome();
        if (profile != null && home != null) {
            siteMembers.addAll(home.getMembers());//外部会员.
        }
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders show() {
        siteMembers.clear();
        site = SiteService.getSite(id);
        if (site != null) {
            siteMembers.addAll(site.getMembers());
        }
        return new DefaultHttpHeaders("show").disableCaching();
    }

    public HttpHeaders edit() {
        if (!haveRight()) return show();
        site = SiteService.getSite(id);
        if (site != null) {
            siteMembers.addAll(site.getMembers());
        }
        return new DefaultHttpHeaders("edit").disableCaching();
    }

    public HttpHeaders editNew() {
        return null;
    }

    public HttpHeaders create() {
        if (!haveRight()) return show();
        site = SiteService.getSite(id);
        if (site != null) {
            try {
                Member member = MemberService.getMember(siteMember.getMember().getMemberId());
                if (member != null) {
                    SiteMember member2 = SiteMemberService.getSiteMemberBySiteAndMember(site, member);
                    if (member2 != null) {
                        BaseException.threw("该会员已在该社区.");
                    }
                    siteMember.setMember(member);
                    siteMember.setType(SiteMember.TYPE_OUTTER);
                    siteMember.setSite(site);
                    siteMember.setStatus(SiteMember.STATUS_ACT);
                    siteMember.setJoinDate(new Date());
                    SiteMemberService.create(siteMember);
                    addActionMessage("添加新会员成功.");
                } else {
                    BaseException.threw("该成员不存在.");
                }
            } catch (BaseException e) {
                log.error(e);
                addActionError(e.getMessage());
            }
        }
        return edit();
    }

    /**
     * 当用户浏览该社区时
     *
     * @return
     */
    public HttpHeaders apply() {
        System.out.println("SiteMemberController.apply");
        if (getProfile() == null) return show();
        site = SiteService.getSite(id);
        if (site != null) {
            try {
                Member profile = getProfile();
                SiteMember member2 = SiteMemberService.getSiteMemberBySiteAndMember(site, profile);
                if (member2 != null) {
                    BaseException.threw("该社区已经是友好社区,或者您已经提交了申请.");
                }

                String remark = ServletActionContext.getRequest().getParameter("remark");
                siteMember = new SiteMember();
                siteMember.setMember(profile);
                siteMember.setType(SiteMember.TYPE_OUTTER);
                siteMember.setSite(site);
                siteMember.setStatus(SiteMember.STATUS_BAN);
                siteMember.setJoinDate(new Date());
                siteMember.setRemark(remark);
                SiteMemberService.create(siteMember);

            } catch (BaseException e) {
                log.error(e);
                addActionError(e.getMessage());
            }
            return show();
        } else
            return index();
    }

    /**
     * 修改状态,备注两个字段.
     *
     * @return HttpHeaders
     */
    public HttpHeaders update() {
        if (!haveRight()) return show();
        site = SiteService.getSite(id);
        String siteMemberId = ServletActionContext.getRequest().getParameter("siteMemberId");
        try {
            SiteMember member2;
            for (SiteMember member : siteMembers) {
                if (member.getId().equals(siteMemberId)) {
                    member2 = member;
                    SiteMemberService.update(member2);
                    break;
                }
            }
        } catch (Exception e) {
            log.error(e);
            addActionError(e.getMessage());
        }
        siteMembers.clear();
        return edit();
    }

    public HttpHeaders destroy() {
        if (!haveRight()) return show();
        site = SiteService.getSite(id);
        String siteMemberId = ServletActionContext.getRequest().getParameter("siteMemberId");
        try {
            if (siteMemberId != null) {
                SiteMember member = SiteMemberService.getMember(siteMemberId);
                if (member.getMember().getSite().getSiteId().equals(site.getSiteId())) {
                    BaseException.threw("只可以禁用,不可以删除本社区成员!");
                }
                SiteMemberService.delete(member);
            }
        } catch (Exception e) {
            log.error(e);
            addActionError(e.getMessage());
        }
        siteMembers.clear();
        return edit();
    }

    private boolean haveRight() {
        Member profile = getProfile();
        Site site = SiteService.getSite(id);
        haveRight = RightService.checkRight(profile, site);
        return haveRight;
    }
}
