package com.sitexa.farm.rest.myland;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.service.FarmService;
import com.sitexa.farm.service.LandService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-21
 * Time: 9:10:17
 */
public class FarmController extends FarmAction {

    public HttpHeaders index() {
        System.out.println("FarmController.index");
        member = getProfile();
        if (member == null) {
            try {
                ServletActionContext.getResponse().sendRedirect("/logon");
            } catch (IOException ignored) {
            }
        }

        String landId = getParameter("landId");
        if (landId != null) land = LandService.getById(landId);

        if (land != null) {
            farms = FarmService.getFarmByLand3(land, page);
            if (farms.size() > 0) farm = farms.get(0);
        }
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders show() {
        System.out.println("FarmController.show");
        farm = FarmService.getById(id);
        if (farm != null) land = farm.getLand();
        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders edit() {
        System.out.println("FarmController.edit");
        farm = FarmService.getById(id);
        if (farm != null) land = farm.getLand();
        member = getProfile();
        if (haveRight() || isAdmin())
            return new DefaultHttpHeaders("edit");
        else
            return index();
    }

    public HttpHeaders editNew() {
        System.out.println("FarmController.editNew");
        String landId = getParameter("landId");
        land = LandService.getById(landId);
        member = getProfile();
        if (haveRight() || isAdmin()) {
            return new DefaultHttpHeaders("editNew");
        } else {
            return index();
        }
    }

    public HttpHeaders create() {
        System.out.println("FarmController.create");
        member = getProfile();
        land = LandService.getById(land.getLandId());
        if (haveRight() || isAdmin()) {
            try {
                farm.setLand(land);
                FarmService.create(farm);
                addActionMessage("创建成功");
            } catch (Exception e) {
                addActionError("创建出错:" + e.getMessage());
            }
        }
        return index();
    }

    public HttpHeaders batch() {
        System.out.println("FarmController.batch");
        String landId = getParameter("landId");
        if (land == null) land = LandService.getById(landId);
        member = getProfile();
        if (haveRight() || isAdmin()) {
            if (land != null) {
                farms = FarmService.getFarmByLand3(land, page);
            }
        } else
            return index();
        return new DefaultHttpHeaders("batch");
    }

    public HttpHeaders update() {
        System.out.println("FarmController.update");
        member = getProfile();
        Farm f = FarmService.getById(farm.getFarmId());
        if (f != null) {
            land = f.getLand();
            id = f.getFarmId();
        }
        if (haveRight() || isAdmin()) {
            try {
                FarmService.update(farm);
                addActionMessage("保存成功");
            } catch (Exception e) {
                addActionError("保存出错:" + e.getMessage());
            }
            return show();
        } else {
            return index();
        }
    }

    public HttpHeaders updateBatch() {
        System.out.println("FarmController.update");
        land = LandService.getById(id);
        member = getProfile();
        if (haveRight() || isAdmin()) {
            try {
                FarmService.updateFarms(farms);
            } catch (Exception e) {
                addActionError(e.toString());
            }
            createFarms();
            farms.clear();
        } else {
            return show();
        }
        return batch();
    }

    private void createFarms() {
        HttpServletRequest req = ServletActionContext.getRequest();
        String[] farmNo = req.getParameterValues("farmNo");
        String[] acreage = req.getParameterValues("acreage");
        site = land.getSite();
        List<Farm> fs = new ArrayList<Farm>();
        for (int i = 0; i < acreage.length; i++) {
            String no = farmNo[i];
            String ac = acreage[i];
            if (no == null || "".equals(no) || ac == null || "".equals(ac)) continue;
            Long l = 0l;
            try {
                l = Long.parseLong(ac);
            } catch (Exception ignored) {
            }
            fs.add(new Farm(null, land, no, l));
        }
        try {
            if (fs.size() > 0)
                FarmService.createFarms(fs);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public HttpHeaders destroy() {
        farm = FarmService.getById(id);
        if (farm != null) {
            land = farm.getLand();
            member = getProfile();
            if (haveRight() || isAdmin()) {
                try {
                    FarmService.delete(id);
                } catch (Exception e) {
                    e.printStackTrace();
                    addActionError("删除出错:" + e.getMessage());
                }
                farms.clear();
            } else {
                return show();
            }
        }
        String type = getParameter("type");
        if (type != null && type.equals("batch")) {
            return batch();
        }
        return index();
    }

    private boolean haveRight() {
        if (member == null) return false;
        if (land == null) return false;
        if (member.getMemberId().equals(land.getLord().getMemberId())) return true;
        return false;
    }
}

