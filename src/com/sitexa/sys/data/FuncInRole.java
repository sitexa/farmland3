package com.sitexa.sys.data;

/**
 * FuncInRole entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class FuncInRole implements java.io.Serializable {

    // Fields

    private FuncInRoleId id;

    // Constructors

    /**
     * default constructor
     */
    public FuncInRole() {
    }

    /**
     * full constructor
     */
    public FuncInRole(FuncInRoleId id) {
        this.id = id;
    }

    // Property accessors

    public FuncInRoleId getId() {
        return this.id;
    }

    public void setId(FuncInRoleId id) {
        this.id = id;
    }

}