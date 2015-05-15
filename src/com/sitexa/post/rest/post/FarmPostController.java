package com.sitexa.post.rest.post;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.Land;
import com.sitexa.farm.service.FarmService;
import com.sitexa.framework.Constants;
import com.sitexa.post.data.Category;
import com.sitexa.post.data.FarmPost;
import com.sitexa.post.data.PostType;
import com.sitexa.post.service.CategoryService;
import com.sitexa.post.service.FarmPostService;
import com.sitexa.post.service.PostService;
import com.sitexa.post.service.PostTypeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-1-7
 * Time: 19:24:39
 */
public class FarmPostController extends PostController {
    private static Log log = LogFactory.getLog(FarmPostController.class);
    private final String CATE_ID = Category.FARMPOST;

    private Land land;
    private Farm farm;
    private FarmPost farmPost;

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public FarmPost getFarmPost() {
        return farmPost;
    }

    public void setFarmPost(FarmPost farmPost) {
        this.farmPost = farmPost;
    }

    public void prepare() {
        postTypes = PostTypeService.getObjectTypes("farmpost");
    }

    //////////////////////////

    public HttpHeaders index() {
        getFarmPosts();
        return new DefaultHttpHeaders("index");
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders show() {
        post = PostService.getPost(id);
        if (post != null) {
            pictures.addAll(post.getPictures());
            farm = FarmPostService.getById(post.getId()).getFarm();
            ServletActionContext.getRequest().getSession().setAttribute(Constants.FARM, farm.getFarmId());
            //posts = FarmPostService.getFarmPosts(farm, page, null);
            return new DefaultHttpHeaders("show");
        } else {
            return index();
        }
    }

    public HttpHeaders edit() {
        if (!haveRight()) {
            return index();
        }
        post = PostService.getPost(id);
        return new DefaultHttpHeaders("edit");
    }

    public HttpHeaders editNew() {
        System.out.println("FarmPostController.editNew");
        if (!haveRight()) {
            return index();
        }
        super.editNew();
        String farmId = ServletActionContext.getRequest().getParameter("farmId");
        if (farmId != null || !"".equals(farmId))
            farm = FarmService.getById(farmId);
        if (farm == null)
            farm = getCurrentFarm();
        postTypes = PostTypeService.getObjectTypes("farmpost");
        ServletActionContext.getRequest().setAttribute("newTag", 1);
        return new DefaultHttpHeaders("editNew");
    }

    public HttpHeaders create() {
        if (!haveRight()) {
            return index();
        }
        farm = FarmService.getById(farm.getFarmId());
        if (farm == null) {
            addActionError("没有选定农庄!");
            return index();
        }
        site = farm.getSite();
        ServletActionContext.getRequest().setAttribute(Constants.FARM, farm.getFarmId());
        try {
            creator = getProfile();

            String c = post.getContent();
            String s = c.replaceAll(regEx_subject, "");
            String subject = s.substring(0, s.length() > 200 ? 200 : s.length());
            String content = c.replaceAll(regEx_content, "");

            post.setSubject(subject);
            post.setContent(content);
            post.setSite(site);
            category = CategoryService.getCategory(CATE_ID);
            post.setCategory(category);
            post.setCreator(creator);
            post.setParent(parent);

            PostType type = PostTypeService.getById(postType.getTypeId());

            FarmPostService service = new FarmPostService();
            FarmPost msg = new FarmPost(post, farm, type);
            service.create(msg);

            //创建信息记录之前,图片已经保存进数据库里了,但还没有postId;
            //所以在创建记录之后,必须更新图片记录.
            //在上传图片之后,每张图片的picId都保存在session中;
            //更新图片之后,要将session中的picId清空.
            updatePicture();

            addActionMessage("创建成功!");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
            addActionError(e.toString());
        }
        return index();
    }

    public void quickpost() {
        if (!haveRight()) {
            return;
        }
        farm = FarmService.getById(farm.getFarmId());
        site = farm.getSite();
        try {
            creator = getProfile();

            String c = post.getContent();
            String s = c.replaceAll(regEx_subject, "");
            String subject = s.substring(0, s.length() > 200 ? 200 : s.length());
            String content = c.replaceAll(regEx_content, "");

            post.setSubject(subject);
            post.setContent(content);
            post.setSite(site);
            category = CategoryService.getCategory(CATE_ID);
            post.setCategory(category);
            post.setCreator(creator);
            post.setParent(parent);

            PostType type = PostTypeService.getById(postType.getTypeId());

            FarmPostService service = new FarmPostService();
            FarmPost msg = new FarmPost(post, farm, type);
            service.create(msg);

            addActionMessage("创建成功!");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
            addActionError(e.toString());
        }

        try {
            String host = ServletActionContext.getRequest().getServerName();
            String port = ServletActionContext.getRequest().getServerPort() + "";
            String ctx = ServletActionContext.getRequest().getContextPath();
            String path = "http://" + host + ":" + port + ctx + "/work/work/" + farm.getFarmId();

            ServletActionContext.getResponse().sendRedirect(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Farm getCurrentFarm() {
        Farm f = null;
        String fid = ServletActionContext.getRequest().getParameter("farmId");
        if (fid == null || "".equals(fid)) {
            fid = (String) ServletActionContext.getRequest().getAttribute("farmId");
        }
        if (fid == null || "".equals(fid)) {
            fid = (String) ServletActionContext.getRequest().getSession().getAttribute("farmId");
        }
        if (fid != null) {
            f = FarmService.getById(fid);
        }

        if (f == null) {
            List list = FarmService.getFarmByMember(getProfile());
            if (list.size() > 0) f = (Farm) list.get(0);
        }
        return f;
    }

    public boolean haveRight() {
        return true;
    }

    private void getFarmPosts() {
        String typeId = getParameter("typeId");
        if (typeId != null && !"".equals(typeId))
            postType = PostTypeService.getById(typeId);
        else
            postType = null;

        String farmId = getParameter("farmId");
        if (farmId != null && !"".equals(farmId))
            farm = FarmService.getById(farmId);
        else farm = null;

        posts = FarmPostService.getFarmPosts(farm, page, typeId);

    }

}
