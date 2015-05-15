package com.sitexa.market.action;

import com.sitexa.farm.data.Farm;
import com.sitexa.framework.Constants;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.market.data.Market;
import com.sitexa.market.data.MarketCategory;
import com.sitexa.market.service.MarketService;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: xnpeng
 * Date: 2009-10-15
 * Time: 19:13:15
 */
public class MarketAction extends BaseAction {
    protected String id; //siteId

    protected Site site;
    protected Farm farm;
    protected Market market;
    protected List<Market> markets = new ArrayList<Market>();

	protected List<Market> children = new ArrayList<Market>();
    
	protected Page page = new Page(10);

    /**
     * @author leim 2008.11.27添加
     * 上传文件
     * Begin
     */
    protected File upload;;
    protected String uploadFileName;
    protected String uploadContentType;


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
    
    public List<Market> getMarkets() {
		return markets;
	}

	public void setMarkets(List<Market> markets) {
		this.markets = markets;
	}
	
    public List<Market> getChildren() {
		return children;
	}

	public void setChildren(List<Market> children) {
		this.children = children;
	}
	
    // Constructors
	/**
	 * End
	 */
    
	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public Map getItemTypes() {
        Map map = new HashMap();
        map.put(1, "供应");
        map.put(2, "需求");
        return map;
    }

    public List<MarketCategory> getMarketCategories() {
        return MarketService.getMarketCategory();
    }
    
    public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}

