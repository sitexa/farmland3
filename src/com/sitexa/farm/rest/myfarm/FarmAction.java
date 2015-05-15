package com.sitexa.farm.rest.myfarm;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.FarmOwner;
import com.sitexa.farm.data.FarmPicture;
import com.sitexa.farm.rest.FarmlanderAction;
import com.sitexa.user.data.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-14
 * Time: 21:40:51
 */
public class FarmAction extends FarmlanderAction {
    protected String id;  //farmId
    protected Farm farm;
    protected FarmPicture picture;
    protected Member member;
    protected FarmOwner farmOwner;
    protected List<FarmOwner> owners = new ArrayList<FarmOwner>();
    protected List<FarmPicture> pictures = new ArrayList<FarmPicture>();

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

    public FarmPicture getPicture() {
        return picture;
    }

    public void setPicture(FarmPicture picture) {
        this.picture = picture;
    }

    public List<FarmOwner> getOwners() {
        return owners;
    }

    public void setOwners(List<FarmOwner> owners) {
        this.owners = owners;
    }

    public List<FarmPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<FarmPicture> pictures) {
        this.pictures = pictures;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public FarmOwner getFarmOwner() {
        return farmOwner;
    }

    public void setFarmOwner(FarmOwner farmOwner) {
        this.farmOwner = farmOwner;
    }

}
