package com.sitexa.farm.rest.admin.work;

import com.sitexa.farm.data.Crops;
import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.Farming;
import com.sitexa.farm.data.Land;
import com.sitexa.farm.rest.FarmlanderAction;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.user.data.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-3-3
 * Time: 9:44:41
 */
public class WorkAction extends FarmlanderAction {
    protected String id;
    protected Land land;
    protected Farm farm;
    protected Member member;
    protected Crops crops;
    protected Farming farming;
    protected List<Land> lands = new ArrayList<Land>();
    protected List<Farm> farms = new ArrayList<Farm>();
    protected List<Crops> cropses = new ArrayList<Crops>();
    protected List<Farming> farmings = new ArrayList<Farming>();
    protected List<Farming> task = new ArrayList<Farming>();

    protected Page page = new Page(20);

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Farming getFarming() {
        return farming;
    }

    public void setFarming(Farming farming) {
        this.farming = farming;
    }

    public Crops getCrops() {
        return crops;
    }

    public void setCrops(Crops crops) {
        this.crops = crops;
    }

    public List<Crops> getCropses() {
        return cropses;
    }

    public void setCropses(List<Crops> cropses) {
        this.cropses = cropses;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public List<Farming> getFarmings() {
        return farmings;
    }

    public void setFarmings(List<Farming> farmings) {
        this.farmings = farmings;
    }

    public List<Land> getLands() {
        return lands;
    }

    public void setLands(List<Land> lands) {
        this.lands = lands;
    }

    public List<Farm> getFarms() {
        return farms;
    }

    public void setFarms(List<Farm> farms) {
        this.farms = farms;
    }

    public List<Farming> getTask() {
        return task;
    }

    public void setTask(List<Farming> task) {
        this.task = task;
    }
}
