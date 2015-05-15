package com.sitexa.user.data;

/**
 * FriendGroup entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class FriendGroup implements java.io.Serializable {

    // Fields

    private String groupId;
    private String groupName;

    // Constructors

    /**
     * default constructor
     */
    public FriendGroup() {
    }

    /**
     * minimal constructor
     */
    public FriendGroup(String groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    // Property accessors

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}