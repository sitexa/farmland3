package com.sitexa.rest;

import com.sitexa.action.MainAction;
import com.sitexa.farm.service.FarmService;
import com.sitexa.farm.service.LandService;
import com.sitexa.post.service.CsaService;
import com.sitexa.post.service.FarmPostService;
import com.sitexa.post.service.LandPostService;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.service.MemberService;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * User: xnpeng
 * Date: 2009-4-14
 * Time: 20:45:49
 */
public class MainController extends MainAction {

    private static final int cnt = 10;

    private void doCalc() {
        try {
            newPictures = CsaService.getNewPostPictures(cnt);

            news = CsaService.getNewPosts(27);
            notices = CsaService.getNotice(cnt);

            farmPosts = FarmPostService.getNewFarmPosts(null, 20);
            landPosts = LandPostService.getNewLandPosts(null, 20);

            starLand = LandService.getStarLand();
            newLands = LandService.getNewLands(cnt);
            newFarms = FarmService.getNewFarms(17);
            newMembers = MemberService.getNewMembers(true, 17);
            hotFarms = FarmService.getHotFarmPictures(18);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HttpHeaders index() {
        System.out.println("MainController.index");
        try {
            doCalc();
            site = getDefaultSite();
            lands = LandService.getAllLand();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DefaultHttpHeaders("").disableCaching();
    }

    public HttpHeaders show() {
        System.out.println("MainController.show");
        try {
            site = SiteService.getSite(id);
            lands = LandService.getLandsBySite(site);
            doCalc();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DefaultHttpHeaders("").disableCaching();
    }

    private Site getCity(Site site1) {
        if (site1 == null) return SiteService.getRoot();
        String type = site1.getType().getTypeId();
        int typeId = Integer.parseInt(type);
        if (typeId < 3) return site1;
        if (typeId >= 3) return site1.getCity();
        else return SiteService.getRoot();
    }

}
