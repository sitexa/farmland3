package com.sitexa.sys.data;

/**
 * MenuInRole entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class MenuInRole implements java.io.Serializable {

    // Fields

    private MenuInRoleId id;

    // Constructors

    /**
     * default constructor
     */
    public MenuInRole() {
    }

    /**
     * full constructor
     */
    public MenuInRole(MenuInRoleId id) {
        this.id = id;
    }

    // Property accessors

    public MenuInRoleId getId() {
        return this.id;
    }

    public void setId(MenuInRoleId id) {
        this.id = id;
    }

}