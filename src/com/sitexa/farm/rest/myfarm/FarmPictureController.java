package com.sitexa.farm.rest.myfarm;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.FarmOwner;
import com.sitexa.farm.data.FarmPicture;
import com.sitexa.farm.service.FarmPictureService;
import com.sitexa.farm.service.FarmService;
import com.sitexa.user.data.Member;
import com.sitexa.user.rest.user.MemberPictureController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-16
 * Time: 14:47:10
 */
public class FarmPictureController extends FarmPictureAction {
	private static Log log = LogFactory.getLog(FarmPictureController.class);
	 
    public HttpHeaders index() {
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
            pictures.clear();
            pictures.addAll(farm.getFarmPictures());
        }
        return new DefaultHttpHeaders("");
    }

    public HttpHeaders show() {
        farm = FarmService.getById(id);
        if (!haveRight()) return index();
        pictures.clear();
        pictures.addAll(farm.getFarmPictures());
        return new DefaultHttpHeaders("");
    }

    public HttpHeaders edit() {
        farm = FarmService.getById(id);
        if (!haveRight()) return index();
        pictures.clear();
        pictures.addAll(farm.getFarmPictures());
        return new DefaultHttpHeaders("");
    }

    public HttpHeaders editNew() {
        return null;
    }

    public HttpHeaders create() {
        return null;
    }

    public HttpHeaders update() {
    	farm = FarmService.getById(id);
        if (!haveRight()) return show();
        if (farm != null) {
            try {
                FarmPictureService.updatePictures(pictures);
            } catch (Exception e) {
                addActionError(e.toString());
            }
            createPictures();
        }
        return show();
    }

    public HttpHeaders destroy() {
    	farm = FarmService.getById(id);
        if (!haveRight()) return show();
        String picId = getParameter("picId");
        try {
            FarmPictureService.deletePicture(picId);
        } catch (Exception e) {
            addActionError(e.toString());
        }
        return show();
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

    public void createPictures() {
        try {
            ArrayList<FarmPicture> pics = gatherPictureInfo();
            FarmPictureService.createPictures(pics, upload, uploadFileName, uploadContentType);
        } catch (Exception e) {
            addActionError(e.toString());
        }
    }

    private ArrayList<FarmPicture> gatherPictureInfo() {
        ArrayList<FarmPicture> pics = new ArrayList<FarmPicture>();
        HttpServletRequest req = ServletActionContext.getRequest();
        String[] t = req.getParameterValues("picTitle");
        String[] d = req.getParameterValues("description");
        for (int i = 0; i < t.length; i++) {
            pics.add(new FarmPicture(null, farm, t[i], d[i]));
        }
        return pics;
    }

}
