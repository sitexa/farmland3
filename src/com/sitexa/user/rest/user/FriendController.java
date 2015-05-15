package com.sitexa.user.rest.user;

import com.sitexa.user.action.FriendAction;
import com.sitexa.user.data.Friend;
import com.sitexa.user.data.Member;
import com.sitexa.user.service.FriendService;
import com.sitexa.user.service.MemberService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-5-6
 * Time: 20:32:06
 */
public class FriendController extends FriendAction {

    public void prepare() {
        super.prepare();
        site = getHome();
    }

    private boolean haveRight() {
        System.out.println("FriendController.haveRight");
        Member profile = getProfile();
        Member member = MemberService.getMember(id);
        return (profile != null && member != null && profile.getMemberId().equals(member.getMemberId()));
    }

    public HttpHeaders index() {
        return new DefaultHttpHeaders("index");
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders show() {
        System.out.println("FriendController.show");
        if (!haveRight()) return index();
        member = MemberService.getMember(id);
        if (member != null) {
            try {
                for (Object o : member.getFriends()) {
                    Friend friend1 = (Friend) o;
                    if (friend1.getStatus() != null && friend.getStatus()) friends.add(friend1);
                }
                site = member.getSite();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new DefaultHttpHeaders("show");
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders edit() {
        System.out.println("FriendController.edit");
        if (!haveRight()) return show();
        member = MemberService.getMember(id);
        if (member != null) {
            try {
                for (Object o : member.getFriends()) {
                    Friend friend1 = (Friend) o;
                    //status==false表示别人还没有答应.status==true表示别人答应了朋友关系.
                    if (friend1.getStatus() != null && friend1.getStatus()) {
                        friends.add(friend1);
                    }
                }
                //找到有哪些请求加为好朋友的列表.
                List list = FriendService.findApply(member);
                for (Object aList : list) {
                    Friend friend1 = (Friend) aList;
                    if (friend1.getStatus()!=null && !friend1.getStatus()) {
                    	fellows.add(friend1);
                    }
                }
                site = member.getSite();
                return new DefaultHttpHeaders("edit");
            } catch (Exception e) {
                e.printStackTrace();
                return show();
            }
        } else
            return show();
    }

    public HttpHeaders editNew() {
        return show();
    }

    /**
     * 参考MemberController中applyFriend.
     *
     * @return HttpHeaders
     */
    public HttpHeaders create() {
        //todo...big problem
        System.out.println("FriendController.create");
        Member profile = getProfile();
        if (profile != null) {
            friend.setMember(profile);
            Member fellow = MemberService.getMember(friend.getFellow().getMemberId());
            if (fellow != null)
                friend.setFellow(fellow);
            try {
                FriendService.create(friend);
                addActionMessage("邀请信已经发送给对方!");
            } catch (Exception e) {
                e.printStackTrace();
                addActionError(e.getMessage());
            }
        }
        friends.clear();
        return edit();
    }

    public HttpHeaders update() {
        System.out.println("FriendController.update");
        if (!haveRight()) return show();
        member = MemberService.getMember(id);
        if (member != null) {
            String fid = ServletActionContext.getRequest().getParameter("fid");
            String op = ServletActionContext.getRequest().getParameter("op");
            for (Friend friend1 : friends) {
                if (fid.equals(friend1.getId())) {
                    try {
                        if ("save".equalsIgnoreCase(op)) {
                            FriendService.update(friend1);
                        } else if ("delete".equalsIgnoreCase(op)) {
                            FriendService.delete(friend1);
                        }
                        addActionMessage("操作成功!");
                    } catch (Exception e) {
                        addActionError(e.getMessage());
                    }
                    break;
                }
            }
        }
        friends.clear();
        return edit();
    }

    public HttpHeaders update2() {
        System.out.println("FriendController.update2");
        if (!haveRight()) return show();
        member = MemberService.getMember(id);
        if (member != null) {
            String fid = ServletActionContext.getRequest().getParameter("fid2");
            String op = ServletActionContext.getRequest().getParameter("op2");
            for (Friend friend1 : fellows) {
                if (fid.equals(friend1.getId())) {
                    try {
                        if ("accept".equalsIgnoreCase(op)) {
                            FriendService.acceptApply(friend1);
                        } else if ("deny".equalsIgnoreCase(op)) {
                            FriendService.delete(friend1);
                        }
                        addActionMessage("操作成功!");
                    } catch (Exception e) {
                        addActionError(e.getMessage());
                    }
                    break;
                }
            }
        }
        fellows.clear();
        return edit();
    }

    public HttpHeaders destroy() {
        return null;
    }

    public HttpHeaders addGroup() {
        return null;
    }
}
