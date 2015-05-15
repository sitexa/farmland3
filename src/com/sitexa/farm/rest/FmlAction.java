package com.sitexa.farm.rest;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.FarmPicture;
import com.sitexa.farm.data.Land;
import com.sitexa.market.data.Market;
import com.sitexa.market.data.MarketPicture;
import com.sitexa.post.data.FarmPost;
import com.sitexa.post.data.LandPost;
import com.sitexa.post.data.Post;
import com.sitexa.post.data.PostPicture;
import com.sitexa.user.data.Member;
import com.sitexa.user.data.User;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-8
 * Time: 15:49:46
 */
public class FmlAction extends FarmlanderAction {
    protected User user;
    protected Member member;

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

    public User getUser() {
        return user;
    }

    public Member getMember() {
        return member;
    }

    public List<PostPicture> getNewPictures() {
        return newPictures;
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
}
