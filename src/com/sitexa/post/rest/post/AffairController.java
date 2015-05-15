package com.sitexa.post.rest.post;

import com.sitexa.framework.Constants;
import com.sitexa.post.data.Affair;
import com.sitexa.post.data.Category;
import com.sitexa.post.data.PostType;
import com.sitexa.post.service.AffairService;
import com.sitexa.post.service.CategoryService;
import com.sitexa.post.service.PostService;
import com.sitexa.post.service.PostTypeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * User: xnpeng
 * Date: 2009-4-30
 * Time: 15:07:38
 */
public class AffairController extends PostController {

    private static Log log = LogFactory.getLog(AffairController.class);
    private Affair affair;
    private PostType affairType;

    public void prepare() {
        super.prepare();
        category = CategoryService.getCategory(Category.AFFAIR);
    }

    public HttpHeaders index() {
        System.out.println("AffairController.index");
        super.index();
        return new DefaultHttpHeaders("index");
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders show() {
        System.out.println("AffairController.show");
        affair = AffairService.getAffair(id);
        if (affair != null) {
            post = affair.getPost();
            pictures.addAll(post.getPictures());
            site = post.getSite();
            getSession().put(Constants.SITE_KEY, site.getSiteId());
            if (!isOwner()) PostService.incVwCnt(post);
            return new DefaultHttpHeaders("show").disableCaching();
        } else {
            return index();
        }
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders edit() {
        System.out.println("AffairController.edit");
        if (!haveRight() && !isSiteGovernor()) return show();
        affair = AffairService.getAffair(id);
        if (affair != null) {
            post = affair.getPost();
            pictures.addAll(post.getPictures());
            return new DefaultHttpHeaders("edit");
        } else
            return show();
    }

    public HttpHeaders editNew() {
        System.out.println("AffairController.editNew");
        if (!isSiteMember() && !isSiteGovernor()) return show();
        return new DefaultHttpHeaders("editNew");
    }

    public HttpHeaders create() {
        System.out.println("AffairController.create");
        if (!isSiteMember() && !isSiteGovernor()) return index();
        try {
            creator = getProfile();

            String s = post.getContent().replaceAll(regEx_subject, "");
            String subject = s.substring(0, s.length() > 200 ? 200 : s.length());

            String content = post.getContent().replaceAll(regEx_content, "");

            post.setSubject(subject);
            post.setContent(content);
            post.setSite(site);
            post.setCategory(category);
            post.setCreator(creator);
            post.setParent(parent);

            AffairService service = new AffairService();
            PostType type = PostTypeService.getById(postType.getTypeId());
            Affair msg = new Affair(null, type, post);
            service.create(msg);

            //创建信息记录之前,图片已经保存进数据库里了,但还没有postId;
            //所以在创建记录之后,必须更新图片记录.
            //在上传图片之后,每张图片的picId都保存在session中;
            //更新图片之后,要将session中的picId清空.
            updatePicture();
            addActionMessage("创建成功!");
        } catch (Exception e) {
            log.error(e);
            addActionError(e.toString());
            return editNew();
        }
        return new DefaultHttpHeaders("show").setLocationId(post);
    }

    public HttpHeaders update() {
        System.out.println("AffairController.update");
        if (!haveRight() && !isSiteGovernor()) return show();
        try {
            String name = post.getName();
            String sub = post.getContent().replaceAll(regEx_subject, "");
            String subject = sub.substring(0, sub.length() > 200 ? 200 : sub.length());
            String content = post.getContent().replaceAll(regEx_content, "");

            post = PostService.getPost(post.getId());
            post.setSubject(subject);
            post.setContent(content);
            post.setName(name);

            AffairService.update(post);

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

    public Affair getAffair() {
        return affair;
    }

    public void setAffair(Affair affair) {
        this.affair = affair;
    }

    public PostType getAffairType() {
        return affairType;
    }

    public void setAffairType(PostType affairType) {
        this.affairType = affairType;
    }
}
