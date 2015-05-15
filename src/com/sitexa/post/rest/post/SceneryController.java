package com.sitexa.post.rest.post;

import com.sitexa.post.data.Category;
import com.sitexa.post.data.Evaluation;
import com.sitexa.post.data.Scenery;
import com.sitexa.post.service.CategoryService;
import com.sitexa.post.service.PostService;
import com.sitexa.post.service.SceneryService;
import com.sitexa.framework.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-4-30
 * Time: 20:35:05
 */
public class SceneryController extends PostController {

    private static Log log = LogFactory.getLog(SceneryController.class);
    private Scenery scenery;
    private Evaluation evaluation;
    private List<Evaluation> evaluations = new ArrayList<Evaluation>();

    public void prepare() {
        super.prepare();
        category = CategoryService.getCategory(Category.SCENERY);
    }

    public HttpHeaders index() {
        System.out.println("SceneryController.index");
        super.index();
        return new DefaultHttpHeaders("index");
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders show() {
        System.out.println("SceneryController.show");
        scenery = SceneryService.getScenery(id);
        if (scenery != null) {
            post = scenery.getPost();
            pictures.addAll(post.getPictures());
            evaluations.addAll(scenery.getEvaluations());
            site = post.getSite();
            getSession().put(Constants.SITE_KEY, site.getSiteId());
            if (!isOwner()) PostService.incVwCnt(post);
            return new DefaultHttpHeaders("show").disableCaching();
        } else
            return index();
    }

    public HttpHeaders edit() {
        System.out.println("SceneryController.edit");
        if (!haveRight() && !isSiteGovernor()) return show();
        scenery = SceneryService.getScenery(id);
        if (scenery != null) {
            scenery = SceneryService.getScenery(id);
            post = scenery.getPost();
            return new DefaultHttpHeaders("edit");
        } else {
            addActionError("文章不存在.");
            return index();
        }
    }

    public HttpHeaders editNew() {
        System.out.println("SceneryController.editNew");
        if (!isSiteMember() && !isSiteGovernor()) {
            addActionError("无权执行该操作!");
            return index();
        }
        return new DefaultHttpHeaders("editNew");
    }

    public HttpHeaders create() {
        System.out.println("SceneryController.create");
        if (!isSiteMember() && !isSiteGovernor()) {
            addActionError("无权执行该操作!");
            return index();
        }
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

            scenery.setPost(post);

            SceneryService service = new SceneryService();
            service.create(scenery);

            //创建信息记录之前,图片已经保存进数据库里了,但还没有postId;
            //所以在创建记录之后,必须更新图片记录.
            //在上传图片之后,每张图片的picId都保存在session中;
            //更新图片之后,要将session中的picId清空.
            updatePicture();

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
            addActionError(e.toString());
        }
        return new DefaultHttpHeaders("show").setLocationId(post);
    }

    public HttpHeaders update() {
        System.out.println("SceneryController.update");
        if (!haveRight() && !isSiteGovernor()) return show();
        try {
            String name = post.getName();
            String sub = post.getContent().replaceAll(regEx_subject, "");
            String subject = sub.substring(0, sub.length() > 200 ? 200 : sub.length());
            String content = post.getContent().replaceAll(regEx_content, "");
            String address = scenery.getAddress();
            String charge = scenery.getCharge();
            String level = scenery.getLevel();

            post = PostService.getPost(post.getId());
            post.setName(name);
            post.setSubject(subject);
            post.setContent(content);

            scenery = SceneryService.getScenery(post.getId());
            scenery.setAddress(address);
            scenery.setCharge(charge);
            scenery.setLevel(level);
            scenery.setPost(post);

            SceneryService.update(scenery);

            //编辑文章里,如果上传了图片,也要一并更新进数据库里.
            updatePicture();

        } catch (Exception e) {
            log.error(e);
            addActionError(e.toString());
        }
        return show();
    }

    public Scenery getScenery() {
        return scenery;
    }

    public void setScenery(Scenery scenery) {
        this.scenery = scenery;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }
}
