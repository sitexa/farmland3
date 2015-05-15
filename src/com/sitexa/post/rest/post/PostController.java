package com.sitexa.post.rest.post;

import com.sitexa.framework.Constants;
import com.sitexa.framework.exception.BaseException;
import com.sitexa.post.MsgType;
import com.sitexa.post.PostCategory;
import com.sitexa.post.action.PostAction;
import com.sitexa.post.data.Category;
import com.sitexa.post.data.Post;
import com.sitexa.post.data.PostPicture;
import com.sitexa.post.service.CategoryService;
import com.sitexa.post.service.PostManager;
import com.sitexa.post.service.PostService;
import com.sitexa.post.service.PostTypeService;
import com.sitexa.service.RightService;
import com.sitexa.site.data.Site;
import com.sitexa.site.data.SiteMember;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-4-8
 * Time: 9:13:12
 */
public class PostController extends PostAction {
    private static Log log = LogFactory.getLog(PostController.class);

    @SuppressWarnings("unchecked")
    public HttpHeaders index() {
        System.out.println("PostController.index");
        site = getCurrentSite();
        category = getCurrentCategory();
        posts = PostService.search(page, words, site, category);

        if (category == null)
            category = CategoryService.getCategory(Category.MESSAGE);

        return new DefaultHttpHeaders("index").disableCaching();
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders show() {
        System.out.println("PostController.show");
        pictures.clear();
        post = PostService.getPost(id);
        if (post != null) {
            site = post.getSite();
            //2009.6.27,当前社区取帖子所属社区。
            getSession().put(Constants.SITE_KEY, site.getSiteId());
            pictures.addAll(post.getPictures());
            if (!isOwner()) PostService.incVwCnt(post);
            return new DefaultHttpHeaders("show").disableCaching();
        } else
            return index();
    }

    public HttpHeaders edit() {
        System.out.println("PostController.edit");
        if (!haveRight() && !isSiteGovernor()) return show();
        post = PostService.getPost(id);
        if (post != null)
            return new DefaultHttpHeaders("edit").disableCaching();
        else
            return show();

    }

    public HttpHeaders editNew() {
        System.out.println("PostController.editNew");
        if (getProfile() == null) return index();
        String typeId = ServletActionContext.getRequest().getParameter("typeId");

        site = getCurrentSite();
        category = getCurrentCategory();
        postType = PostTypeService.getById(typeId);

        if (category == null) category = CategoryService.getCategory(Category.MESSAGE);
        if (postType == null) postType = PostTypeService.getObjectType(
                PostCategory.MESSAGE.value(), MsgType.TYPE1.value());

        return new DefaultHttpHeaders("editNew");
    }

    public HttpHeaders create() {
        System.out.println("PostController.create");
        if (!isSiteMember() && !isSiteGovernor()) {
            addActionError("无权执行该操作");
            return index();
        }
        try {
            creator = getProfile();

            String content = post.getContent().replaceAll(regEx_subject, "");
            String subject = content.substring(0, content.length() > 200 ? 200 : content.length());

            post.setSubject(subject);
            post.setSite(site);
            post.setCategory(category);
            post.setCreator(creator);
            post.setContent(content);
            post.setParent(parent);

            postType = PostTypeService.getById(postType.getTypeId());

            PostManager.createPost(post, postType);
            addActionMessage("发布成功!");
        } catch (Exception e) {
            log.error(e);
            addActionError(e.toString());
            return editNew();
        }
        id = post.getId();
        return show();
    }

    public HttpHeaders update() {
        System.out.println("PostController.update");
        if (!haveRight() && !isSiteGovernor()) {
            addActionError("无权执行该操作");
            return show();
        }
        try {
            String sub = post.getContent().replaceAll(regEx_subject, "");
            String subject = sub.substring(0, sub.length() > 200 ? 200 : sub.length());
            String content = post.getContent().replaceAll(regEx_content, "");

            post.setSubject(subject);
            post.setContent(content);

            PostService.update(post);

            //编辑文章里,如果上传了图片,也要一并更新进数据库里.
            updatePicture();

            addActionMessage("更新成功!");
        } catch (Exception e) {
            log.error(e);
            addActionError(e.getMessage());
            return edit();
        }
        return show();
    }

    public HttpHeaders destroy() {
        System.out.println("PostController.destroy");
        if (!haveRight() && !isSiteGovernor()) {
            addActionError("无权执行操作!");
            return show();
        }

        post = PostService.getPost(id);
        if (post != null) {
            if (post.getParent() != null)
                id = post.getParent().getId();
            try {
                PostService.delete(post);
                addActionMessage("删除成功!");
            } catch (BaseException e) {
                log.error(e);
                addActionError("删除失败!");
                return show();
            }
        }
        return show();
    }

    public HttpHeaders comment() {
        System.out.println("PostController.comment");
        parent = PostService.getPost(id);
        try {
            creator = getProfile();
            if (creator == null) {
            	addActionError("请登录后再发表评论.");
                ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
                return null;
            }

            if (parent != null)
                category = parent.getCategory();

            String content = comment.replaceAll(regEx_content, "");
            String subject = content.replaceAll(regEx_subject, "");
            subject = subject.substring(0, subject.length() > 200 ? 200 : subject.length());

            Post cmnt = new Post();

            cmnt.setName("评:" + parent.getName());
            cmnt.setSubject(subject);
            cmnt.setContent(content);
            cmnt.setSite(parent.getSite());
            cmnt.setCategory(category);
            cmnt.setCreator(creator);
            cmnt.setParent(parent);
            System.out.println("cmnt = " + cmnt);

            PostService.comment(cmnt);
            comment = null;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
            addActionError(e.toString());
        }
        return show();
    }

    /**
     * 创建信息记录之前,图片已经保存进数据库里了,但还没有postId;
     * 所以在创建记录之后,必须更新图片记录.
     * 同时,将保存在文件系统里的图片移动到相应的目录里.
     * 在上传图片之后,每张图片的picId都保存在session中;
     * 更新图片之后,要将session中的picId清空.
     */
    @SuppressWarnings("unchecked")
    protected void updatePicture() {
        System.out.println("PostController.updatePicture");
        List<String> pics = (List<String>) getSession().get(Constants.PICS);
        pictures = PostService.getPictures(pics);
        for (PostPicture picture : pictures) {
            picture.setPost(post);
        }
        PostService.updatePictures(pictures);
        getSession().remove(Constants.PICS);
    }

    public void prepare() {
        System.out.println("PostController.prepare");
        super.prepare();
        String siteId = (String) getSession().get(Constants.SITE_KEY);
        if (siteId != null)
            site = SiteService.getSite(siteId);
        else
            site = getHome();
        String cateId = (String) getSession().get(Constants.CATEGORY_KEY);
        if (cateId != null)
            category = CategoryService.getCategory(cateId);
    }

    protected boolean haveRight() {
        Member profile = getProfile();
        Post post = PostService.getPost(id);
        return (profile != null && post != null
                && (profile.getMemberId().equals(post.getCreator().getMemberId())
                || (post.getParent() != null && post.getParent().getCreator().getMemberId().equals(profile.getMemberId()))));
    }

    protected boolean isOwner() {
        Member member = getProfile();
        return (post != null && member != null
                && member.getMemberId().equals(post.getCreator().getMemberId()));
    }

    protected boolean isSiteMember() {
        Member profile = getProfile();
        if (profile == null) return false;
        if (site == null) return false;
        for (SiteMember siteMember : site.getMembers()) {
            if (SiteMember.STATUS_ACT.equalsIgnoreCase(siteMember.getStatus())
                    && profile.getMemberId().equals(siteMember.getMember().getMemberId())) {
                return true;
            }
        }
        return false;
    }

    protected boolean isSiteGovernor() {
        Member profile = getProfile();
        if (profile == null) return false;
        if (site == null) return false;

        haveRight = RightService.checkRight(profile, site);
        return haveRight;
    }

    /**
     * 1，从request里取得siteId,2,从session中限得siteId,3,取得自己的siteId
     *
     * @return site;
     */
    protected Site getCurrentSite() {
        Site site1 = null;
        String siteId = ServletActionContext.getRequest().getParameter("siteId");
        if (siteId == null || "".equals(siteId)) {
            siteId = (String) getSession().get(Constants.SITE_KEY);
        }

        if (siteId != null && !"".equals(siteId)) {
            try {
                site1 = SiteService.getSite(siteId);
            } catch (Exception ignored) {
            }
        } else {
            site1 = getHome();
        }

        return site1;
    }

    /**
     * 1，从request里取得cateId,2,从session中限得cateId;
     *
     * @return Category;
     */
    protected Category getCurrentCategory() {
        Category cate1 = null;
        String cateId = ServletActionContext.getRequest().getParameter("cateId");
        if (cateId == null || "".equals(cateId)) {
            cateId = (String) getSession().get(Constants.CATEGORY_KEY);
        }

        if (cateId != null && !cateId.equals("")) {
            try {
                cate1 = CategoryService.getCategory(cateId);
            } catch (Exception ignored) {
            }
        }
        return cate1;
    }
}
