package com.sitexa.farm.rest.admin.work;

import com.sitexa.farm.data.Farming;
import com.sitexa.farm.service.CropService;
import com.sitexa.farm.service.FarmService;
import com.sitexa.farm.service.FarmingService;
import com.sitexa.farm.service.LandService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

/**
 * User: xnpeng
 * Date: 2010-3-3
 * Time: 9:43:42
 */
@SuppressWarnings("unchecked")
public class WorkController extends WorkAction {
    public HttpHeaders index() {
        System.out.println("WorkController.index");
        member = getProfile();
        if (!isLander()) internalRedirects("");
        lands = LandService.getLandsByMember(member);
        return new DefaultHttpHeaders("index");
    }



    public HttpHeaders farms() {
        System.out.println("WorkController.farms");
        member = getProfile();
        if (!isLander()) {
            try {
                ServletActionContext.getResponse().sendRedirect("/" + ServletActionContext.getRequest().getContextPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String landId = ServletActionContext.getRequest().getParameter("landId");
        if (!"".equals(landId)) {
            land = LandService.getById(landId);
        }
        if (land == null) {
            lands = LandService.getLandsByMember(member);
            if (lands != null && lands.size() > 0) {
                land = lands.get(0);
            }
        }
        farms = FarmService.getFarmByLand(land, page);
        return new DefaultHttpHeaders("farms");
    }

    public HttpHeaders tasks() {
        System.out.println("WorkController.tasks");
        member = getProfile();
        if (!isLander()) {
            try {
                ServletActionContext.getResponse().sendRedirect("/" + ServletActionContext.getRequest().getContextPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String farmId = ServletActionContext.getRequest().getParameter("farmId");
        if (!"".equals(farmId)) {
            farm = FarmService.getById(farmId);
            land = farm.getLand();
        }
        task = WorkService.getFarmingByFarm(farm, page);
        return new DefaultHttpHeaders("tasks");
    }

    public HttpHeaders reply() {
        System.out.println("WorkController.reply");
        member = getProfile();
        if (!isLander()) {
            try {
                ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getContextPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String farmingId = ServletActionContext.getRequest().getParameter("farmingId");
        if (farmingId == null || "".equals(farmingId)) {
            addActionError("无指令错误！");
            return new DefaultHttpHeaders("reply");
        }
        farming = FarmingService.getById(farmingId);
        if (farming != null) {
            farm = farming.getFarm();
            land = farming.getFarm().getLand();
        }

        return new DefaultHttpHeaders("reply");
    }

    public HttpHeaders saveReply() {
        System.out.println("WorkController.saveReply");
        member = getProfile();
        if (!isLander()) {
            try {
                ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getContextPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (farming != null && !"".equals(farming.getFarmingId())) {
            Farming farming1 = FarmingService.getById(farming.getFarmingId());
            if (farming1 != null) {

                String state = farming.getState();
                if ("true".equals(state) || "on".equals(state))
                    farming1.setState("1");

                String amt = farming1.getAmount();
                if (amt == null) amt = "";
                farming1.setAmount(farming.getAmount());

                String remark = farming1.getRemark();
                if (remark == null) remark = "";
                String dt = DateFormat.getDateInstance().format(new Date());
                remark += "【" + dt + "】【原费用：" + amt + "】" + farming.getRemark();

                farming1.setRemark(remark);
                FarmingService.update(farming1);
            }
        }

        return new DefaultHttpHeaders("saved");
    }

    public HttpHeaders crops() {
        System.out.println("WorkController.crops");
        member = getProfile();
        if (!isLander()) {
            try {
                ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getContextPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String farmId = ServletActionContext.getRequest().getParameter("farmId");
        if (farmId == null || "".equals(farmId)) return new DefaultHttpHeaders("crops");
        farm = FarmService.getById(farmId);
        if (farm != null) land = farm.getLand();
/*
        List cropsList = WorkService.getCropsList(farm, page);
        ServletActionContext.getRequest().setAttribute("farmId", farmId);
        ServletActionContext.getRequest().setAttribute("cropsList", cropsList);
*/
        cropses = CropService.getByFarm(farm);
        return new DefaultHttpHeaders("crops");
    }

    public HttpHeaders farming() {
        System.out.println("WorkController.farming");
        member = getProfile();
        if (!isLander()) {
            try {
                ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getContextPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String farmId = ServletActionContext.getRequest().getParameter("farmId");
        String seedId = ServletActionContext.getRequest().getParameter("seedId");
        if (farmId == null || "".equals(farmId)) return new DefaultHttpHeaders("farming");
        farm = FarmService.getById(farmId);
        if (farm != null) land = farm.getLand();
        farmings = FarmingService.getFarming(farmId, seedId, page);
        ServletActionContext.getRequest().setAttribute("farmId", farmId);
        ServletActionContext.getRequest().setAttribute("seedId", seedId);
        return new DefaultHttpHeaders("farming");
    }

    public void farmingWork() {
        member = getProfile();
        if (!isLander()) {
            return;
        }
        String farmingId = ServletActionContext.getRequest().getParameter("farmingId");
        boolean success = WorkService.farmingWork(farmingId);
        try {
            ServletActionContext.getResponse().getWriter().print(success);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateRemark() {
        member = getProfile();
        if (!isLander()) {
            return;
        }
        String farmingId = ServletActionContext.getRequest().getParameter("farmingId");
        String remark = ServletActionContext.getRequest().getParameter("remark");
        boolean success = WorkService.updateRemark(farmingId, remark);
        try {
            ServletActionContext.getResponse().getWriter().print(success);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HttpHeaders edit() {
        System.out.println("WorkController.edit");
        String cropsId = id;
        crops = CropService.getById(cropsId);
        return new DefaultHttpHeaders("edit");
    }

    protected boolean isLander() {
        if (member == null || !member.getType().getTypeId().equals("6")) return false;
        return true;
    }

    public HttpHeaders msg() {
        System.out.println("WorkController.msg");
        return new DefaultHttpHeaders("msg");
    }

    public void getmsg() {
        System.out.println("WorkController.getmsg");
        try {
            ServletActionContext.getResponse().getWriter().println("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.1//EN' 'http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd'> <html><head><script>alert(1)</script></head><body></body></html>");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
