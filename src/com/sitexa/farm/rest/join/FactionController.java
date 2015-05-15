package com.sitexa.farm.rest.join;

import com.sitexa.farm.data.Land;
import com.sitexa.farm.service.FactionService;
import com.sitexa.farm.service.LandService;
import com.sitexa.farm.service.ServiceService;
import com.sitexa.service.UpfileService;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-22
 * Time: 10:35:20
 */
public class FactionController extends FactionAction {
    public HttpHeaders index() {
        System.out.println("FactionController.index");
        member = getProfile();
        if (land == null) {
            String landId = getParameter("landId");
            land = LandService.getById(landId);
        }
        if (land == null) {
            List<Land> lands = LandService.getLandsByMember(member);
            if (lands.size() > 0) land = lands.get(0);
        }
        if (!haveRight()) internalRedirects("join/joinland");
        factions = FactionService.getFactions(land);
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders show() {
        System.out.println("FactionController.show");
        member = getProfile();
        faction = FactionService.getById(id);
        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders edit() {
        System.out.println("FactionController.edit");
        member = getProfile();
        faction = FactionService.getById(id);
        if (faction != null) land = faction.getLand();
        if (!haveRight()) {
            try {
                ServletActionContext.getResponse()
                        .sendRedirect(ServletActionContext.getRequest().getContextPath() + "/join/joinland");
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new DefaultHttpHeaders("edit");
    }

    public HttpHeaders editNew() {
        member = getProfile();
        String landId = getParameter("landId");
        land = LandService.getById(landId);
        if (!haveRight()) internalRedirects("join/joinland");

        services = ServiceService.getServices();
        return new DefaultHttpHeaders("editNew");
    }

    public HttpHeaders create() {
        member = getProfile();
        String landId = getParameter("landId");
        land = LandService.getById(landId);
        if (!haveRight()) internalRedirects("join/joinland");

        faction.setLand(land);
        String imgName = getParameter("imgName");
        if (imgName == null) {
            service = ServiceService.getById(faction.getActionId());
            if (service != null) faction.setImg(service.getImg());
        } else {
            faction.setImg(UpfileService.getImage(imgName));
        }
        if (!"1".equals(faction.getStatus())) faction.setStatus("");
        FactionService.create(faction);
        return index();
    }

    public HttpHeaders update() {
        member = getProfile();
        land = LandService.getById(faction.getLand().getLandId());
        if (!haveRight()) internalRedirects("join/joinland");

        String imgName = getParameter("imgName");
        if (imgName != null) {
            faction.setImg(UpfileService.getImage(imgName));
        }
        FactionService.update(faction);
        return index();
    }

    public HttpHeaders destroy() {
        member = getProfile();
        faction = FactionService.getById(id);
        if (faction == null) return index();
        land = faction.getLand();
        if (!haveRight()) internalRedirects("join/joinland");

        FactionService.delete(faction);
        return index();
    }

    public void getServiceData() {
        String svcId = ServletActionContext.getRequest().getParameter("svcId");
        service = ServiceService.getById(svcId);
        if (service != null) {
            JSONObject json = new JSONObject();
            json.put("svcName", service.getSvcName());
            json.put("expense", service.getExpense());
            json.put("materials", service.getMaterials());
            json.put("contents", service.getContents());
            json.put("remark", service.getRemark());
            try {
                ServletActionContext.getResponse().setCharacterEncoding("utf-8");
                ServletActionContext.getResponse().getWriter().print(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean haveRight() {
        if (member == null || land == null) return false;
        if (land.getLord() == null) return false;
        if (member.getMemberId().equals(land.getLord().getMemberId())) return true;
        return false;
    }


}
