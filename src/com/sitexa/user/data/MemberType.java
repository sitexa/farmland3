package com.sitexa.user.data;

import java.util.HashSet;
import java.util.Set;

/**
 * MemberType entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class MemberType implements java.io.Serializable {

    /**
     * 小区官员:
     * 1,普通内容管理员;
     * 2,物业管理员;
     * 3,安全管理员;
     * 4,活动管理员;
     * 5,商务管理员;
     * 6,...
     */
    // Fields

    private String typeId;
    private String name;
    private Set members = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public MemberType() {
    }

    /**
     * minimal constructor
     */
    public MemberType(String typeId, String name) {
        this.typeId = typeId;
        this.name = name;
    }

    /**
     * full constructor
     */
    public MemberType(String typeId, String name, Set members) {
        this.typeId = typeId;
        this.name = name;
        this.members = members;
    }

    // Property accessors

    public String getTypeId() {
        return this.typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set getMembers() {
        return this.members;
    }

    public void setMembers(Set members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "MemberType{" +
                "typeId='" + typeId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}