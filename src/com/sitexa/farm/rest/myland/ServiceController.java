package com.sitexa.farm.rest.myland;

import com.sitexa.farm.service.ServiceService;
import com.sitexa.framework.util.ImageUtil;
import com.sitexa.sys.service.RoleService;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * User: xnpeng
 * Date: 2010-4-22
 * Time: 10:14:33
 */
public class ServiceController extends ServiceAction {
    public HttpHeaders index() {
        System.out.println("ServiceController.index");
        if (haveRight()) {
            services = ServiceService.getServices();
        }
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders show() {
        service = ServiceService.getById(id);
        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders edit() {
        if (!haveRight()) return index();
        service = ServiceService.getById(id);
        return new DefaultHttpHeaders("edit");
    }

    public HttpHeaders editNew() {
        if (!haveRight()) return index();
        return new DefaultHttpHeaders("editNew");
    }

    public HttpHeaders create() {
        if (!haveRight()) return index();
        try {
            Image img = ImageUtil.abbrev(ImageIO.read(new FileInputStream(upload)));
            byte[] imgData = ImageUtil.getCompressedImage(img);
            service.setImg(imgData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ServiceService.create(service);
        id = service.getSvcId();
        return edit();
    }

    public HttpHeaders update() {
        System.out.println("ServiceController.update");
        if (!haveRight()) return index();
        if (upload != null) {
            try {
                Image img = ImageUtil.abbrev(ImageIO.read(new FileInputStream(upload)));
                byte[] imgData = ImageUtil.getCompressedImage(img);
                service.setImg(imgData);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ServiceService.update(service);
        id = service.getSvcId();
        return edit();
    }

    public HttpHeaders destroy() {
        if (haveRight()) {
            ServiceService.delete(id);
        }
        return index();
    }

    private boolean haveRight() {
        member = getProfile();
        if (member == null) return false;
        return RoleService.getRoleInUser(member.getUser(), RoleService.getRole("4")) != null;
    }
}
