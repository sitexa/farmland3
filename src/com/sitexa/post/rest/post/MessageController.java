package com.sitexa.post.rest.post;

import com.sitexa.post.data.Category;
import com.sitexa.post.data.Message;
import com.sitexa.post.data.PostType;
import com.sitexa.post.service.CategoryService;
import com.sitexa.post.service.MessageService;
import com.sitexa.post.service.PostTypeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-4-13
 * Time: 19:20:05
 */
public class MessageController extends PostController {

    private static Log log = LogFactory.getLog(MessageController.class);

    private Message message;
    private PostType msgType;

    public void prepare() {
        super.prepare();
        category = CategoryService.getCategory(Category.MESSAGE);
    }

    /**
     * 收集界面输入,形成transient对象,不从数据库中获取持久对象.
     *
     * @return page result type.
     */
    public HttpHeaders create() {
        System.out.println("MessageController.create");

        if (!isSiteMember() && !isSiteGovernor()) {
            addActionError("无权执行该操作!");
            return index();
        }
        try {
            creator = getProfile();

            if (category.getCateId() != null)
                category = CategoryService.getCategory(category.getCateId());
            else
                category = CategoryService.getCategory(Category.MESSAGE);

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

            MessageService service = new MessageService();
            PostType type = PostTypeService.getById(postType.getTypeId());
            Message msg = new Message(null, type, post);
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
        return new DefaultHttpHeaders("show").setLocationId(post);
    }

    public HttpHeaders update() {
        System.out.println("MessageController.update begin");
        if (!haveRight() && !isSiteGovernor()) return show();

        try {
            String sub = post.getContent().replaceAll(regEx_subject, "");
            String subject = sub.substring(0, sub.length() > 200 ? 200 : sub.length());
            String content = post.getContent().replaceAll(regEx_content, "");

            post.setSubject(subject);
            post.setContent(content);

            MessageService.update(post);

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

    public PostType getMsgType() {
        return msgType;
    }

    public void setMsgType(PostType msgType) {
        this.msgType = msgType;
    }

    public List<PostType> getMsgTypeList() {
        return PostTypeService.getObjectTypes("message");
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
