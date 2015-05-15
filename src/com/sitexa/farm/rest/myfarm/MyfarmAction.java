package com.sitexa.farm.rest.myfarm;

import com.sitexa.farm.data.*;
import com.sitexa.framework.Constants;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.post.data.Post;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;
import com.sitexa.user.service.MemberService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-1
 * Time: 10:20:26
 */
public class MyfarmAction extends BaseAction {
    protected String id;    //farmId
    protected Farm farm;
    protected List<Farm> farms = new ArrayList<Farm>();
    protected List<Farm> friendFarms = new ArrayList<Farm>();

    protected Member member;
    protected List<Member> newMembers = new ArrayList<Member>();
    protected List<Farm> neFarms = new ArrayList<Farm>();
    protected List<Member> visitors = new ArrayList<Member>();

    protected Farming farming;
    protected List<Farming> farmings = new ArrayList<Farming>();

    protected Crops crops;
    protected List<Crops> cropses = new ArrayList<Crops>();

    protected com.sitexa.farm.data.Faction faction;
    protected List<com.sitexa.farm.data.Faction> factions = new ArrayList<com.sitexa.farm.data.Faction>();

    protected Seed seed;
    protected List<Seed> seeds = new ArrayList<Seed>();
    protected List<Seed> farmSeeds = new ArrayList<Seed>();
    protected List<Post> newPosts = new ArrayList<Post>();
    protected List<Post> newActions = new ArrayList<Post>();
    protected List<Post> newNotices = new ArrayList<Post>();
    protected List<Post> newPlans = new ArrayList<Post>();

    protected String creditPassword;

    protected Page page = new Page(10);

    protected File upload;
    protected String uploadFileName;
    protected String uploadContentType;

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

    public Farming getFarming() {
        return farming;
    }

    public void setFarming(Farming farming) {
        this.farming = farming;
    }

    public List<Farming> getFarmings() {
        return farmings;
    }

    public void setFarmings(List<Farming> farmings) {
        this.farmings = farmings;
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

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public List<Faction> getFactions() {
        return factions;
    }

    public void setFactions(List<Faction> factions) {
        this.factions = factions;
    }

    public Seed getSeed() {
        return seed;
    }

    public void setSeed(Seed seed) {
        this.seed = seed;
    }

    public List<Seed> getSeeds() {
        return seeds;
    }

    public void setSeeds(List<Seed> seeds) {
        this.seeds = seeds;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Member> getNewMembers() {
        newMembers = MemberService.getNewMembers(5);
        return newMembers;
    }

    public List<Farm> getFarms() {
        return farms;
    }

    public void setFarms(List<Farm> farms) {
        this.farms = farms;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<Farm> getNeFarms() {
        return neFarms;
    }

    public String getCreditPassword() {
        return creditPassword;
    }

    public void setCreditPassword(String creditPassword) {
        this.creditPassword = creditPassword;
    }

    public List<Seed> getFarmSeeds() {
        return farmSeeds;
    }

    public void setFarmSeeds(List<Seed> farmSeeds) {
        this.farmSeeds = farmSeeds;
    }

    public List<Post> getNewPosts() {
        return newPosts;
    }

    public void setNewPosts(List<Post> newPosts) {
        this.newPosts = newPosts;
    }

    public List<Farm> getFriendFarms() {
        return friendFarms;
    }

    public void setFriendFarms(List<Farm> friendFarms) {
        this.friendFarms = friendFarms;
    }

    public List<Post> getNewActions() {
        return newActions;
    }

    public void setNewActions(List<Post> newActions) {
        this.newActions = newActions;
    }

    public List<Post> getNewNotices() {
        return newNotices;
    }

    public void setNewNotices(List<Post> newNotices) {
        this.newNotices = newNotices;
    }

    public List<Post> getNewPlans() {
        return newPlans;
    }

    public void setNewPlans(List<Post> newPlans) {
        this.newPlans = newPlans;
    }

    public List<Member> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Member> visitors) {
        this.visitors = visitors;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
