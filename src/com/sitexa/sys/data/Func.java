package com.sitexa.sys.data;

import java.util.HashSet;
import java.util.Set;

/**
 * Func entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Func implements java.io.Serializable {

    // Fields

    private String funcId;
    private String funcName;
    private String funcType;
    private String application;
    private String remark;
    private Set roles = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Func() {
    }

    /**
     * minimal constructor
     */
    public Func(String funcId, String funcName, String application) {
        this.funcId = funcId;
        this.funcName = funcName;
        this.application = application;
    }

    // Property accessors

    public String getFuncId() {
        return this.funcId;
    }

    public void setFuncId(String funcId) {
        this.funcId = funcId;
    }

    public String getFuncName() {
        return this.funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getFuncType() {
        return this.funcType;
    }

    public void setFuncType(String funcType) {
        this.funcType = funcType;
    }

    public String getApplication() {
        return this.application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set getRoles() {
        return this.roles;
    }

    public void setRoles(Set roles) {
        this.roles = roles;
    }

}