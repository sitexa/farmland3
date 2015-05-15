package com.sitexa.user.action;

import com.sitexa.framework.Constants;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.sys.service.RoleService;
import com.sitexa.user.data.Member;
import com.sitexa.user.data.MemberCredit;
import com.sitexa.user.data.MemberCreditLog;
import com.sitexa.user.service.VvPay;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-12-2
 * Time: 10:46:50
 */
public class MemberCreditAction extends BaseAction {
    protected String id;
    protected Member member;
    protected MemberCredit mc;
    protected String point;
    protected List<MemberCreditLog> mclogs = new ArrayList<MemberCreditLog>();
    protected VvPay vvPay;
    protected String perm = "0";

    protected Page page = new Page(20);

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public MemberCredit getMc() {
        return mc;
    }

    public void setMc(MemberCredit mc) {
        this.mc = mc;
    }

    public List<MemberCreditLog> getMclogs() {
        return mclogs;
    }

    public void setMclogs(List<MemberCreditLog> mclogs) {
        this.mclogs = mclogs;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public VvPay getVvPay() {
        return vvPay;
    }

    public void setVvPay(VvPay vvPay) {
        this.vvPay = vvPay;
    }

    public String getPerm() {
        if (getProfile() != null && RoleService.getRoleInUser(getMe(), RoleService.getRole("4")) != null) {
            perm = "l";
        } else {
            perm = "0";
        }
        return perm;
    }

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
