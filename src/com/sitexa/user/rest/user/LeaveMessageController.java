package com.sitexa.user.rest.user;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.sitexa.user.action.LeaveMessageAction;
import com.sitexa.user.data.Friend;
import com.sitexa.user.data.Member;
import com.sitexa.user.service.LeaveMessageService;
import com.sitexa.user.service.MemberService;

import java.io.IOException;

/**
 * User: xnpeng
 * Date: 2009-4-2
 * Time: 20:29:05
 */
public class LeaveMessageController extends LeaveMessageAction {

    private static Log log = LogFactory.getLog(LeaveMessageController.class);

    public HttpHeaders index() {
    	return inBox();
    }
    
    public HttpHeaders inBox(){
    	System.out.println("LeaveMessageController.inBox");
        member = getProfile();
        int count = 0;
        try {
        	if (member == null) {
        		ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
        	}else{
                leaveMessages = LeaveMessageService.getleaveMessagesByPage(member, 0, page);
                count = LeaveMessageService.getleaveMessageCount(member, 0);
        	}
        } catch (IOException e) {
        	e.printStackTrace();
        }
        ServletActionContext.getRequest().setAttribute("count", count);
        ServletActionContext.getRequest().setAttribute("menu", 0);
    	return new DefaultHttpHeaders("inBox");
    }
    
    public HttpHeaders outBox(){
    	System.out.println("LeaveMessageController.outBox");
        member = getProfile();
        int count = 0;
        try {
        	if (member == null) {
        		ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
        	}else{
                leaveMessages = LeaveMessageService.getleaveMessagesByPage(member, 1, page);
                count = LeaveMessageService.getleaveMessageCount(member, 1);
        	}
        } catch (IOException e) {
        	e.printStackTrace();
        }
        ServletActionContext.getRequest().setAttribute("count", count);
        ServletActionContext.getRequest().setAttribute("menu", 1);
    	return new DefaultHttpHeaders("outBox");
    }
    
    public HttpHeaders send() {
    	System.out.println("LeaveMessageController.send");
    	member = getProfile();
        try {
        	if (member == null) {
        		ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
        	}else{
        		Friend friend1;
                for (Object o : member.getFriends()) {
                    friend1 = (Friend) o;
                    if (friend1.getStatus() != null && friend1.getStatus()) friends.add(friend1);
                }
                String sid = ServletActionContext.getRequest().getParameter("sid");
                if(sid!=null && !"".equals(sid)){
                	Member stranger = MemberService.getMember(sid);
                	if(stranger!=null){
                		ServletActionContext.getRequest().setAttribute("strangerId", stranger.getMemberId());
                		ServletActionContext.getRequest().setAttribute("strangerName", stranger.getRealname());
                	}
                }
                
        	}
        } catch (IOException e) {
        	e.printStackTrace();
        }
        ServletActionContext.getRequest().setAttribute("menu", 2);
    	return new DefaultHttpHeaders("send");
    }
    public HttpHeaders save(){
    	System.out.println("LeaveMessageController.save");
    	member = getProfile();
		try {
			if(member == null){
				ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
				return null;
			}
			String ids = ServletActionContext.getRequest().getParameter("ids");
	    	if(leaveMessage != null){
	    		LeaveMessageService.saveByReceivers(leaveMessage, ids);
	    	}
	    	ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getContextPath() + "/user/leave-message!outBox");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
        //return inBox();
    }
    
    public HttpHeaders saveReply(){
    	member = getProfile();
		try {
			if(member == null){
				ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
				return null;
			}
	    	if(leaveMessage != null){
	    		LeaveMessageService.saveReply(leaveMessage);
	    	}
	    	ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getContextPath() + "/user/leave-message!outBox");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
        //return inBox();
    }
    
    public HttpHeaders delete(){
    	System.out.println("LeaveMessageController.delete");
        member = getProfile();
        try {
        	if (member == null) {
        		ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
        	}else{
                String[] ids = ServletActionContext.getRequest().getParameterValues("ids");
                System.out.println(ids.toString());
                boolean success = LeaveMessageService.delete(ids);
        	}
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
        String menu = ServletActionContext.getRequest().getParameter("menu");
        if(menu!=null && menu.equals("1")){
        	return outBox();
        }else{
        	return inBox();
        }
    }
    
    public HttpHeaders reply(){
    	System.out.println("LeaveMessageController.reply");
        member = getProfile();
        try {
        	if (member == null) {
        		ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
        	}else{
            	if(id==null || id.equals("")) inBox();
            	leaveMessage = LeaveMessageService.findById(id);
        	}
        } catch (IOException e) {
        	e.printStackTrace();
        }
    	return new DefaultHttpHeaders("reply");
    }
    
    public HttpHeaders show(){
    	System.out.println("LeaveMessageController.show");
        member = getProfile();
        try {
        	if (member == null) {
        		ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
        	}else{
            	if(id==null || id.equals("")) return inBox();
            	leaveMessage = LeaveMessageService.findById(id);
            	if(leaveMessage==null)	return inBox();
            	if(leaveMessage.getInOutTag()==0){
            		LeaveMessageService.read(id);
            	}
        	}
        } catch (IOException e) {
        	e.printStackTrace();
        }

    	ServletActionContext.getRequest().setAttribute("menu", leaveMessage.getInOutTag());
    	return new DefaultHttpHeaders("show");
    }
    
    public void read(){
    	System.out.println("LeaveMessageController.read");
		member = getProfile();
        try {
        	if (member == null) {
        		ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
        	}else{
        		boolean success = false;
        		if(id!=null && !id.equals("")){
        			success = LeaveMessageService.read(id);
        		}
        		ServletActionContext.getResponse().getWriter().println(success);
        	}
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    
    public void getNewLeaveMessageCount(){
    	System.out.println("LeaveMessageController.getMessageCount");
        member = getProfile();
        try {
        	if (member != null) {
        		int count = LeaveMessageService.getNewLeaveMessageCount(member);
                ServletActionContext.getResponse().getWriter().println(count);
        	}
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
}
