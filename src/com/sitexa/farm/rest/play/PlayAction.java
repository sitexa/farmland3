package com.sitexa.farm.rest.play;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.Land;
import com.sitexa.farm.rest.FarmlanderAction;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.post.data.Category;
import com.sitexa.post.data.Post;
import com.sitexa.post.data.PostPicture;
import com.sitexa.post.data.PostType;
import com.sitexa.site.data.Site;
import com.sitexa.user.data.Member;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-8
 * Time: 14:52:16
 */
public class PlayAction extends FarmlanderAction {
    protected static final String regEx_trn = "[\\t\\r\\n]";
    protected static final String regEx_space = "(&nbsp;)*";
    protected static final String regEx_link = "<([A|a][ ]*href)[^>]*>|</([A|a])[^>]*>";
    protected static final String regEx_html = "<(([A-Z][A-Z0-9]*)|([a-z][a-z0-9]*))\\b[^>]*>|</(([A-Z][A-Z0-9]*)|([a-z][a-z0-9]*))>";
    protected static final String regEx_subject = regEx_html + "|" + regEx_trn + "|" + regEx_space;
    protected static final String regEx_content = regEx_link + "|" + regEx_trn + "|" + regEx_space;

    protected String words = "";
    protected Page page = new Page(15);

    protected Post post;
    protected List<Post> posts = new ArrayList<Post>();

    protected Member creator;
    protected Site site;
    protected Land land;
    protected Category category;
    protected List<Category> categories = new ArrayList<Category>();
    protected PostType postType;
    protected List<PostType> postTypes = new ArrayList<PostType>();

    protected Post parent;
    protected List<Post> children = new ArrayList<Post>();
    protected String comment;

    protected List<File> upload = new ArrayList<File>();
    protected List<String> uploadFileName = new ArrayList<String>();
    protected List<String> uploadContentType = new ArrayList<String>();
    protected List<PostPicture> pictures = new ArrayList<PostPicture>();

    protected boolean haveRight = false;

    protected List<Post> hotPosts = new ArrayList<Post>();
    protected List<PostPicture> hotPictures = new ArrayList<PostPicture>();
    protected List<Member> newMembers = new ArrayList<Member>();
    protected List<Farm> newFarms = new ArrayList<Farm>();

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }


    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Member getCreator() {
        return creator;
    }

    public void setCreator(Member creator) {
        this.creator = creator;
    }

    public Site getSite() {
        return site;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public List<PostType> getPostTypes() {
        return postTypes;
    }

    public void setPostTypes(List<PostType> postTypes) {
        this.postTypes = postTypes;
    }

    public Post getParent() {
        return parent;
    }

    public void setParent(Post parent) {
        this.parent = parent;
    }

    public List<Post> getChildren() {
        return children;
    }

    public void setChildren(List<Post> children) {
        this.children = children;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<File> getUpload() {
        return upload;
    }

    public void setUpload(List<File> upload) {
        this.upload = upload;
    }

    public List<String> getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(List<String> uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public List<String> getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(List<String> uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public List<PostPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<PostPicture> pictures) {
        this.pictures = pictures;
    }

    public boolean isHaveRight() {
        return haveRight;
    }

    public void setHaveRight(boolean haveRight) {
        this.haveRight = haveRight;
    }

    public List<Post> getHotPosts() {
        return hotPosts;
    }

    public void setHotPosts(List<Post> hotPosts) {
        this.hotPosts = hotPosts;
    }

    public List<PostPicture> getHotPictures() {
        return hotPictures;
    }

    public void setHotPictures(List<PostPicture> hotPictures) {
        this.hotPictures = hotPictures;
    }

    public List<Member> getNewMembers() {
        return newMembers;
    }

    public void setNewMembers(List<Member> newMembers) {
        this.newMembers = newMembers;
    }

    public List<Farm> getNewFarms() {
        return newFarms;
    }

    public void setNewFarms(List<Farm> newFarms) {
        this.newFarms = newFarms;
    }
}
