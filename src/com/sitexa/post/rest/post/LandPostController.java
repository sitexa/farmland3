package com.sitexa.post.rest.post;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.Land;
import com.sitexa.farm.service.FarmService;
import com.sitexa.farm.service.LandService;
import com.sitexa.framework.Constants;
import com.sitexa.post.data.Category;
import com.sitexa.post.data.LandPost;
import com.sitexa.post.data.PostPicture;
import com.sitexa.post.service.CategoryService;
import com.sitexa.post.service.LandPostService;
import com.sitexa.post.service.PostService;
import com.sitexa.post.service.PostTypeService;
import com.sitexa.user.data.Member;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-12-23
 * Time: 10:08:02
 */
public class LandPostController extends PostController {
    private static Log log = LogFactory.getLog(LandPostController.class);
    private final String CATE_ID = Category.LANDPOST;
    private int CNT = 5;

    private Land land;
    private List<Member> members = new ArrayList<Member>();
    private List<Farm> farms = new ArrayList<Farm>();

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Farm> getFarms() {
        return farms;
    }

    public void setFarms(List<Farm> farms) {
        this.farms = farms;
    }

    //////

    public HttpHeaders index() {
        System.out.println("LandPostController.index");
        String landId = ServletActionContext.getRequest().getParameter("landId");
        String typeId = ServletActionContext.getRequest().getParameter("typeId");
        posts = LandPostService.getLandPosts(land, page, typeId);
        getLandPosts();
        return new DefaultHttpHeaders("index");
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders show() {
        System.out.println("LandPostController.show");
        post = PostService.getPost(id);
        if (post != null) {
            pictures.addAll(post.getPictures());
            land = LandPostService.getById(post.getId()).getLand();
            members = LandService.getMembers(land);
            farms = FarmService.getJoinedFarms(land);
            ServletActionContext.getRequest().getSession().setAttribute(Constants.LAND, land.getLandId());
        }
        posts = LandPostService.getLandPosts(land, page, null);
        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders edit() {
        System.out.println("LandPostController.edit");
        if (!haveRight()) return index();
        post = PostService.getPost(id);
        return new DefaultHttpHeaders("edit");
    }

    public HttpHeaders editNew() {
        System.out.println("LandPostController.editNew");
        if (!haveRight()) return index();
        land = getCurrentLand();
        ServletActionContext.getRequest().setAttribute("newTag", 1);
        return new DefaultHttpHeaders("editNew");
    }

    public HttpHeaders create() {
        System.out.println("LandPostController.create");
        creator = getProfile();
        if (creator == null) {
            try {
                ServletActionContext.getResponse()
                        .sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
            } catch (IOException e) {
            }
        }
        if (!haveRight()) return index();
        category = CategoryService.getCategory(CATE_ID);
        land = LandService.getById(land.getLandId());
        if (land == null) {
            addActionError("没有选定农场!");
            postType = null;
            return index();
        }
        site = land.getSite();
        ServletActionContext.getRequest().getSession().setAttribute(Constants.LAND, land.getLandId());
        try {
            String c = post.getContent();
            String s = c.replaceAll(regEx_subject, "");
            String subject = s.substring(0, s.length() > 200 ? 200 : s.length());
            String content = c.replaceAll(regEx_content, "");

            post.setSubject(subject);
            post.setContent(content);
            post.setSite(site);
            post.setCategory(category);
            post.setCreator(creator);
            post.setParent(parent);

            postType = PostTypeService.getById(postType.getTypeId());

            LandPostService service = new LandPostService();
            LandPost msg = new LandPost(post, land, postType);
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
        System.out.println("LandPostController.quickpost");
        creator = getProfile();
        if (creator == null) {
            try {
                ServletActionContext.getResponse()
                        .sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
            } catch (IOException e) {

            }
        }
        if (!haveRight()) return;
        category = CategoryService.getCategory(CATE_ID);
        land = LandService.getById(land.getLandId());
        site = land.getSite();
        try {
            creator = getProfile();

            String c = post.getContent();
            String s = c.replaceAll(regEx_subject, "");
            String subject = s.substring(0, s.length() > 200 ? 200 : s.length());
            String content = c.replaceAll(regEx_content, "");

            post.setSubject(subject);
            post.setContent(content);
            post.setSite(site);
            post.setCategory(category);
            post.setCreator(creator);
            post.setParent(parent);

            postType = PostTypeService.getById(postType.getTypeId());

            LandPostService service = new LandPostService();
            LandPost msg = new LandPost(post, land, postType);
            service.create(msg);
            addActionMessage("创建成功!");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
            addActionError(e.toString());
        }

        String path = ServletActionContext.getRequest().getHeader("referer");   //可重写
        try {
            ServletActionContext.getResponse().sendRedirect(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HttpHeaders update() {
        System.out.println("LandPostController.update");
        if (!haveRight()) return index();
        try {
            String sub = post.getContent().replaceAll(regEx_subject, "");
            String subject = sub.substring(0, sub.length() > 200 ? 200 : sub.length());
            String content = post.getContent().replaceAll(regEx_content, "");

            post.setSubject(subject);
            post.setContent(content);

            LandPostService.update(post);

            updatePicture();
            addActionMessage("更新成功!");
        } catch (Exception e) {
            log.error(e);
            addActionError(e.toString());
            return edit();
        }
        return show();
    }

    public HttpHeaders destroy() {
        System.out.println("LandPostController.destroy");
        if (!haveRight()) return index();
        super.destroy();
        return index();
    }

    @SuppressWarnings("unchecked")
    protected void updatePicture() {
        List<String> pics = (List<String>) getSession().get(Constants.PICS);
        pictures = PostService.getPictures(pics);
        for (PostPicture picture : pictures) {
            picture.setPost(post);
        }
        PostService.updatePictures(pictures);
        getSession().remove(Constants.PICS);
    }

    private Land getCurrentLand() {
        Land land = null;
        String landId = ServletActionContext.getRequest().getParameter("landId");
        if (landId != null) {
            land = LandService.getById(landId);
        }
        if (land == null) {
            landId = (String) ServletActionContext.getRequest().getAttribute("landId");
        }
        if (landId != null) {
            land = LandService.getById(landId);
        }
        return land;
    }

    public boolean haveRight() {
        if (getProfile() == null) return false;
        return true;
    }

    public void prepare() {
        System.out.println("LandPostController.prepare");
        postTypes = PostTypeService.getObjectTypes("landpost");
        super.prepare();
        System.out.println("LandPostController.prepare end");
    }

    private void getLandPosts() {
        String typeId = null;
        if (postType != null) {
            typeId = postType.getTypeId();
            postType = PostTypeService.getById(typeId);
        }

        String landId = getParameter("landId");
        if (landId != null) land = LandService.getById(landId);

        posts = LandPostService.getLandPosts(land, page, typeId);

    }

}
