/**
 * @作者 leim
 * @创建日期 2010-6-11
 * @版本 V 1.0
 */
package com.sitexa.farm.rest.myland;

import com.sitexa.farm.data.FarmPlan;
import com.sitexa.farm.data.Land;
import com.sitexa.farm.service.LandService;
import com.sitexa.farm.service.PlanService;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

public class PlanController extends PlanAction {

    public HttpHeaders index() {
        member = getProfile();
        String landId = getParameter("landId");
        land = LandService.getById(landId);
        if (land == null) {
            java.util.List<Land> lands = LandService.getLandsByMember(member);
            if (lands.size() > 0) land = lands.get(0);
        }
        if (!haveRight()) internalRedirects("join/joinland");

        farmPlans = PlanService.getPlans(land, page);
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders editNew() {
        String landId = getParameter("landId");
        land = LandService.getById(landId);
        member = getProfile();
        if (!haveRight()) internalRedirects("join/joinland");

        return new DefaultHttpHeaders("editNew");
    }

    public HttpHeaders create() {
        String landId = getParameter("landId");
        land = LandService.getById(landId);
        member = getProfile();
        if (!haveRight()) internalRedirects("join/joinland");

        plan.setLand(land);
        PlanService.create(plan);
        farmPlans = PlanService.getPlans(land, page);
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders edit() {
        member = getProfile();
        if (id != null && !"".equals(id)) plan = PlanService.getById(id);
        if (plan != null) land = plan.getLand();
        if (!haveRight()) internalRedirects("join/joinland");

        return new DefaultHttpHeaders("edit");
    }

    public HttpHeaders update() {
        member = getProfile();
        FarmPlan plan1 = null;
        if (plan != null && plan.getPlanId() != null && !"".equals(plan.getPlanId())) {
            plan1 = PlanService.getById(plan.getPlanId());
        }
        if (plan1 != null) land = plan1.getLand();
        if (!haveRight()) internalRedirects("join/joinland");

        //plan.setStarLand(land);
        PlanService.update(plan);
        farmPlans = PlanService.getPlans(land, page);
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders destroy() {
        member = getProfile();
        plan = PlanService.getById(id);
        if (plan != null) land = plan.getLand();
        if (!haveRight()) internalRedirects("join/joinland");

        PlanService.delete(plan);
        farmPlans = PlanService.getPlans(land, page);
        return new DefaultHttpHeaders("index");
    }

    private boolean haveRight() {
        if (member == null || land == null) return false;
        if (member.getMemberId().equals(land.getLord().getMemberId())) return true;
        return false;
    }
}
