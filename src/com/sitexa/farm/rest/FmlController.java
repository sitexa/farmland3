package com.sitexa.farm.rest;

import com.sitexa.farm.service.FarmService;
import com.sitexa.farm.service.LandService;
import com.sitexa.framework.config.AppConfig;
import com.sitexa.post.CsaType;
import com.sitexa.post.PostCategory;
import com.sitexa.post.data.PostPicture;
import com.sitexa.post.service.CsaService;
import com.sitexa.post.service.FarmPostService;
import com.sitexa.post.service.LandPostService;
import com.sitexa.post.service.PostTypeService;
import com.sitexa.user.service.MemberService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * User: xnpeng
 * Date: 2010-4-8
 * Time: 15:48:17
 */
public class FmlController extends FmlAction {
    private static final int cnt = 10;

    private void doCalc() {
        newPictures = CsaService.getNewPostPictures(cnt);
        news = CsaService.getNewPosts(PostTypeService.getObjectType(PostCategory.CSA.value(),
                CsaType.NEWS.value()).getTypeId(), cnt);
        posts = CsaService.getNewPosts(PostTypeService.getObjectType(PostCategory.CSA.value(),
                CsaType.STRATEGY.value()).getTypeId(), cnt);
        activities = CsaService.getNewPosts(PostTypeService.getObjectType(PostCategory.CSA.value(),
                CsaType.ACTIVITY.value()).getTypeId(), cnt);
        notices = CsaService.getNotice(cnt);
        farmPosts = FarmPostService.getNewFarmPosts(null, cnt);
        landPosts = LandPostService.getNewLandPosts(null, cnt);
        newLands = LandService.getNewLands(5);
        newFarms = FarmService.getNewFarms(4);
        hotFarms = FarmService.getHotFarmPictures(9);
        newMembers = MemberService.getNewMembers(true, 4);
    }

    public HttpHeaders index() {
        doCalc();
        return new DefaultHttpHeaders("");
    }

}
