package com.sitexa.user.data;

import java.util.HashSet;
import java.util.Set;

/**
 * Right entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Right implements java.io.Serializable {

    // Fields

    private String rightId;
    private String name;
    private Set positionRights = new HashSet(0);
    private Set memberRights = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Right() {
    }

    /**
     * minimal constructor
     */
    public Right(String rightId, String name) {
        this.rightId = rightId;
        this.name = name;
    }

    /**
     * full constructor
     */
    public Right(String rightId, String name, Set positionRights,
                 Set memberRights) {
        this.rightId = rightId;
        this.name = name;
        this.positionRights = positionRights;
        this.memberRights = memberRights;
    }

    // Property accessors

    public String getRightId() {
        return this.rightId;
    }

    public void setRightId(String rightId) {
        this.rightId = rightId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set getPositionRights() {
        return this.positionRights;
    }

    public void setPositionRights(Set positionRights) {
        this.positionRights = positionRights;
    }

    public Set getMemberRights() {
        return this.memberRights;
    }

    public void setMemberRights(Set memberRights) {
        this.memberRights = memberRights;
	}

}