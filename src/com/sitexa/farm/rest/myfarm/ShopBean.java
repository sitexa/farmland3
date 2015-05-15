/**
* @作者 leim
* @创建日期 2010-6-10
* @版本 V 1.0
*/ 
package com.sitexa.farm.rest.myfarm;

import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.FarmPlan;
import com.sitexa.farm.data.FarmStore;
import com.sitexa.farm.data.FarmStoreDAO;
import com.sitexa.farm.data.Seed;
import com.sitexa.farm.service.ActionBase;
import com.sitexa.farm.service.FarmingException;
import com.sitexa.farm.service.PlanService;
import com.sitexa.farm.service.SeedService;
import com.sitexa.sys.Sequence;
import com.sitexa.user.data.Member;
import com.sitexa.user.data.MemberCredit;
import com.sitexa.user.service.FriendService;
import com.sitexa.user.service.MemberCreditBean;
import com.sitexa.user.service.MemberCreditService;
import com.sitexa.user.service.UserService;

public class ShopBean {
	private static Log log = LogFactory.getLog(ShopBean.class);
	
	private boolean flag = true; 
	private String message = "";
	
	private FarmStore farmStore;
	private Farm farm;
	private Member buyer;
	private String number = "";
	private float price = 0;
	private int amount = 0;
	private String pwd = "";
	
	public ShopBean(FarmStore farmStore_, String number_, String pwd_){
		farmStore = farmStore_;
		number = number_;
		pwd = pwd_;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 验证数据
	 * @return
	 */
	private boolean CheckData(){
		if(farmStore==null){
			flag = false;
			message = "购买信息出错。";
			return false;
		}
		farm = farmStore.getFarm();
		if(farm==null || farm.getFarmId()==null || "".equals(farm.getFarmId())){
			flag = false;
			message = "农庄信息为空。";
			return false;			
		}
		buyer = farmStore.getBuyer();
		if(buyer==null || buyer.getMemberId()==null || "".equals(buyer.getMemberId())){
			flag = false;
			message = "购买人信息为空。";
			return false;			
		}
		if("".equals(farmStore.getObjectId())){
			flag = false;
			message = "商品分类为空，无法获取商品。";
			return false;	
		}
		if(farmStore.getItemId()==null || "".equals(farmStore.getItemId())){
			flag = false;
			message = "商品ID为空，无法确认商品。";
			return false;			
		}
		if(!getPriceAndNameByItemId()){
			flag = false;
			message = "无法找到该类商品。";
			return false;	
		}
		if(!testInt(number)){
			flag = false;
			message = "数量格式有误，应为整数。";
			return false;
		}
		farmStore.setAcreage(Integer.parseInt(number));
		if("".equals(pwd)){
			flag = false;
			message = "密码为空。";
			return false;			
		}
		return true;
	}
	
	public String pay(){
		if(CheckData() && checkRight() && checkCreditPassword(pwd) && checkCreditBalance()){
			String reason = "购买 " + farmStore.getItemName();
			MemberCreditBean bean = new MemberCreditBean(buyer);
			bean.pay(farm.getLand().getLord(), amount, pwd, reason);
			//存入仓库
			FarmStoreDAO dao = new FarmStoreDAO();
			FarmStore farmStore1 = dao.findIt(farm, buyer, farmStore.getItemId());
			if(farmStore1==null){
				farmStore.setRemark(reason);
				farmStore.setStoreId(Sequence.getId());
				dao.save(farmStore);
			}else{
				farmStore1.setAcreage(farmStore1.getAcreage()+farmStore.getAcreage());
				dao.update(farmStore1);
			}
			message = "购买成功，已将商品存入您的仓库内。";
		}
		return message;
	}

    /**
     * 检测用户操作权限
     * @return
     */
	private boolean checkRight() {
        if (buyer.getMemberId().equals(farm.getMember().getMemberId())) return true;
        if (FriendService.isFriends(buyer, farm.getMember())) return true;
        if (FriendService.isFriends(farm.getMember(), buyer)) return true;
        //农场主可以给客户操作.
        if (buyer.getMemberId().equals(farm.getLand().getLord().getMemberId())) return true;
        setMessage("用户权限不够。");
        return false;
    }
    
    /**
     * 验证支付密码
     *
     * @param password
     * @return
     */
    private boolean checkCreditPassword(String password) {
        boolean success = false;
        try {
            success = UserService.checkPassword(buyer.getMemberId(), password);
            if (!success) {
            	message = "密码验证失败。";
            }
        } catch (Exception e) {
            log.error(e);
        }
        return success;
    }
    
    /**
     * 验证帐户余额
     * @return
     */
    private boolean checkCreditBalance() {
    	amount = Math.round(farmStore.getAcreage() * price);
        if (amount == 0) return true;

        MemberCredit credit = MemberCreditService.getMemberCredit(buyer.getMemberId());
        if (credit == null) {
            message = "您好，请您先冲值花币";
            return false;
        } else if (credit.getCredit() < amount) {
        	message = "点卡余额不足，请及时冲值##";
            return false;
        }
        return true;
    }
    
    /**
     * 获取价格
     * @return
     */
    private boolean getPriceAndNameByItemId(){
    	if("1".equals(farmStore.getObjectId())){
    		Seed seed = SeedService.getById(farmStore.getItemId());
    		if(seed!=null){
    			price = Float.parseFloat(seed.getPrice());
    			farmStore.setItemName(seed.getSeedName());
    			return true;
    		}
    	}else if("2".equals(farmStore.getObjectId())){
    		FarmPlan plan = PlanService.getById(farmStore.getItemId());
    		if(plan!=null){
    			price = plan.getPrice();
    			farmStore.setItemName(plan.getPlanName());
    			return true;
    		}
    	}
    	return false;
    }
    
	/**
	 * @author leim 2010.6.10
	 * 验证字符串是否为数字
	 * @param str	
	 * @return boolean
	 */
	public boolean testInt(String str){
		String regEx = "^\\d+$";	
        return Pattern.compile(regEx).matcher(str).find();
	}
	
	/**
	 * @author leim 2010.6.10
	 * 验证字符串是否为数字
	 * @param str	
	 * @return boolean
	 */
	public boolean testFloat(String str){
		String regEx = "[0-9]+[\\.]?[0-9]+$";
        return Pattern.compile(regEx).matcher(str).find();
	}

	
}
