package com.sitexa.farm.rest.myfarm;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.FarmOwner;
import com.sitexa.farm.rest.FarmlanderAction;
import com.sitexa.user.data.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-16
 * Time: 16:53:17
 */
public class FarmOwnerAction extends FarmlanderAction {
    protected String id;//farmId;
    protected Farm farm;
    protected FarmOwner owner;
    protected Member member;

    protected List<FarmOwner> owners = new ArrayList<FarmOwner>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public FarmOwner getOwner() {
        return owner;
    }

    public void setOwner(FarmOwner owner) {
        this.owner = owner;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<FarmOwner> getOwners() {
        return owners;
    }

    public void setOwners(List<FarmOwner> owners) {
        this.owners = owners;
    }
}
