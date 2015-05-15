package com.sitexa.user.rest.user;

import com.sitexa.framework.AppContext;
import com.sitexa.framework.Constants;
import com.sitexa.service.RightService;
import com.sitexa.user.action.MemberAction;
import com.sitexa.user.data.Friend;
import com.sitexa.user.data.Member;
import com.sitexa.user.service.FriendService;
import com.sitexa.user.service.MemberService;
import com.sitexa.user.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;
import java.util.Date;

/**
 * User: xnpeng
 * Date: 2009-4-4
 * Time: 22:09:27
 */
public class MemberController extends MemberAction {

    private static Log log = LogFactory.getLog(MemberController.class);

    public void prepare() {
        System.out.println("MemberController.prepare");
        super.prepare();
        memberTypeList = MemberService.getMemberTypeList();
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders index() {
        System.out.println("MemberController.index");
        member = getProfile();
        if (member != null) {
            user = member.getUser();
            properties.addAll(member.getProperties());
            pictures.addAll(member.getPictures());
            return new DefaultHttpHeaders("index").disableCaching();
        } else if (getMe() != null) {
            return editNew();
        } else {
            try {
                ServletActionContext.getResponse()
                        .sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
            } catch (IOException ignored) {
            }
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders show() {
        member = MemberService.getMember(id);
        if (member != null) {
            user = member.getUser();
            properties.addAll(member.getProperties());
            pictures.addAll(member.getPictures());
            if ((getProfile() != null) && !member.getMemberId().equals(getProfile().getMemberId())) {
                MemberService.visit(member, getProfile());
            }
            return new DefaultHttpHeaders("show").disableCaching();
        } else {
            return index();
        }
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders edit() {
        System.out.println("MemberController.edit");
        if (!haveRight()) return show();
        member = MemberService.getMember(id);
        if (member != null) {
            user = member.getUser();
            properties.addAll(member.getProperties());
            pictures.addAll(member.getPictures());
            return new DefaultHttpHeaders("edit").disableCaching();
        } else {
            return show();
        }
    }

    public HttpHeaders editNew() {
        System.out.println("MemberController.editNew");
        user = getMe();
        if (user != null) {
            member = getProfile();
            if (member != null) return show();
        } else {
            try {
                ServletActionContext.getResponse()
                        .sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
            } catch (IOException ignored) {
            }
        }
        return new DefaultHttpHeaders("editNew");
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders create() {
        System.out.println("MemberController.create");
        user = UserService.getUser(id);
        if (user == null) {
            addActionError("用户不存在.");
            return index();
        } else {
            if (!user.getUserId().equals(getMe().getUserId())) return index();
            try {
                member.setUser(user);
                member.setUpdateDate(new Date());
                if (type != null && type.getTypeId() != null) {
                    type = MemberService.getMemberType(type.getTypeId());
                    member.setType(type);
                }
                if (member.getSite().getSiteId() == null || "".equals(member.getSite().getSiteId())) {
                    member.setSite(null);
                }
                MemberService.create(member);
                //todo...完成内部登录过程.即,如果有了社区,就要在ac中放入siteId.
                if (member.getSite() != null) {
                    AppContext ac = new AppContext(member.getMemberId(), member.getSite().getSiteId());
                    getSession().put(Constants.APP_CONTEXT, ac);
                }
            } catch (Exception e) {
                addActionError("创建成员出错!原因:" + e.getMessage());
                log.error(e);
                return editNew();
            }
        }
        return edit();
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders update() {
        System.out.println("MemberController.update");
        if (!haveRight()) return show();
        if (member != null) {
            try {
                MemberService.update(member);
                //todo...如果变更了社区,就要在ac中放入新的siteId.
                if (member.getSite() != null) {
                    if (member.getMemberId().equals(getProfile().getMemberId())) {
                        AppContext ac = new AppContext(member.getMemberId(), member.getSite().getSiteId());
                        getSession().put(Constants.APP_CONTEXT, ac);
                    }
                }
                addActionMessage("修改成功");
            } catch (Exception e) {
                addActionError("修改会员出错!原因:" + e.getMessage());
                log.error(e);
            }
        } else {
            return show();
        }
        return edit();
    }

    public HttpHeaders destroy() {
        System.out.println("MemberController.destroy");
        //todo...check admin role
        return index();
    }

    /**
     * 将正在浏览会员(fellow(id))加为我(profile)的好友.
     * 参考FriendController,profile(id)不是fellow.
     *
     * @return HttpHeaders
     */
    public HttpHeaders applyFriend() {
        System.out.println("MemberController.applyFriend");
        if (getProfile() == null) return show();
        Member fellow = MemberService.getMember(id);
        if (fellow != null) {
            Member profile = getProfile();

            String remark = ServletActionContext.getRequest().getParameter("remark");
            try {
                Friend friend = new Friend();
                friend.setMember(profile);
                friend.setFellow(fellow);
                friend.setRemark(remark);
                friend.setStatus(false);
                FriendService.create(friend);
                addActionMessage("提交成功!");
            } catch (Exception e) {
                log.error(e);
                addActionError(e.getMessage());
            }
        }
        return show();
    }

    protected boolean haveRight() {
        Member profile = getProfile();
        Member member = MemberService.getMember(id);
        return (profile != null && member != null && profile.getMemberId().equals(member.getMemberId()))
                || (profile != null && member != null && RightService.checkRight(profile, member.getSite()));
    }

    public HttpHeaders search() {
        System.out.println("MemberController.search");
        String keyWord = getParameter("keyWord");
        members = MemberService.search(keyWord, page);
        member = getProfile();
        site = getHome();
        ServletActionContext.getRequest().setAttribute("keyWord", keyWord);
        return new DefaultHttpHeaders("newMember").disableCaching();
    }

    //2010-5-6 leim
    public HttpHeaders applyLanderPage() {
        if (!haveRight()) return index();
        member = getProfile();
        String status = member.getStatus();
        String typeId = member.getType().getTypeId();
        if ((status != null && status.endsWith("x")) || (typeId != null && typeId.equals("6"))) {
            ServletActionContext.getRequest().setAttribute("tipType", 1);
            return new DefaultHttpHeaders("tips").disableCaching();
        } else {
            return new DefaultHttpHeaders("applyLanderPage").disableCaching();
        }
    }

    public HttpHeaders applyLander() {
        if (!haveRight()) return index();
        member = getProfile();
        MemberService.applyLander(member);
        ServletActionContext.getRequest().setAttribute("tipType", 2);
        return new DefaultHttpHeaders("tips").disableCaching();
    }
}
