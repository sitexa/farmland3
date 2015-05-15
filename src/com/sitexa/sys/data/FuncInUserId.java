package com.sitexa.sys.data;

import com.sitexa.user.data.User;

/**
 * FuncInUserId entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class FuncInUserId implements java.io.Serializable {

    // Fields

    private Func func;
    private User user;

    // Constructors

    /**
     * default constructor
     */
    public FuncInUserId() {
    }

    /**
     * full constructor
     */
    public FuncInUserId(Func func, User user) {
        this.func = func;
        this.user = user;
    }

    // Property accessors

    public Func getFunc() {
        return this.func;
    }

    public void setFunc(Func func) {
        this.func = func;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof FuncInUserId))
            return false;
        FuncInUserId castOther = (FuncInUserId) other;

        return ((this.getFunc() == castOther.getFunc()) || (this.getFunc() != null
                && castOther.getFunc() != null && this.getFunc().equals(
                castOther.getFunc())))
                && ((this.getUser() == castOther.getUser()) || (this.getUser() != null
                && castOther.getUser() != null && this.getUser()
                .equals(castOther.getUser())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result
                + (getFunc() == null ? 0 : this.getFunc().hashCode());
        result = 37 * result
                + (getUser() == null ? 0 : this.getUser().hashCode());
        return result;
    }

}