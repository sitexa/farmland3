package com.sitexa.user.data;

import java.util.Date;

/**
 * TLeaveMessage entity. @author MyEclipse Persistence Tools
 */

public class LeaveMessage implements java.io.Serializable, Cloneable {

	// Fields
	private String id;
	private String relateId;
	private String title;
	private Member receiver;
	private Member sender;
	private String message;
	private Date sendDate;
	private Integer readTag;
	private LeaveMessage parent;
	private Integer inOutTag; 

	// Constructors

	/** default constructor */
	public LeaveMessage() {
	}

	/** full constructor */
	public LeaveMessage(String id, String title, Member receiver,
			Member sender, String message, Date sendDate, Integer readTag,
			LeaveMessage parent) {
		this.id = id;
		this.title = title;
		this.receiver = receiver;
		this.sender = sender;
		this.message = message;
		this.sendDate = sendDate;
		this.readTag = readTag;
		this.parent = parent;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRelateId() {
		return relateId;
	}

	public void setRelateId(String relateId) {
		this.relateId = relateId;
	}
	
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Member getReceiver() {
		return this.receiver;
	}

	public void setReceiver(Member receiver) {
		this.receiver = receiver;
	}

	public Member getSender() {
		return this.sender;
	}

	public void setSender(Member sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Integer getReadTag() {
		return this.readTag;
	}

	public void setReadTag(Integer readTag) {
		this.readTag = readTag;
	}

	public LeaveMessage getParent() {
		return this.parent;
	}

	public void setParent(LeaveMessage parent) {
		this.parent = parent;
	}
	
	public Integer getInOutTag() {
		return inOutTag;
	}

	public void setInOutTag(Integer inOutTag) {
		this.inOutTag = inOutTag;
	}
	
	public LeaveMessage clone() {  
		LeaveMessage o = null;  
		try {  
		    o = (LeaveMessage) super.clone();  
		} catch (CloneNotSupportedException e) {  
		    e.printStackTrace();  
		}  
		return o;  
	}  
}