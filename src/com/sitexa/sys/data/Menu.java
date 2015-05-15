package com.sitexa.sys.data;

import java.util.HashSet;
import java.util.Set;

/**
 * Menu entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Menu implements java.io.Serializable {

    // Fields

    private String menuId;
    private String menuCode;
    private String caption;
    private String captionCn;
    private String imagePath;
    private String linkPath;
    private String status;
    private String remark;
    private Set menusInRole = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Menu() {
    }

    /**
     * minimal constructor
     */
    public Menu(String menuId, String menuCode, String caption, String linkPath) {
        this.menuId = menuId;
        this.menuCode = menuCode;
        this.caption = caption;
        this.linkPath = linkPath;
    }

    /**
     * full constructor
     */
    public Menu(String menuId, String menuCode, String caption,
                String captionCn, String imagePath, String linkPath, String status,
                String remark, Set menusInRole) {
        this.menuId = menuId;
        this.menuCode = menuCode;
        this.caption = caption;
        this.captionCn = captionCn;
        this.imagePath = imagePath;
        this.linkPath = linkPath;
        this.status = status;
        this.remark = remark;
        this.menusInRole = menusInRole;
    }

    // Property accessors

    public String getMenuId() {
        return this.menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuCode() {
        return this.menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getCaption() {
        return this.caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCaptionCn() {
        return this.captionCn;
    }

    public void setCaptionCn(String captionCn) {
        this.captionCn = captionCn;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getLinkPath() {
        return this.linkPath;
    }

    public void setLinkPath(String linkPath) {
        this.linkPath = linkPath;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set getMenusInRole() {
        return this.menusInRole;
    }

    public void setMenusInRole(Set menusInRole) {
        this.menusInRole = menusInRole;
    }

}