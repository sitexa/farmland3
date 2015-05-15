package com.sitexa.post.rest.post;

import com.sitexa.framework.Constants;
import com.sitexa.post.data.*;
import com.sitexa.post.service.ActivityService;
import com.sitexa.post.service.CategoryService;
import com.sitexa.post.service.PostService;
import com.sitexa.post.service.PostTypeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * User: xnpeng
 * Date: 2009-4-28
 * Time: 23:20:58
 */
public class ActivityController extends PostController {

    private static Log log = LogFactory.getLog(ActivityController.class);
    private Activity activity;
    private PostType activityType;
    private Participant participant;
    private List<Participant> participants = new ArrayList<Participant>();

    public void prepare() {
        super.prepare();
        category = CategoryService.getCategory(Category.ACTIVITY);
    }

    public HttpHeaders index() {
        System.out.println("ActivityController.index");
        return new DefaultHttpHeaders("index").disableCaching();
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders show() {
        System.out.println("ActivityController.show");
        activity = ActivityService.getActivity(id);
        if (activity != null) {
            post = activity.getPost();
            pictures.addAll(post.getPictures());
            participants.addAll(activity.getParticipants());
            site = post.getSite();
            getSession().put(Constants.SITE_KEY, site.getSiteId());
            if (!isOwner()) PostService.incVwCnt(post);
            return new DefaultHttpHeaders("show").disableCaching();
        } else
            return index();
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders edit() {
        System.out.println("ActivityController.edit");
        if (!haveRight() && !isSiteGovernor()) return show();
        activity = ActivityService.getActivity(id);
        if (activity != null) {
            post = activity.getPost();
            pictures.addAll(post.getPictures());
            participants.addAll(activity.getParticipants());
            return new DefaultHttpHeaders("edit");
        } else
            return show();
    }

    public HttpHeaders editNew() {
        System.out.println("ActivityController.editNew");
        if (!isSiteMember() && !isSiteGovernor()) {
            addActionError("您不是该社区会员,或者没开通发布权.");
            return index();
        }
        return new DefaultHttpHeaders("editNew");
    }

    public HttpHeaders create() {
        System.out.println("ActivityController.create");
        if (!isSiteMember() && !isSiteGovernor()) {
            addActionError("您不是该社区会员,或者没开通发布权.");
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

            PostType type = PostTypeService.getById(postType.getTypeId());
            activity.setType(type);
            activity.setPost(post);

            if (activity.getContact().equals(""))
                activity.setContact(getProfile().getRealname());

            ActivityService service = new ActivityService();
            service.create(activity);

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
        System.out.println("ActivityController.update");
        if (!haveRight() && !isSiteGovernor()) {
            addActionError("无权执行该操作!");
            return show();
        }
        Activity act = ActivityService.getActivity(id);
        if (act != null) {
            try {
                Post post1 = act.getPost();

                String s = post.getContent().replaceAll(regEx_subject, "");
                String subject = s.substring(0, s.length() > 200 ? 200 : s.length());
                String content = post.getContent().replaceAll(regEx_content, "");

                post1.setName(post.getName());
                post1.setSubject(subject);
                post1.setContent(content);

                act.setStartDate(activity.getStartDate());
                act.setEndDate(activity.getEndDate());
                act.setJoinEndDate(activity.getJoinEndDate());
                act.setContact(activity.getContact());
                act.setExpense(activity.getExpense());
                act.setAddress(activity.getAddress());

                act.setPost(post1);

                ActivityService.update(act);

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

    @SuppressWarnings("unchecked")
    public HttpHeaders register() {
        System.out.println("ActivityController.register");
        activity = ActivityService.getActivity(id);
        if (activity != null) {
            try {
                if (activity.getEndDate().after(new Date())) {
                    Set parties = activity.getParticipants();
                    boolean inside = false;
                    for (Object party1 : parties) {
                        Participant party = (Participant) party1;
                        if (party.getMember().getMemberId().equalsIgnoreCase(getProfile().getMemberId())) {
                            addActionMessage("你已经报名参加了.");
                            inside = true;
                            break;
                        }
                    }
                    if (!inside) {
                        participant.setActivity(activity);
                        participant.setMember(getProfile());
                        participant.setJoinDate(new Date());
                        ActivityService.addParticipant(participant);
                    }
                } else {

                    addActionError("活动已经结束.");
                }
            } catch (Exception e) {
                log.error(e);
                addActionError(e.getMessage());
            }
        }
        return show();
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public PostType getPostType() {
        return activityType;
    }

    public void setPostType(PostType activityType) {
        this.activityType = activityType;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }
}
