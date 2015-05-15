package com.sitexa.farm.rest.myfarm;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.FarmOwner;
import com.sitexa.farm.service.FarmService;
import com.sitexa.user.data.Member;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-14
 * Time: 21:39:41
 */
public class FarmController extends FarmAction {
    public HttpHeaders index() {
        System.out.println("FarmController.index");
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

        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders show() {
        farm = FarmService.getById(id);
        if (!haveRight()) return index();
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders edit() {
        farm = FarmService.getById(id);
        if (!haveRight()) return index();
        return new DefaultHttpHeaders("edit");
    }

    public HttpHeaders update() {
        Farm farm1 = farm;
        farm = FarmService.getById(farm1.getFarmId());
        if (!haveRight()) return index();
        farm = farm1;
        farm = FarmService.updateNameAndSlogan(farm);
        return new DefaultHttpHeaders("edit");
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

    private boolean acceptType(String type) {
        return ("image/gif".equalsIgnoreCase(type)) ||
                ("image/jpg".equalsIgnoreCase(type)) ||
                ("image/jpeg".equalsIgnoreCase(type)) ||
                ("image/pjpeg".equalsIgnoreCase(type)) ||
                ("image/bmp".equalsIgnoreCase(type)) ||
                ("image/png".equalsIgnoreCase(type));
    }
}
