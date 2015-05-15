package com.sitexa.farm.rest.play;

import com.sitexa.farm.data.Land;
import com.sitexa.farm.service.FarmService;
import com.sitexa.framework.Constants;
import com.sitexa.post.data.Csa;
import com.sitexa.post.data.Post;
import com.sitexa.post.data.PostPicture;
import com.sitexa.post.service.CategoryService;
import com.sitexa.post.service.CsaService;
import com.sitexa.post.service.PostService;
import com.sitexa.post.service.PostTypeService;
import com.sitexa.site.data.Site;
import com.sitexa.user.data.Member;
import com.sitexa.user.service.MemberService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-8
 * Time: 14:50:40
 */
public class PlayController extends PlayAction {
    private int cnt = 5;

    private void doCalc() {
        site = getDefaultSite();
//        land = getDefaultLand();
        hotPosts = CsaService.getHotPosts(site, cnt);
        hotPictures = CsaService.getNewPostPictures(site, cnt);
        newFarms = FarmService.getNewFarms(cnt);
        newMembers = MemberService.getNewMembers(cnt);
        postTypes = PostTypeService.getObjectTypes("csa");
    }



    public HttpHeaders index() {
        System.out.println("PlayController.index");
        if (postType != null && !"".equals(postType.getTypeId())) {
            postType = PostTypeService.getById(postType.getTypeId());
        }
        posts = CsaService.search(page, words, postType);

        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders show() {
        System.out.println("PlayController.show");
        post = PostService.getPost(id);
        if (postType != null && !"".equals(postType.getTypeId())) {
            postType = PostTypeService.getById(postType.getTypeId());
        }
        if (post != null) {
            pictures.clear();
            pictures.addAll(post.getPictures());
        }
        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders edit() {
        System.out.println("PlayController.edit");
        try {
            creator = getProfile();
            if (!haveRight()) return show();
            post = PostService.getPost(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DefaultHttpHeaders("edit");
    }

    public HttpHeaders editNew() {
        creator = getProfile();
        if (creator == null) {
            try {
                ServletActionContext.getResponse()
                        .sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
            } catch (IOException ignored) {
            }
        }
        postTypes = PostTypeService.getObjectTypes("csa");
        return new DefaultHttpHeaders("editNew");
    }

    public HttpHeaders create() {
        creator = getProfile();
        if (creator == null) {
            try {
                ServletActionContext.getResponse()
                        .sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
            } catch (IOException ignored) {
            }
        }

        site = getDefaultSite();
        category = CategoryService.getCategory("csa");

        try {
            String c = post.getContent();
            String s = c.replaceAll(regEx_subject, "");
            String subject = s.substring(0, s.length() > 200 ? 200 : s.length());
            String content = c.replaceAll(regEx_content, "");

            if (post.getName() == null || "".equals(post.getName()))
                post.setName("no name");
            post.setSubject(subject);
            post.setContent(content);
            post.setSite(site);
            post.setCategory(category);
            post.setCreator(creator);
            post.setParent(parent);

            postType = PostTypeService.getById(postType.getTypeId());

            CsaService service = new CsaService();
            Csa msg = new Csa(null, postType, post);
            service.create(msg);

            //创建信息记录之前,图片已经保存进数据库里了,但还没有postId;
            //所以在创建记录之后,必须更新图片记录.
            //在上传图片之后,每张图片的picId都保存在session中;
            //更新图片之后,要将session中的picId清空.
            updatePicture();

            addActionMessage("创建成功!");
        } catch (Exception e) {
            addActionError(e.toString());
            return editNew();
        }
        return index();
    }

    public HttpHeaders update() {
        System.out.println("PlayController.update");
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
            addActionError(e.toString());
            return edit();
        }
        return show();
    }

    public HttpHeaders destroy() {
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
            } catch (Exception e) {
                addActionError("删除失败!");
                return show();
            }
        }
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

    public HttpHeaders comment() {
        parent = PostService.getPost(id);
        try {
            creator = getProfile();
            if (creator == null) {
                throw new Exception("请登录后再发表评论.");
            }

            if (parent != null)
                category = parent.getCategory();

            String content = comment.replaceAll(regEx_subject, "");
            String subject = content.substring(0, content.length() > 200 ? 200 : content.length());

            Post cmnt = new Post();

            cmnt.setName("评:" + parent.getName());
            cmnt.setSubject(subject);
            cmnt.setContent(content);
            cmnt.setSite(parent.getSite());
            cmnt.setCategory(category);
            cmnt.setCreator(creator);
            cmnt.setParent(parent);

            PostService.comment(cmnt);
            comment = null;
        } catch (Exception e) {
            addActionError(e.toString());
        }
        return show();
    }


    protected boolean haveRight() {
        haveRight = false;
        Member profile = getProfile();
        Post post = PostService.getPost(id);
        if (post != null && profile != null) {
            if (post.getSite().getGovernor() != null && post.getSite().getGovernor().getMemberId().equals(profile.getMemberId()))
                haveRight = true;
            if (post.getCreator().getMemberId().equals(profile.getMemberId())) haveRight = true;
            if (post.getParent() != null && post.getParent().getCreator().getMemberId().equals(profile.getMemberId()))
                haveRight = true;
        }
        return haveRight;
    }
}
