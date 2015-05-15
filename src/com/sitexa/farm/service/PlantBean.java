package com.sitexa.farm.service;

import com.sitexa.farm.data.Crops;
import com.sitexa.farm.data.CropsDAO;
import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.FarmStore;
import com.sitexa.farm.data.FarmStoreDAO;
import com.sitexa.farm.data.Farming;
import com.sitexa.farm.rest.myfarm.ShopController;
import com.sitexa.sys.Sequence;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-1-27
 * Time: 13:09:07
 */
public class PlantBean extends ActionBase {
    private static Log log = LogFactory.getLog(PlantBean.class);

    public PlantBean(Farming operation) {
        super(operation);
    }

    protected boolean checkConditions() {
        if (seed == null) {
            this.addErrorMessage("您操作的农作物不存在##");
            throw new FarmingException(SEED_ERROR);
        }
        if (!Farm.PLOUGH_STATUS.equals(farm.getPloughStatus())) {
            this.addErrorMessage("不能播种尚未耕过的土地##");
            throw new FarmingException(PLANTING_ERROR);
        }
        if (operation.getQuantity() > getNoPlantingAcreage()) {
            this.addErrorMessage("土地面积不够请求的操作##");
            throw new FarmingException(NO_ENOUGH_ACREAGE);
        }
        Object[] seedInfo = StoreService.getGood(farm, farmer, ShopController.SEED_ID, seed.getSeedId());
        if(operation.getQuantity() > Integer.parseInt(seedInfo[2].toString())){
        	this.addErrorMessage("仓库种子不够，请您先购买后再播种##");
        	throw new FarmingException("仓库种子不够，请您先购买后再播种##");
        }
        return true;
    }

    protected Integer calcPrice() {
        
        float price1 = 0, price2 = 0, qty = 0, count = 0;
        /*
        //购买种子的费用
        try {
            price1 = Float.parseFloat(seed.getPrice());
            qty = operation.getQuantity();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        count = price1 * qty;
		*/
        //操作的费用
        try {
            price2 = Float.parseFloat(action.getExpense());
            qty = operation.getQuantity();
            if (qty>0 && qty < 10) qty = 10;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        count += price2 * qty;
        return (int) count;
    }

    protected void createOrChangeCrops() {
        try {
            if (crops == null) {    //如果还没有种植该种作物
                crops = new Crops();
                crops.setFarm(farm);
                crops.setSeed(seed);
                crops.setSeedNumber(operation.getQuantity());
                crops.setVitalPower(1);
                crops.setMaturity(1);
                crops.setStartDate(new Date());
                crops.setCropsId(Sequence.getId());
                createCrops(crops);
            } else {    //如果以前已经种植过该种作物,增加作物数量，改变状态.
                updateCropsNumberAndPower(crops, operation.getQuantity(), 1, 1);
            }
            this.addSuccessMessage("春种一粒谷秋收万颗米##");
        } catch (Exception e) {
            log.error(e);
            throw new FarmingException(CREATE_OR_CHANGE_CROPS_ERROR);
        }
    }


    private void createCrops(Crops crops1) {
        if (crops1 == null) return;
        if (crops1.getCropsId() == null)
            crops1.setCropsId(Sequence.getId());
        try {
            CropsDAO dao = new CropsDAO();
            dao.save(crops1);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new FarmingException(DAO_ERROR);
        }

    }

    private void updateCropsNumberAndPower(Crops crops, int number, int vitalPower, int maturity) {
        if (crops == null) return;
        try {
            CropsDAO dao = new CropsDAO();
            Crops crops1 = dao.findById(crops.getCropsId());
            crops1.setSeedNumber(crops1.getSeedNumber() + operation.getQuantity());
            crops1.setMaturity(crops1.getMaturity() + maturity);
            crops1.setVitalPower(crops1.getVitalPower() + vitalPower);
            dao.update(crops1);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new FarmingException(DAO_ERROR);
        }
    }
	/**
	 * 从仓库中扣除种子
	 */
    protected void otherWork(){
    	int qty = operation.getQuantity();
    	if(qty>0){
    		List<FarmStore> SeedsInfo = StoreService.getSeedsInfoFromStore(farm, farmer, seed.getSeedId());
    		Iterator<FarmStore> it = SeedsInfo.iterator();
    		FarmStore seedInfo;
    		FarmStoreDAO dao = new FarmStoreDAO();
    		//先从当前农庄、当前用户中扣除
    		while(it.hasNext()){
    			seedInfo = it.next();
    			if(seedInfo.getFarm().getFarmId().equals(farm.getFarmId()) && seedInfo.getBuyer().getMemberId().equals(farmer.getMemberId())){
    				if(seedInfo.getAcreage()>qty){
    					seedInfo.setAcreage(seedInfo.getAcreage()-qty);
    					dao.update(seedInfo);
    					return;
    				}else{
    					qty = qty - seedInfo.getAcreage();
    					dao.delete(seedInfo);
    					it.remove();											//删除最后一个使用的元素，即当前seedinfo
    					if(qty==0) return;
    				}
    				break;														//只有一条，所以跳出
    			}
    		}
    		//再从当前农庄的其它农庄主中扣除
    		it = SeedsInfo.iterator();
    		while(it.hasNext()){
    			seedInfo = it.next();
    			if(seedInfo.getFarm().getFarmId().equals(farm.getFarmId())){
    				if(seedInfo.getAcreage()>qty){
    					seedInfo.setAcreage(seedInfo.getAcreage()-qty);
    					dao.update(seedInfo);
    					return;
    				}else{
    					qty = qty - seedInfo.getAcreage();
    					dao.delete(seedInfo);
    					it.remove();											//删除最后一个使用的元素，即当前seedinfo
    					if(qty==0) return;
    				}
     			}
    		}
    		//再从当前用户的其它农庄中扣除
    		it = SeedsInfo.iterator();
    		while(it.hasNext()){
    			seedInfo = it.next();
    			if(seedInfo.getBuyer().getMemberId().equals(farmer.getMemberId())){
    				if(seedInfo.getAcreage()>qty){
    					seedInfo.setAcreage(seedInfo.getAcreage()-qty);
    					dao.update(seedInfo);
    					return;
    				}else{
    					qty = qty - seedInfo.getAcreage();
    					dao.delete(seedInfo);
    					it.remove();											//删除最后一个使用的元素，即当前seedinfo
    					if(qty==0) return;
    				}
     			}
    		}
    	}
    }
    
}
