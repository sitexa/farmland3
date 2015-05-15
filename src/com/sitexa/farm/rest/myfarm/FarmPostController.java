package com.sitexa.farm.rest.myfarm;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.FarmOwner;
import com.sitexa.farm.service.FarmService;
import com.sitexa.post.service.FarmPostService;
import com.sitexa.user.data.Member;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-16
 * Time: 17:02:02
 */
public class FarmPostController extends FarmPostAction {
    public HttpHeaders index() {
        member = getProfile();
        if (member == null) {
            try {
                ServletActionContext.getResponse()
                        .sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
            } catch (IOException ignored) {
            }
        }

        List<Farm> farms = FarmService.getFarmByMember(member);
        if (farms.size() > 0)
            farm = farms.get(0);

        if (farm != null) {
            posts = FarmPostService.getFarmPosts(farm, page, "");
        }

        return new DefaultHttpHeaders("");
    }

    public HttpHeaders show() {
        farm = FarmService.getById(id);
        if (!haveRight()) return index();
        posts = FarmPostService.getFarmPosts(farm, page, "");
        return new DefaultHttpHeaders("");
    }

    public HttpHeaders edit() {
        farm = FarmService.getById(id);
        if (!haveRight()) return index();
        posts = FarmPostService.getFarmPosts(farm, page, "");
        site = farm.getSite();									//2010.5.11 lei
        ServletActionContext.getRequest().setAttribute("siteId", site.getSiteId());
        return new DefaultHttpHeaders("");
    }

    public HttpHeaders editNew() {
        return null;
    }

    public HttpHeaders create() {
        return null;
    }

    public HttpHeaders update() {
        return null;
    }

    public HttpHeaders destroy() {
        return null;
    }

    private boolean haveRight() {
        member = getProfile();
        if (member == null) return false;
        if (farm == null || farm.getMember() == null) return false;
        for (Object o : farm.getOwners()) {
            FarmOwner owner = (FarmOwner) o;
            if (owner.getMember().getMemberId().equals(member.getMemberId())) return true;
        }
        Member lord = farm.getLand().getLord();
        if (member.getMemberId().equals(lord.getMemberId())) return true;
        if (!farm.getMember().getMemberId().equals(member.getMemberId())) return false;
        return true;
    }
}
