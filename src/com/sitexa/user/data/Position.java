package com.sitexa.user.data;

import java.util.HashSet;
import java.util.Set;

/**
 * TPosition entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Position implements java.io.Serializable {

    // Fields

    private String positionId;
    private Position parent;
    private String name;
    private Set children = new HashSet(0);
    private Set rights = new HashSet(0);
    private Set memberPositions = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Position() {
    }

    /**
     * minimal constructor
     */
    public Position(String positionId, String name) {
        this.positionId = positionId;
        this.name = name;
    }

    /**
     * full constructor
     */
    public Position(String positionId, Position parent, String name,
                    Set children, Set rights, Set memberPositions) {
        this.positionId = positionId;
        this.parent = parent;
        this.name = name;
        this.children = children;
        this.rights = rights;
        this.memberPositions = memberPositions;
    }

    // Property accessors

    public String getPositionId() {
        return this.positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public Position getParent() {
        return parent;
    }

    public void setParent(Position parent) {
        this.parent = parent;
    }

    public Set getChildren() {
        return children;
    }

    public void setChildren(Set children) {
        this.children = children;
    }

    public Set getRights() {
        return rights;
    }

    public void setRights(Set rights) {
        this.rights = rights;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set getMemberPositions() {
        return this.memberPositions;
    }

    public void setMemberPositions(Set memberPositions) {
        this.memberPositions = memberPositions;
    }

    @Override
    public String toString() {
        return "Position{" +
                "positionId='" + positionId + '\'' +
                ", parent=" + parent +
                ", name='" + name + '\'' +
                '}';
    }
}