package com.sitexa.sys.data;

/**
 * FuncInRoleId entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class FuncInRoleId implements java.io.Serializable {

    // Fields

    private Func func;
    private Role role;

    // Constructors

    /**
     * default constructor
     */
    public FuncInRoleId() {
    }

    /**
     * full constructor
     */
    public FuncInRoleId(Func func, Role role) {
        this.func = func;
        this.role = role;
    }

    // Property accessors

    public Func getFunc() {
        return this.func;
    }

    public void setFunc(Func func) {
        this.func = func;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof FuncInRoleId))
            return false;
        FuncInRoleId castOther = (FuncInRoleId) other;

        return ((this.getFunc() == castOther.getFunc()) || (this.getFunc() != null
                && castOther.getFunc() != null && this.getFunc().equals(
                castOther.getFunc())))
                && ((this.getRole() == castOther.getRole()) || (this.getRole() != null
                && castOther.getRole() != null && this.getRole()
                .equals(castOther.getRole())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result
                + (getFunc() == null ? 0 : this.getFunc().hashCode());
        result = 37 * result
                + (getRole() == null ? 0 : this.getRole().hashCode());
        return result;
    }

}