package com.sitexa.rest;

import com.sitexa.farm.data.Faction;
import com.sitexa.farm.data.FarmPicture;
import com.sitexa.farm.data.LandPicture;
import com.sitexa.farm.data.Seed;
import com.sitexa.farm.data.Service;
import com.sitexa.farm.service.FactionService;
import com.sitexa.farm.service.FarmService;
import com.sitexa.farm.service.LandService;
import com.sitexa.farm.service.SeedService;
import com.sitexa.farm.service.ServiceService;
import com.sitexa.framework.util.ImageUtil;
import com.sitexa.post.data.PostPicture;
import com.sitexa.post.service.PostService;
import com.sitexa.site.data.SitePicture;
import com.sitexa.site.service.SitePictureService;
import com.sitexa.user.data.MemberPicture;
import com.sitexa.user.service.MemberPictureService;
import org.apache.struts2.ServletActionContext;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * User: xnpeng
 * Date: 2009-5-13
 * Time: 16:08:46
 */
public class ImageController {
    public void index() {
        byte[] imgData = null;
        String picId = ServletActionContext.getRequest().getParameter("picId");
        String type = ServletActionContext.getRequest().getParameter("type");
        if ("p".equalsIgnoreCase(type)) {
            PostPicture pic = PostService.getPicture(picId);
            if (pic != null) imgData = pic.getAbbrev();
        } else if ("m".equalsIgnoreCase(type)) {
            MemberPicture pic = MemberPictureService.getPicture(picId);
            if (pic != null) imgData = pic.getAbbrev();
        } else if ("s".equalsIgnoreCase(type)) {
            SitePicture pic = SitePictureService.getPicture(picId);
            if (pic != null) imgData = pic.getAbbrev();
        } else if ("land".equalsIgnoreCase(type)) {
            LandPicture pic = LandService.getLandPictureById(picId);
            if (pic != null) imgData = pic.getAbbrev();
        } else if ("farm".equalsIgnoreCase(type)) {
            FarmPicture pic = FarmService.getFarmPictureById(picId);
            if (pic != null) imgData = pic.getAbbrev();
        } else if ("seed".equalsIgnoreCase(type)) {
            Seed seed = SeedService.getById(picId);
            if (seed != null) imgData = seed.getImg();
        } else if ("service".equalsIgnoreCase(type)) {
            Service service = ServiceService.getById(picId);
            if (service != null) imgData = service.getImg();
        } else if ("faction".equalsIgnoreCase(type)) {
        	Faction faction = FactionService.getById(picId);
        	if (faction != null) imgData = faction.getImg();
        }
        if (imgData != null) {
            BufferedImage img = ImageUtil.getDecompressedImage(imgData);
            try {
                ImageIO.write(img, "JPEG", ServletActionContext.getResponse().getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void show() {
        index();
    }

    public void edit() {
        index();
    }

    public void editNew() {
        index();
    }

    public void create() {
        index();
    }

    public void update() {
        index();
    }

    public void destroy() {
        index();
    }

    public void codeImage() {
        int width = 70, height = 20;
        String code = "";
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        g.setColor(getRandColor(160, 200));

        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            code += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 14 * i + 10, 16);
        }
        g.dispose();

        try {
            ServletActionContext.getRequest().getSession().setMaxInactiveInterval(2 * 60 * 60);
            ServletActionContext.getRequest().getSession().setAttribute("code", code);
            ImageIO.write(image, "JPEG", ServletActionContext.getResponse().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
