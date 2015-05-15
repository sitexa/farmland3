package com.sitexa.farm.rest.myfarm;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.service.FarmOwnerService;
import com.sitexa.farm.service.FarmService;
import com.sitexa.user.data.Member;
import com.sitexa.user.service.MemberService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-16
 * Time: 16:52:27
 */
@SuppressWarnings("unchecked")
public class FarmOwnerController extends FarmOwnerAction {
    public HttpHeaders index() {
        System.out.println("FarmOwnerController.index");
        member = getProfile();
        if (member == null) {
            try {
                ServletActionContext.getResponse()
                        .sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
            } catch (IOException ignored) {
            }
        }

        List<Farm> farms = FarmService.getFarmByMember(member);
        if (farms.size() > 0) {
            farm = farms.get(0);
            owners.clear();
            owners.addAll(farm.getOwners());
        }
        return new DefaultHttpHeaders("");
    }

    public HttpHeaders show() {
        farm = FarmService.getById(id);
        if (!haveRight()) return index();
        owners.clear();
        owners.addAll(farm.getOwners());
        return new DefaultHttpHeaders("");
    }

    public HttpHeaders edit() {
        farm = FarmService.getById(id);
        if (!haveRight()) return index();
        owners.clear();
        owners.addAll(farm.getOwners());
        return new DefaultHttpHeaders("");
    }

    public HttpHeaders editNew() {
        return null;
    }

    public HttpHeaders create() {
        System.out.println("FarmOwnerController.create");
        farm = FarmService.getById(id);
        if (!haveRight()) return index();

        System.out.println("farm.getFarmId() = " + farm.getFarmId());
        System.out.println("owner.getMember().getMemberId() = " + owner.getMember().getMemberId());

        if (owner.getMember().getMemberId() != null || !"".equals(owner.getMember().getMemberId())) {
            Member m = MemberService.getMember(owner.getMember().getMemberId());
            owner.setMember(m);
            owner.setFarm(farm);
            FarmOwnerService.create(owner);
        }
        owners.clear();
        owners.addAll(farm.getOwners());
        return new DefaultHttpHeaders("");
    }

    public HttpHeaders update() {
        System.out.println("FarmOwnerController.update");
        farm = FarmService.getById(id);
        if (!haveRight()) return index();
        if (owner.getOwnerId() != null) {
            FarmOwnerService.update(owner);
        }
        owners.clear();
        owners.addAll(farm.getOwners());
        return new DefaultHttpHeaders("");
    }

    public HttpHeaders destroy() {
        System.out.println("FarmOwnerController.destroy");
        farm = FarmService.getById(id);
        if (!haveRight()) return index();
        if (owner.getOwnerId() != null) {
            FarmOwnerService.delete(owner);
        }
        owners.clear();
        owners.addAll(farm.getOwners());
        return new DefaultHttpHeaders("");
    }

    private boolean haveRight() {
        member = getProfile();
        if (member == null) return false;
        if (farm == null || farm.getMember() == null) return false;
        Member lord = farm.getLand().getLord();
        if (member.getMemberId().equals(lord.getMemberId())) return true;
        if (!farm.getMember().getMemberId().equals(member.getMemberId())) return false;
        return true;
    }
}
