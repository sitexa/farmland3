package com.sitexa.farm.rest.land;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.Land;
import com.sitexa.farm.data.RentMode;
import com.sitexa.farm.rest.FarmlanderAction;
import com.sitexa.user.data.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Oscar Peng, xnpeng@163.com
 * Date: 11-1-24
 * Time: 下午4:44
 */
public class BuyfarmAction extends FarmlanderAction {
    protected Land land;
    protected Farm farm;
    protected Member member;
    protected List<RentMode> rentModes = new ArrayList<RentMode>();
    protected String password;

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<RentMode> getRentModes() {
        return rentModes;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
