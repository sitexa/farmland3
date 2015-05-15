package com.sitexa.post.rest.post;

import com.sitexa.post.data.Business;
import com.sitexa.post.data.Category;
import com.sitexa.post.data.PostType;
import com.sitexa.post.service.BusinessService;
import com.sitexa.post.service.CategoryService;
import com.sitexa.post.service.PostService;
import com.sitexa.framework.Constants;
import com.sitexa.post.service.PostTypeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * User: xnpeng
 * Date: 2009-4-30
 * Time: 16:57:53
 */
public class BusinessController extends PostController {

    private static Log log = LogFactory.getLog(BusinessController.class);
    private Business business;
    private PostType businessType;

    public void prepare() {
        super.prepare();
        category = CategoryService.getCategory(Category.BUSINESS);
    }

    public HttpHeaders index() {
        super.index();
        return new DefaultHttpHeaders("index");
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders show() {
        System.out.println("BusinessController.show");
        business = BusinessService.getBusiness(id);
        if (business != null) {
            post = business.getPost();
            pictures.addAll(post.getPictures());
            site = post.getSite();
            getSession().put(Constants.SITE_KEY, site.getSiteId());
            if (!isOwner()) PostService.incVwCnt(post);
            return new DefaultHttpHeaders("show").disableCaching();
        } else
            return index();
    }

    public HttpHeaders edit() {
        System.out.println("BusinessController.edit");
        if (!haveRight() && !isSiteGovernor()) return show();
        business = BusinessService.getBusiness(id);
        if (business != null) {
            post = PostService.getPost(id);
            return new DefaultHttpHeaders("edit");
        } else
            return show();
    }

    public HttpHeaders editNew() {
        if (!isSiteMember() && !isSiteGovernor()) {
            addActionError("无权执行该操作!");
            return index();
        }
        return new DefaultHttpHeaders("editNew");
    }

    public HttpHeaders create() {
        System.out.println("BusinessController.create");
        if (!isSiteMember() && !isSiteGovernor()) {
            addActionError("无权执行该操作!");
            return index();
        }
        try {
            creator = getProfile();
            if (creator == null) {
                addActionError("请填写成员资料后再发布信息.");
                return new DefaultHttpHeaders("error");
            }
            if (site == null) {
                addActionError("请选择社区后再发布信息.");
                return new DefaultHttpHeaders("error");
            }

            String s = post.getContent().replaceAll(regEx_subject, "");
            String subject = s.substring(0, s.length() > 200 ? 200 : s.length());
            String content = post.getContent().replaceAll(regEx_content, "");

            post.setSubject(subject);
            post.setContent(content);
            post.setSite(site);
            post.setCategory(category);
            post.setCreator(creator);
            post.setParent(null);

            BusinessService service = new BusinessService();
            PostType type = PostTypeService.getById(postType.getTypeId());
            Business msg = new Business(null, type, post);
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
        System.out.println("BusinessController.update");
        if (!haveRight() && !isSiteGovernor()) return show();
        try {
            String sub = post.getContent().replaceAll(regEx_subject, "");
            String subject = sub.substring(0, sub.length() > 200 ? 200 : sub.length());
            String content = post.getContent().replaceAll(regEx_content, "");
            String name = post.getName();

            post = PostService.getPost(post.getId());

            post.setName(name);
            post.setContent(content);
            post.setSubject(subject);

            BusinessService.update(post);

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

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public PostType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(PostType businessType) {
        this.businessType = businessType;
    }
}
