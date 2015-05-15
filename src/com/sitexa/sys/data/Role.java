package com.sitexa.sys.data;

import java.util.HashSet;
import java.util.Set;

/**
 * Role entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Role implements java.io.Serializable {

    // Fields

    private String roleId;
    private String roleName;
    private String roleType;
    private Set menus = new HashSet(0);
    private Set funcs = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Role() {
    }

    /**
     * minimal constructor
     */
    public Role(String roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    // Property accessors

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleType() {
        return this.roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public Set getMenus() {
        return menus;
    }

    public void setMenus(Set menus) {
        this.menus = menus;
    }

    public Set getFuncs() {
        return funcs;
    }

    public void setFuncs(Set funcs) {
        this.funcs = funcs;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleType='" + roleType + '\'' +
                '}';
    }
}