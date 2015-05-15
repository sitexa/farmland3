package com.sitexa.farm.rest.join;

import com.sitexa.farm.data.Land;
import com.sitexa.farm.service.LandService;
import com.sitexa.farm.service.SeedService;
import com.sitexa.service.UpfileService;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * User: xnpeng
 * Date: 2010-4-22
 * Time: 10:35:20
 */
public class SeedController extends SeedAction {
    public HttpHeaders index() {
        member = getProfile();
        String landId = getParameter("landId");
        land = LandService.getById(landId);
        if (land == null) {
            java.util.List<Land> lands = LandService.getLandsByMember(member);
            if (lands.size() > 0) land = lands.get(0);
        }
        if (!haveRight()) internalRedirects("join/joinland");

        seeds = SeedService.getSeeds(land, page);
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders show() {
        seed = SeedService.getById(id);
        if (seed != null) land = seed.getLand();
        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders edit() {
        seed = SeedService.getById(id);
        if (seed != null) land = seed.getLand();
        member = getProfile();
        if (!haveRight()) internalRedirects("join/joinland");

        return new DefaultHttpHeaders("edit");
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

        seed.setLand(land);
        String imgName = getParameter("imgName");
        if (imgName != null) {
            seed.setImg(UpfileService.getImage(imgName));
        }
        SeedService.create(seed);
        seeds = SeedService.getSeeds(land, page);
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders update() {
        System.out.println("SeedController.update");
        String landId = getParameter("landId");
        land = LandService.getById(landId);
        member = getProfile();
        if (!haveRight()) internalRedirects("join/joinland");

        seed.setLand(land);
        String imgName = getParameter("imgName");
        if (imgName != null) {
            seed.setImg(UpfileService.getImage(imgName));
        }
        if (!"1".equals(seed.getStatus())) {
            seed.setStatus("");
        }

        SeedService.update(seed);
        seeds = SeedService.getSeeds(land, page);
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders destroy() {
        member = getProfile();
        seed = SeedService.getById(id);
        if (seed != null) land = seed.getLand();
        if (!haveRight()) internalRedirects("join/joinland");

        seeds = SeedService.getSeeds(land, page);
        return new DefaultHttpHeaders("index");
    }

    private boolean haveRight() {
        if (member == null || land == null) return false;
        if (member.getMemberId().equals(land.getLord().getMemberId())) return true;
        return false;
    }
}
