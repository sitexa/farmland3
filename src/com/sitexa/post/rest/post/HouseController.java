package com.sitexa.post.rest.post;

import com.sitexa.post.data.Category;
import com.sitexa.post.data.House;
import com.sitexa.post.data.Post;
import com.sitexa.post.service.CategoryService;
import com.sitexa.post.service.HouseService;
import com.sitexa.post.service.PostService;
import com.sitexa.framework.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * User: xnpeng
 * Date: 2009-6-20
 * Time: 10:19:33
 */
public class HouseController extends PostController {
    private static Log log = LogFactory.getLog(HouseController.class);
    private House house;

    public void prepare() {
        super.prepare();
        category = CategoryService.getCategory(Category.HOUSE);
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders show() {
        System.out.println("HouseController.show");
        house = HouseService.getHouse(id);
        if (house != null) {
            post = house.getPost();
            pictures.addAll(post.getPictures());
            site = post.getSite();
            getSession().put(Constants.SITE_KEY, site.getSiteId());
            if (!isOwner()) PostService.incVwCnt(post);
            return new DefaultHttpHeaders("show").disableCaching();
        } else
            return index();
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders edit() {
        System.out.println("HouseController.edit");
        if (!haveRight() && !isSiteGovernor()) return show();
        house = HouseService.getHouse(id);
        if (house != null) {
            post = house.getPost();
            pictures.addAll(post.getPictures());
            return new DefaultHttpHeaders("edit").disableCaching();
        } else
            return show();
    }

    public HttpHeaders editNew() {
        System.out.println("HouseController.editNew");
        if (!isSiteMember() && !isSiteGovernor()) {
            addActionError("您不是该社区会员,或者没开通发布权.");
            return index();
        }
        return new DefaultHttpHeaders("editNew");
    }

    public HttpHeaders create() {
        System.out.println("HouseController.create");
        if (!isSiteMember() && !isSiteGovernor()) {
            addActionError("您不是该社区会员,或者没开通发布权.");
            return index();
        }
        try {
            creator = getProfile();

            String s = post.getContent().replaceAll(regEx_subject, "").replaceAll(regEx_html, "").replaceAll(regEx_space, "");
            String subject = s.substring(0, s.length() > 200 ? 200 : s.length());
            String content = post.getContent().replaceAll(regEx_subject, "").replaceAll(regEx_space, "").replaceAll(regEx_v32, "");

            post.setSubject(subject);
            post.setContent(content);
            post.setSite(site);
            post.setCategory(category);
            post.setCreator(creator);
            post.setParent(parent);

            house.setPost(post);

            HouseService service = new HouseService();
            service.create(house);

            //创建信息记录之前,图片已经保存进数据库里了,但还没有postId;
            //所以在创建记录之后,必须更新图片记录.
            //在上传图片之后,每张图片的picId都保存在session中;
            //更新图片之后,要将session中的picId清空.
            updatePicture();
            addActionMessage("创建成功!");
        } catch (RuntimeException re) {
            log.error(re);
            addActionError(re.getMessage());
            return editNew();
        }
        return new DefaultHttpHeaders("show").setLocationId(post);
    }

    public HttpHeaders update() {
        System.out.println("HouseController.update");
        if (!haveRight() && !isSiteGovernor()) {
            addActionError("无权执行该操作!");
            return show();
        }
        House house2 = HouseService.getHouse(id);
        if (house2 != null) {
            try {
                Post post1 = house2.getPost();

                String s = post.getContent().replaceAll(regEx_subject, "").replaceAll(regEx_html, "").replaceAll(regEx_space, "");
                String subject = s.substring(0, s.length() > 200 ? 200 : s.length());
                String content = post.getContent().replaceAll(regEx_subject, "").replaceAll(regEx_space, "").replaceAll(regEx_v32, "");

                post1.setName(post.getName());
                post1.setSubject(subject);
                post1.setContent(content);

                house2.setPost(post1);

                house2.setBuilding(house.getBuilding());
                house2.setRoom(house.getRoom());
                house2.setArea(house.getArea());
                house2.setPrice(house.getPrice());
                house2.setPost(post1);

                HouseService.update(house2);

                //创建信息记录之前,图片已经保存进数据库里了,但还没有postId;
                //所以在创建记录之后,必须更新图片记录.
                //在上传图片之后,每张图片的picId都保存在session中;
                //更新图片之后,要将session中的picId清空.
                updatePicture();
                addActionMessage("更新成功!");
            } catch (RuntimeException re) {
                log.error(re);
                addActionError(re.getMessage());
                return edit();
            }
        }
        return show();
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}
