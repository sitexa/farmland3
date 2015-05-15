package com.sitexa.sys.data;

/**
 * RoleInUser entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class RoleInUser implements java.io.Serializable {

    // Fields

    private RoleInUserId id;

    // Constructors

    /**
     * default constructor
     */
    public RoleInUser() {
    }

    /**
     * full constructor
     */
    public RoleInUser(RoleInUserId id) {
        this.id = id;
    }

    // Property accessors

    public RoleInUserId getId() {
        return this.id;
    }

    public void setId(RoleInUserId id) {
        this.id = id;
    }

}