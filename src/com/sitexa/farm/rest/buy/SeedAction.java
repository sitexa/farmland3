/**
 * @作�? leim
 * @创建日期 2010-4-21
 * @版本 V 1.0
 */
package com.sitexa.farm.rest.buy;

import com.sitexa.farm.data.Land;
import com.sitexa.farm.data.Seed;
import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;

public class SeedAction extends BaseAction{
	protected String id;
	protected Seed seed;
	protected Land land;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Seed getSeed() {
		return seed;
	}

	public void setSeed(Seed seed) {
		this.seed = seed;
	}

	public Land getLand() {
		return land;
	}

	public void setLand(Land land) {
		this.land = land;
	}

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
