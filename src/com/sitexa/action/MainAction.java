package com.sitexa.action;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.FarmPicture;
import com.sitexa.farm.data.Land;
import com.sitexa.farm.rest.FarmlanderAction;
import com.sitexa.market.data.Market;
import com.sitexa.market.data.MarketPicture;
import com.sitexa.post.data.FarmPost;
import com.sitexa.post.data.LandPost;
import com.sitexa.post.data.Post;
import com.sitexa.post.data.PostPicture;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;
import com.sitexa.user.data.User;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-4-8
 * Time: 9:26:37
 */
public class MainAction extends FarmlanderAction {
    protected User user;
    protected Member member;
    protected Site site;
    protected Land starLand;
    protected List<Land> lands = new ArrayList<Land>();

    protected List<Land> newLands = new ArrayList<Land>();
    protected List<Farm> newFarms = new ArrayList<Farm>();
    protected List<FarmPicture> hotFarms = new ArrayList<FarmPicture>();
    protected List<Market> newMarkets = new ArrayList<Market>();
    protected List<MarketPicture> newMarketPictures = new ArrayList<MarketPicture>();
    protected List<Member> newMembers = new ArrayList<Member>();

    protected List<PostPicture> newPictures = new ArrayList<PostPicture>();
    protected List<Post> news = new ArrayList<Post>();
    protected List<Post> activities = new ArrayList<Post>();
    protected List<Post> posts = new ArrayList<Post>();

    protected List<FarmPost> farmPosts = new ArrayList<FarmPost>();
    protected List<LandPost> landPosts = new ArrayList<LandPost>();
    protected List<Post> notices = new ArrayList<Post>();

    protected String siteAddress;

    public User getUser() {
        return user;
    }

    public Member getMember() {
        return member;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public List<PostPicture> getNewPictures() {
        return newPictures;
    }

    public void setNewPictures(List<PostPicture> newPictures) {
        this.newPictures = newPictures;
    }

    public Land getStarLand() {
        return starLand;
    }

    public void setStarLand(Land starLand) {
        this.starLand = starLand;
    }

    public List<Land> getLands() {
        return lands;
    }

    public List<Land> getNewLands() {
        return newLands;
    }

    public List<Farm> getNewFarms() {
        return newFarms;
    }

    public List<Market> getNewMarkets() {
        return newMarkets;
    }

    public List<Member> getNewMembers() {
        return newMembers;
    }

    public List<FarmPicture> getHotFarms() {
        return hotFarms;
    }

    public List<Post> getNews() {
        return news;
    }

    public List<Post> getActivities() {
        return activities;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<MarketPicture> getNewMarketPictures() {
        return newMarketPictures;
    }

    public List<FarmPost> getFarmPosts() {
        return farmPosts;
    }

    public List<LandPost> getLandPosts() {
        return landPosts;
    }

    public List<Post> getNotices() {
        return notices;
    }

    public Float getLng() {
        return site.getLongitude();
    }

    public Float getLat() {
        return site.getLatitude();
    }

    public Integer getZoom() {
        return getZM(site);
    }

    private Integer getZM(Site site) {
        if (site == null) return 8;
        if (site.getZoom() == null) return 8;
        String typeId = site.getType().getTypeId();
        int intId = Integer.parseInt(typeId);
        if (intId == 1) return 3;
        else if (intId == 2) return 7;
        else if (intId == 3) return 8;
        else return 9;
    }

    public String getSiteAddress() {
        Site root = SiteService.getRoot();
        if (site != null && site.getType() != null && site.getType().getTypeId() != null) {
            if ("1".equals(site.getType().getTypeId())) {  //country
                siteAddress = site.getName();
            } else if ("2".equals(site.getType().getTypeId())) { //province
                siteAddress = site.getName() + "," + root.getName();
            } else if ("3".equals(site.getType().getTypeId())) { //city
                siteAddress = site.getName() + "," + site.getParent().getName() + "," + root.getName();
            } else if ("4".equals(site.getType().getTypeId())) { //county and others
                siteAddress = site.getName() + "," + site.getParent().getParent().getName() + "," + root.getName();
            } else if ("5".equals(site.getType().getTypeId())) { //county and others
                siteAddress = site.getName() + "," + site.getParent().getParent().getParent().getName() + "," + root.getName();
            } else {
                siteAddress = site.getName() + "," + site.getParent().getParent().getName() + "," + root.getName();
            }
        } else {
            siteAddress = root.getName();
        }
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public String getLandPositions() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < lands.size(); i++) {
            Land land = lands.get(i);
            if (i != 0) sb.append(",");
            sb.append("new google.maps.LatLng(");
            sb.append(land.getLatitude());
            sb.append(",");
            sb.append(land.getLongitude());
            sb.append(")");
        }
        sb.append("]");
        return sb.toString();
    }

    //[{id:1,name:a},{id:2,name:b},{}]
    public String getLandNames() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < lands.size(); i++) {
            Land land = lands.get(i);
            if (i != 0) sb.append(",");
            sb.append("{'landId':'");
            sb.append(land.getLandId());
            sb.append("',");
            sb.append("'landName':'");
            sb.append(land.getLandName());
            sb.append("'}");
        }
        sb.append("]");
        return sb.toString();
    }

}
