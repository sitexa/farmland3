package com.sitexa.post.rest.post;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.service.FarmService;
import com.sitexa.framework.Constants;
import com.sitexa.post.PostCategory;
import com.sitexa.post.data.*;
import com.sitexa.post.service.CategoryService;
import com.sitexa.post.service.CsaService;
import com.sitexa.post.service.PostService;
import com.sitexa.post.service.PostTypeService;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;
import com.sitexa.user.service.MemberService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-12-3
 * Time: 10:15:09
 */
public class CsaController extends PostController {

    private static Log log = LogFactory.getLog(CsaController.class);
    private final String CATE_ID = Category.CSA;
    private int cnt = 5;

    private Csa csa;
    private PostType csaType;
    private List<Post> hotPosts = new ArrayList<Post>();
    private List<PostPicture> hotPictures = new ArrayList<PostPicture>();
    private List<Member> newMembers = new ArrayList<Member>();
    private List<Farm> newFarms = new ArrayList<Farm>();

    private void doCalc() {
        try {
            hotPosts = CsaService.getHotPosts(site, cnt);
            hotPictures = CsaService.getNewPostPictures(site, cnt);
            newFarms = FarmService.getNewFarms(cnt);
            newMembers = MemberService.getNewMembers(cnt);
            postTypes = PostTypeService.getObjectTypes("csa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HttpHeaders index() {
        //String typeId = ServletActionContext.getRequest().getParameter("typeId");
        //csaType = PostTypeService.getById(typeId);
        site = getCurrentSite();
        category = CategoryService.getCategory(CATE_ID);
        posts = CsaService.search(page, words, csaType);
        doCalc();
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders show() {
        post = PostService.getPost(id);
        pictures.addAll(post.getPictures());
        site = getCurrentSite();
        doCalc();
        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders edit() {
        if (!haveRight()) return index();
        post = PostService.getPost(id);
        doCalc();
        return new DefaultHttpHeaders("edit");
    }

    public HttpHeaders editNew() {
        if (!haveRight()) return index();
        postTypes = PostTypeService.getObjectTypes(PostCategory.CSA.value());
        doCalc();
        ServletActionContext.getRequest().setAttribute("newTag", 1);
        return new DefaultHttpHeaders("editNew");
    }

    public HttpHeaders create() {
        if (!haveRight()) return index();
        site = getCurrentSite();
        category = CategoryService.getCategory(CATE_ID);

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

            CsaService service = new CsaService();
            PostType type = PostTypeService.getById(csaType.getTypeId());
            Csa msg = new Csa(null, type, post);
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
            return editNew();
        }
        return index();
    }

    public HttpHeaders update() {
        if (!haveRight()) return index();
        try {
            String sub = post.getContent().replaceAll(regEx_subject, "");
            String subject = sub.substring(0, sub.length() > 200 ? 200 : sub.length());
            String content = post.getContent().replaceAll(regEx_content, "");

            post.setSubject(subject);
            post.setContent(content);

            CsaService.update(post);

            //编辑文章里,如果上传了图片,也要一并更新进数据库里.
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
        if (!haveRight()) return index();
        super.destroy();
        return index();
    }

    @SuppressWarnings("unchecked")
    protected void updatePicture() {
        if (!haveRight()) return;
        List<String> pics = (List<String>) getSession().get(Constants.PICS);
        pictures = PostService.getPictures(pics);
        for (PostPicture picture : pictures) {
            picture.setPost(post);
        }
        PostService.updatePictures(pictures);
        getSession().remove(Constants.PICS);
    }

    protected boolean haveRight() {
        if (getProfile() == null) {
            addActionError("没有登录系统！");
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    protected Site getCurrentSite() {
        Site site = null;
        String siteId = ServletActionContext.getRequest().getParameter("siteId");

        if (siteId == null || "".equals(siteId)) {
            siteId = (String) getSession().get(Constants.SITE_KEY);
        }

        if (siteId != null && !"".equals(siteId)) {
            try {
                site = SiteService.getSite(siteId);
                getSession().put(Constants.SITE_KEY, siteId);
            } catch (Exception ignored) {
            }
        } else {
            site = getHome();
        }
        return site;
    }

    //////////////////////////////////////

    public Csa getCsa() {
        return csa;
    }

    public void setCsa(Csa csa) {
        this.csa = csa;
    }

    public List<Post> getHotPosts() {
        return hotPosts;
    }

    public void setHotPosts(List<Post> hotPosts) {
        this.hotPosts = hotPosts;
    }

    public List<PostPicture> getHotPictures() {
        return hotPictures;
    }

    public void setHotPictures(List<PostPicture> hotPictures) {
        this.hotPictures = hotPictures;
    }

    public List<Member> getNewMembers() {
        return newMembers;
    }

    public void setNewMembers(List<Member> newMembers) {
        this.newMembers = newMembers;
    }

    public List<Farm> getNewFarms() {
        return newFarms;
    }

    public void setNewFarms(List<Farm> newFarms) {
        this.newFarms = newFarms;
    }

    public PostType getCsaType() {
        return csaType;
    }

    public void setCsaType(PostType csaType) {
        this.csaType = csaType;
    }
}
