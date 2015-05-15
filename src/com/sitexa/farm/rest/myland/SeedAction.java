package com.sitexa.farm.rest.myland;

import com.sitexa.farm.data.Land;
import com.sitexa.farm.data.Seed;
import com.sitexa.farm.rest.FarmlanderAction;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.user.data.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-22
 * Time: 10:39:26
 */
public class SeedAction extends FarmlanderAction {
    protected String id;//landId;
    protected Land land;
    protected Member member;
    protected Seed seed;
    protected List<Seed> seeds = new ArrayList<Seed>();
    protected Page page = new Page(10);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
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

    public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}
