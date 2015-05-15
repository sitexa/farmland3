package com.sitexa.market.rest.market;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.service.FarmService;
import com.sitexa.framework.config.StrutsConfig;
import com.sitexa.framework.util.DrawImage;
import com.sitexa.framework.util.FileUtil;
import com.sitexa.framework.util.ImageUtil;
import com.sitexa.market.action.MarketAction;
import com.sitexa.market.data.Market;
import com.sitexa.market.data.MarketPicture;
import com.sitexa.market.service.MarketService;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.sys.Sequence;
import com.sitexa.user.data.Member;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * User: xnpeng
 * Date: 2009-10-15
 * Time: 19:12:32
 */
public class MarketController extends MarketAction {
    private static Log log = LogFactory.getLog(MarketController.class);
    private static String SITEID = "430000";
    private static final String TEMP_DIR = "temp";

    public HttpHeaders index() {
        System.out.println("MarketController.index");
        HttpServletRequest request = ServletActionContext.getRequest();
        String siteId = this.getParameter("siteId");
        String itemType = this.getParameter("itemType");
        String cateId = this.getParameter("cateId");
        if (siteId == null || "".equals(siteId)) siteId = SITEID;
        if (itemType == null || "".equals(itemType)) itemType = "1";
        if (cateId == null || "".equals(cateId)) cateId = "1";

        site = SiteService.getSite(siteId);
        markets = MarketService.getMarkets(page, site, itemType, cateId);

        //传参给index
        market = null;
        request.setAttribute("isFarmer", this.isFarmer());
        request.setAttribute("itemType", itemType);
        request.setAttribute("cateId", cateId);
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders show() {
        System.out.println("MarketController.show");
        HttpServletRequest request = ServletActionContext.getRequest();
        market = MarketService.getMarket(id);
        //传参
        //String siteId = this.getParameter("siteId");
        //request.setAttribute("siteId", this.getParameter("siteId"));
        //request.setAttribute("itemType", this.getParameter("itemType"));
        //request.setAttribute("cateId", this.getParameter("cateId"));
        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders edit() {
        System.out.println("MarketController.edit");
        HttpServletRequest request = ServletActionContext.getRequest();
        market = MarketService.getMarket(id);
        if(!(haveRight(market) && market.getParent()==null))	return index();
        
        //传参给index
        request.setAttribute("siteId", request.getParameter("siteId"));
        request.setAttribute("itemType", request.getParameter("itemType"));
        request.setAttribute("cateId", request.getParameter("cateId"));
        return new DefaultHttpHeaders("edit");
    }

    public HttpHeaders editNew() {
        return null;
    }

    public HttpHeaders create() {
        System.out.println("MarketController.create");
        if(!this.isFarmer())	return index();	
        
        System.out.println("market.getItemType = " + market.getItemType());
        //Begin	获取参数 2009.11.25
        HttpServletRequest request = ServletActionContext.getRequest();
        String siteId = request.getParameter("siteId");
        String itemType = request.getParameter("itemType");
        String cateId = request.getParameter("cateId");
        String[] imgNames = request.getParameterValues("imgName");
        String[] imgTitles = request.getParameterValues("imgTitle");
        String[] imgDescriptions = request.getParameterValues("imgDescription");
        market.setCategory(MarketService.getMarketCategory(cateId));
        market.setItemType(itemType);
        //End
        Member profile = getProfile();
        //profile = MemberService.getMember(profile.getMemberId());
        market.setMember(profile);

        Site site = SiteService.getSite(siteId);
        market.setSite(site);

        List<Farm> farms = FarmService.getFarmByMember(profile);
        if(farms!=null && farms.size()>0) market.setFarm(farms.get(0));

        MarketService.save(market, imgNames, imgTitles, imgDescriptions);
        return index();
    }
    /**
     * 2010.3.15 对传参进行了修改
     * @return
     */
    public HttpHeaders reply() {
        System.out.println("MarketController.create");
        System.out.println("market.getItemType = " + market.getItemType());
        //Begin	获取参数 2009.11.25
        HttpServletRequest request = ServletActionContext.getRequest();
        //String siteId = request.getParameter("siteId");
        //String itemType = request.getParameter("itemType");
        //String cateId = request.getParameter("cateId");
        String parentId = request.getParameter("parentId");
        if (parentId == null && "".equals(parentId)) return index();
        Market parent = MarketService.getMarket(parentId);

    	market.setParent(parent);
        market.setCategory(parent.getCategory());
        market.setItemType(parent.getItemType());
        market.setSite(parent.getSite());
        //End
        Member profile = getProfile();
        //if (profile == null) profile = MemberService.getMember(profile.getMemberId());
        if (profile != null)	market.setMember(profile);

        market.setSite(site);
        
        //找到发布信息者(楼主)的农庄
        List<Farm> farms = FarmService.getFarmByMember(parent.getMember());
        if(farms!=null && farms.size()>0) market.setFarm(farms.get(0));
        
        MarketService.save(market);

        //传参
        id = parentId;
        market = MarketService.getMarket(id);
        //request.setAttribute("siteId", siteId);
        //request.setAttribute("itemType", itemType);
        //request.setAttribute("cateId", cateId);
        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders update() {
        System.out.println("MarketController.update");
        if(haveRight(market) && market.getParent()==null)	MarketService.update(market);
        return index();
    }

    public HttpHeaders destroy() {
        System.out.println("MarketController.destroy");
        market = MarketService.getMarket(id);
        if(market==null)	return index();
        if(!(haveRight(market) || haveRight(market.getParent())))	return index();// 是否为（信息发布者/回复者）
        
        HttpServletRequest request = ServletActionContext.getRequest();
        String parentId = request.getParameter("parentId");
        MarketService.delete(id);
        if(parentId!=null && !"".equals(parentId )){	//删除children才会回传parentId
            //request.setAttribute("siteId", request.getParameter("siteId"));
            //request.setAttribute("itemType", request.getParameter("itemType"));
            //request.setAttribute("cateId", request.getParameter("cateId"));
        	id = parentId;
        	return show();
        }
        return index();
    }

    /**
     * @author leim 2009.11.27
     * 上传文件
     */
    public void uploadImg() {
        System.out.println("MarketController.fileUpload");
        if (!acceptType(uploadContentType)) return;
        
        String newFilename = UUID.randomUUID().toString().replaceAll("-", "")
                + FileUtil.getExtention(uploadFileName);
        String title = getParameter("title");
        String description = getParameter("description");
        try {
        	String picId = Sequence.getId();
            MarketService.createMarketPicture(upload, newFilename, id, picId, title, description);
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("picId", picId);
            jsonObj.put("title", title);
            jsonObj.put("description", description);
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(jsonObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author leim    2009.11.27
     * 根据ID获取MarketPicture,处理图片数据:Abbrev,并通过数据流发送至客户端
     */
    public void getImage() {
        String picId = ServletActionContext.getRequest().getParameter("picId");
        MarketPicture marketPicture = MarketService.getMarketPictureById(picId);
        if (marketPicture != null) {
            byte[] imgData = marketPicture.getAbbrev();
            if (imgData != null) {
                BufferedImage img = ImageUtil.getDecompressedImage(imgData);
                try {
                    ImageIO.write(img, "JPEG", ServletActionContext.getResponse().getOutputStream());
                } catch (IOException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @author leim 2009.11.27
     * 上传文件
     */
    public void uploadImgTemp() {
        System.out.println("MarketController.addImg");
        if (!acceptType(uploadContentType)) return;
        //String title = getParameter("title");
        //String description = getParameter("description");
        try {
            String path = StrutsConfig.getSaveDir();
            path += File.separator + TEMP_DIR + File.separator;
            String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + FileUtil.getExtention(uploadFileName);
            //保存文件
            DrawImage.saveFile(upload, path, newFileName);
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("newFileName", newFileName);
            jsonObj.put("oldFileName", uploadFileName);
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(jsonObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author leim 2009.11.27
     * 删除图片
     */
    public void delMarketPicture() {
        boolean success = false;
        String picId = getParameter("picId");
        try {
        	if(!"".equals(picId))
        		success = MarketService.delMarketPictureById(picId);
            ServletActionContext.getResponse().getWriter().print(success);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author leim 2009.11.24
     * 更新图片
     */
    public void updateMarketPicture() {
        boolean success = false;
        String picId = getParameter("picId");
        String title = getParameter("title");
        String description = getParameter("description");
        JSONObject jsonObj = new JSONObject();
        try {
            if (picId != null && !"".equals(picId)) {
            	MarketPicture marketPicture = new MarketPicture();
            	marketPicture.setPicId(picId);
                marketPicture.setTitle(title);
                marketPicture.setDescription(description);
                MarketService.updateMarketPicture(marketPicture);
                success = true;
            }
            jsonObj.put("success", success);
            ServletActionContext.getResponse().getWriter().print(jsonObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //---------------------------------权限验证----------------------------------------
    /**
     * 是否为农庄主
     * @return boolean
     */
    private boolean isFarmer(){
    	Member member = getProfile();
    	if(member == null)	return false;
    	List farms = FarmService.getFarmByMember(member);
    	if(farms==null || farms.size()<=0)	return false;
    	return true;
    }
    /**
     * 是否为当前用户
     * @param land_
     * @return
     */
    private boolean haveRight(Market market){
    	if(getProfile() == null) return false;
    	Market market1 = MarketService.getMarket(market.getItemId());
        if (market1 == null || !getProfile().getMemberId().equals(market1.getMember().getMemberId())) return false;
        return true;
    }
    //-------------------------------other math()------------------------------------------------------------
    /**
     * 可接受的图片类型.
     *
     * @param type - 类型名
     * @return - boolean
     */
    private boolean acceptType(String type) {
    	type = type.toLowerCase();
        return ("image/gif".equalsIgnoreCase(type)) ||
                ("image/jpg".equalsIgnoreCase(type)) ||
                ("image/jpeg".equalsIgnoreCase(type)) ||
                ("image/pjpeg".equalsIgnoreCase(type)) ||
                ("image/bmp".equalsIgnoreCase(type)) ||
                ("image/png".equalsIgnoreCase(type));
    }

    /**
     * 获取参数
     *
     * @param name
     * @return
     */
    private String getParaValue(String name) {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (request.getParameter(name) != null && !"".equals(request.getParameter(name))) {
            return request.getParameter(name);
        }
        /**
        if (request.getAttribute(name) != null && !"".equals(request.getAttribute(name))) {
            return request.getAttribute(name).toString();
        }
        */
        return "";
    }

}
