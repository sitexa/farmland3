package com.sitexa.user.data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

    // Fields

    private String userId;
    private String username;
    private String password;
    private String email;
    private boolean emailConfirmed;
    private String confirmCode;
    private boolean status;
    private Date RegisterDate;
    private Date validDate;
    private Date expiryDate;
    private int loginTimes;
    private Date loginDate;
    private Date lastLoginDate;
    private String loginAddress;
    private String lastLoginAddress;
    private int tryTimes;
    private Date tryDate;
    private Member member;
    private Set rolesInUser = new HashSet(0);
    private Set funcsInUser = new HashSet(0);

    public User() {
    }

    public User(String userId) {
        this.userId = userId;
    }

    public User(String userId, String username, String password, String email,
                boolean emailConfirmed, boolean status, Date registerDate,
                Date validDate, Date expiryDate) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.emailConfirmed = emailConfirmed;
        this.status = status;
        RegisterDate = registerDate;
        this.validDate = validDate;
        this.expiryDate = expiryDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public String getConfirmCode() {
        return confirmCode;
    }

    public void setConfirmCode(String confirmCode) {
        this.confirmCode = confirmCode;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getRegisterDate() {
        return RegisterDate;
    }

    public void setRegisterDate(Date registerDate) {
        RegisterDate = registerDate;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(int loginTimes) {
        this.loginTimes = loginTimes;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLoginAddress() {
        return loginAddress;
    }

    public void setLoginAddress(String loginAddress) {
        this.loginAddress = loginAddress;
    }

    public String getLastLoginAddress() {
        return lastLoginAddress;
    }

    public void setLastLoginAddress(String lastLoginAddress) {
        this.lastLoginAddress = lastLoginAddress;
    }

    public int getTryTimes() {
        return tryTimes;
    }

    public void setTryTimes(int tryTimes) {
        this.tryTimes = tryTimes;
    }

    public Date getTryDate() {
        return tryDate;
    }

    public void setTryDate(Date tryDate) {
        this.tryDate = tryDate;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Set getRolesInUser() {
        return rolesInUser;
    }

    public void setRolesInUser(Set rolesInUser) {
        this.rolesInUser = rolesInUser;
    }

    public Set getFuncsInUser() {
        return funcsInUser;
    }

    public void setFuncsInUser(Set funcsInUser) {
        this.funcsInUser = funcsInUser;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", emailConfirmed=" + emailConfirmed +
                ", status=" + status +
                ", RegisterDate=" + RegisterDate +
                ", validDate=" + validDate +
                ", expiryDate=" + expiryDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!userId.equals(user.userId)) return false;

        return true;
    }

}