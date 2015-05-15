package com.sitexa.rest.admin.post;

import com.sitexa.framework.Constants;
import com.sitexa.framework.exception.BaseException;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.sys.service.RoleService;
import com.sitexa.post.action.PostAction;
import com.sitexa.post.data.Post;
import com.sitexa.post.data.PostPicture;
import com.sitexa.post.service.PostService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-5-3
 * Time: 23:36:14
 */
public class PostAdminController extends PostAction {

    private static Log log = LogFactory.getLog(PostAdminController.class);

    public void prepare() {
        super.prepare();
        page = new Page(10);
        if (!haveRight())
            try {
                addActionError("你没有权限访问该页面!");
                ServletActionContext.getResponse()
                        .sendRedirect(ServletActionContext.getRequest().getContextPath() + "error");
            } catch (IOException ignored) {

            }
    }

    protected boolean haveRight() {
        return RoleService.getRoleInUser(getMe(), RoleService.getRole("3")) != null;
    }

    public HttpHeaders index() {
        return search();
    }

    public HttpHeaders delete() {
        System.out.println("PostAdminController.delete");
        if (!haveRight()) return index();
        post = PostService.getPost(id);
        if (post != null)
            return new DefaultHttpHeaders("delete");
        else return index();
    }

    public HttpHeaders edit() {
        System.out.println("PostAdminController.edit");
        if (!haveRight()) return show();
        post = PostService.getPost(id);
        if (post != null)
            return new DefaultHttpHeaders("edit");
        else
            return show();
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders show() {
        System.out.println("PostAdminController.show");
        pictures.clear();
        post = PostService.getPost(id);
        if (post != null) {
            site = post.getSite();
            pictures.addAll(post.getPictures());
            return new DefaultHttpHeaders("show").disableCaching();
        } else
            return index();
    }

    public HttpHeaders update() {
        System.out.println("PostAdminController.update");
        if (!haveRight()) {
            addActionError("无权执行该操作");
            return show();
        }
        try {
            String sub = post.getContent().replaceAll(regEx_subject, "").replaceAll(regEx_html, "").replaceAll(regEx_space, "");
            String subject = sub.substring(0, sub.length() > 200 ? 200 : sub.length());
            String content = post.getContent().replaceAll(regEx_subject, "").replaceAll(regEx_space, "");

            Post po = PostService.getPost(id);
            po.setName(post.getName());
            po.setSubject(subject);
            po.setContent(content);

            PostService.update(po);

            //编辑文章里,如果上传了图片,也要一并更新进数据库里.
            updatePicture();

            addActionMessage("更新成功!");
        } catch (Exception e) {
            log.error(e);
            addActionError(e.getMessage());
            return edit();
        }
        return index();
    }

    public HttpHeaders destroy() {
        System.out.println("PostAdminController.destroy");
        if (!haveRight()) {
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

    public HttpHeaders search() {
        System.out.println("PostAdminController.search");
        posts = PostService.search(words, page);
        return new DefaultHttpHeaders("index");
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
        System.out.println("PostAdminController.updatePicture");
        List<String> pics = (List<String>) getSession().get(Constants.PICS);
        pictures = PostService.getPictures(pics);
        for (PostPicture picture : pictures) {
            picture.setPost(post);
        }
        PostService.updatePictures(pictures);
        getSession().remove(Constants.PICS);
    }

}
