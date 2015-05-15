package com.sitexa.sys.data;

/**
 * FuncInUser entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class FuncInUser implements java.io.Serializable {

    // Fields

    private FuncInUserId id;

    // Constructors

    /**
     * default constructor
     */
    public FuncInUser() {
    }

    /**
     * full constructor
     */
    public FuncInUser(FuncInUserId id) {
        this.id = id;
    }

    // Property accessors

    public FuncInUserId getId() {
        return this.id;
    }

    public void setId(FuncInUserId id) {
        this.id = id;
    }

}