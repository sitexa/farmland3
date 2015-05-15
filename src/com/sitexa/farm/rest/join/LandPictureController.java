package com.sitexa.farm.rest.join;

import com.sitexa.farm.data.LandPicture;
import com.sitexa.farm.service.LandPictureService;
import com.sitexa.farm.service.LandService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * User: xnpeng
 * Date: 2010-4-21
 * Time: 11:35:09
 */
public class LandPictureController extends LandPictureAction {
    public HttpHeaders index() {
        System.out.println("LandPictureController.index");
        String landId = getParameter("landId");
        land = LandService.getById(landId);
        member = getProfile();
        if (!haveRight()) return new DefaultHttpHeaders("");
        if (land != null) {
            pictures.clear();
            pictures.addAll(land.getLandPictures());
        }
        return new DefaultHttpHeaders("");
    }

    public HttpHeaders show() {
        land = LandService.getById(id);
        member = getProfile();
        if (!haveRight()) return new DefaultHttpHeaders("");
        if (land != null) {
            pictures.clear();
            pictures.addAll(land.getLandPictures());
        }
        return new DefaultHttpHeaders("");
    }

    public HttpHeaders update() {
        land = LandService.getById(id);
        member = getProfile();
        if (!haveRight()) return show();
        try {
            LandPictureService.updatePictures(pictures);
        } catch (Exception e) {
            addActionError(e.toString());
        }
        createPictures();
        pictures.clear();
        return show();
    }

    public HttpHeaders destroy() {
        System.out.println("LandPictureController.destroy");
        land = LandService.getById(id);
        member = getProfile();
        if (!haveRight()) return show();
        String picId = ServletActionContext.getRequest().getParameter("picId");
        try {
            LandPictureService.deletePicture(picId);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("删除图片出错:" + e.getMessage());
        }
        pictures.clear();
        return show();
    }

    private void createPictures() {
        try {
            ArrayList<LandPicture> pics = gatherPictureInfo();
            LandPictureService.createPictures(pics, upload, uploadFileName, uploadContentType);
        } catch (Exception e) {
            addActionError(e.toString());
        }
    }

    private ArrayList<LandPicture> gatherPictureInfo() {
        ArrayList<LandPicture> pics = new ArrayList<LandPicture>();
        HttpServletRequest req = ServletActionContext.getRequest();
        String[] t = req.getParameterValues("title");
        String[] d = req.getParameterValues("description");
        for (int i = 0; i < t.length; i++) {
            pics.add(new LandPicture(null, land, t[i], d[i]));
        }
        return pics;
    }

    private boolean haveRight() {
        if (member == null) return false;
        if (land == null) return false;
        if (member.getMemberId().equals(land.getLord().getMemberId())) return true;
        return false;
    }
}
