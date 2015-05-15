package com.sitexa.user.action;

import com.sitexa.framework.Constants;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Friend;
import com.sitexa.user.data.LeaveMessage;
import com.sitexa.user.data.Member;
import java.util.ArrayList;
import java.util.List;

/**
 * User: lei
 * Date: 2010-4-2
 * Time: 20:23:56
 */
public class LeaveMessageAction extends BaseAction{

    protected String id;
    protected LeaveMessage leaveMessage;
	protected List<LeaveMessage> leaveMessages = new ArrayList<LeaveMessage>();
	protected Member member;
	protected List<Friend> friends = new ArrayList<Friend>();
	protected Page page = new Page(10);
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
    public LeaveMessage getLeaveMessage() {
		return leaveMessage;
	}
	public void setLeaveMessage(LeaveMessage leaveMessage) {
		this.leaveMessage = leaveMessage;
	}
	
	public List<LeaveMessage> getLeaveMessages() {
		return leaveMessages;
	}
	public void setLeaveMessages(List<LeaveMessage> leaveMessages) {
		this.leaveMessages = leaveMessages;
	}
	
    public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	public List<Friend> getFriends() {
		return friends;
	}
	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
