package com.sitexa.farm.rest.join;

import com.sitexa.farm.service.LandService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;

/**
 * User: xnpeng
 * Date: 2010-4-20
 * Time: 16:30:07
 */
@SuppressWarnings("unchecked")
public class LandController extends LandAction {
    public HttpHeaders index() {
        member = getProfile();
        if (member == null) {
            try {
                ServletActionContext.getResponse().sendRedirect("/logon");
            } catch (IOException ignored) {
            }
        }

        if (isAdmin())
            lands = LandService.getAllLand();
        else
            lands = LandService.getLandsByMember(member);

        if (lands.size() > 0) land = lands.get(0);
        if (land != null) {
            pictures.clear();
            pictures.addAll(land.getLandPictures());
        }
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders show() {
        member = getProfile();
        land = LandService.getById(id);
        if (haveRight() || isAdmin()) {
            if (land != null) {
                farms.clear();
                farms.addAll(land.getFarms());
                pictures.clear();
                pictures.addAll(land.getLandPictures());
            }
            return new DefaultHttpHeaders("show");
        } else {
            return index();
        }
    }

    public HttpHeaders edit() {
        land = LandService.getById(id);
        member = getProfile();

        if (haveRight() || isAdmin())
            return new DefaultHttpHeaders("edit");
        else
            return index();
    }

    public HttpHeaders editNew() {
        member = getProfile();
        if (isLandlord() || isAdmin())
            return new DefaultHttpHeaders("editNew");
        else
            return index();
    }

    public HttpHeaders create() {
        member = getProfile();
        if (isLandlord() || isAdmin()) {
            land.setLord(member);
            LandService.create(land);
            return new DefaultHttpHeaders("show");
        } else {
            addActionMessage("你的用户类型不正确");
            return index();
        }
    }

    public HttpHeaders update() {
        member = getProfile();
        if (haveRight() || isAdmin()) {
            LandService.update(land);
        } else return index();
        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders destroy() {
        member = getProfile();
        land = LandService.getById(id);
        if (haveRight() || isAdmin()) {
            try {
                LandService.delete(land.getLandId());
                addActionMessage("删除农场成功");
            } catch (Exception e) {
                addActionError("删除农场出错:" + e.getMessage());
            }
        }
        return index();
    }

    private boolean haveRight() {
        if (land == null) return false;
        if (member == null) return false;
        if (land.getLord().getMemberId().equals(member.getMemberId())) return true;
        return false;
    }
}
