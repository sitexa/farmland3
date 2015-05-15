package com.sitexa.sys.data;

/**
 * MenuInRoleId entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class MenuInRoleId implements java.io.Serializable {

    // Fields

    private Menu menu;
    private Role role;

    // Constructors

    /**
     * default constructor
     */
    public MenuInRoleId() {
    }

    /**
     * full constructor
     */
    public MenuInRoleId(Menu menu, Role role) {
        this.menu = menu;
        this.role = role;
    }

    // Property accessors

    public Menu getMenu() {
        return this.menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
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
        if (!(other instanceof MenuInRoleId))
            return false;
        MenuInRoleId castOther = (MenuInRoleId) other;

        return ((this.getMenu() == castOther.getMenu()) || (this.getMenu() != null
                && castOther.getMenu() != null && this.getMenu().equals(
                castOther.getMenu())))
                && ((this.getRole() == castOther.getRole()) || (this.getRole() != null
                && castOther.getRole() != null && this.getRole()
                .equals(castOther.getRole())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result
                + (getMenu() == null ? 0 : this.getMenu().hashCode());
        result = 37 * result
                + (getRole() == null ? 0 : this.getRole().hashCode());
        return result;
    }

}