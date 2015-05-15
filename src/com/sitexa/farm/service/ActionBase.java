/**
 * @作者 leim
 * @创建日期 2010-5-31
 * @版本 V 1.0
 */
package com.sitexa.farm.service;

import com.sitexa.farm.data.*;
import com.sitexa.sys.Sequence;
import com.sitexa.user.data.Member;
import com.sitexa.user.data.MemberCredit;
import com.sitexa.user.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.List;

/**
 * doFarming,要完成一系列动作：
 * 0，检查关操除操作所需的信用（如果信用不够，则拒绝该操作)
 * 1，插入t_farming表；
 * 2，修改t_Crops表,改变种子的成熟度和生命力；
 * 3，修改t_crops_status表，记录对种子的操作；
 * 4，修改t_farm表,改变积分:操作1次，增加1分；朋友操作1次，增加2分；
 * <p/>
 * 根据不同的操作(Action）有不同的逻辑.成熟度随时间增加，生命力随操作改变.
 * 耕地：种子生命力降为0；
 * 播种：种子生命力加1；
 * 洒水：种子生命力加1；
 * 除草：种子生命力加1；
 * 施肥：种子生命力加1；
 * 杀虫：种子生命力加1；
 * 采摘：种子生命力减1；
 * 收割：种子生命力归0；
 * <p/>
 * 没有耕地不能播种，没有播种(Seed)，不能进行后续操作，即只能对作物(Crops)进行操作(action);
 * 如果是耕地，当土地上没有种植作物时，只在t_farming表中增加记录；如果已经种植作物，则拒绝该操作。
 *
 * @param farming ;
 * @param pwd     ;
 * @return crops;
 * @throws com.sitexa.user.service.CreditException;
 *
 */

/**
 * 1，检查权限；
 * 2，检查作物；
 * 3，检查动作；
 * 4，检查点卡；
 * 5，先付钱；
 * 6，后做事；
 * 7，记日志；
 * 离散化操作，每次操作只能是一个单位。比如，一次，一包，等；
 * <p/>
 * User: xnpeng
 * Date: 2010-1-22
 * Time: 12:52:01
 */
public class ActionBase {

    private static Log log = LogFactory.getLog(ActionBase.class);

    public final String OBJECT_ERROR = "10";
    public final String DAO_ERROR = "11";
    public final String NORIGHT_ERROR = "12";
    public final String NOSEED_ERROR = "13";
    public final String NO_ACTION_ERROR = "14";
    public final String NOTFIT_ERROR = "15";
    public final String NO_ENOUGH_CREDIT_ERROR = "16";
    public final String PAYMENT_ERROR = "17";
    public final String CREATE_OR_CHANGE_CROPS_ERROR = "18";
    public final String CREATE_LOG_ERROR = "19";
    public final String NO_SERVICE_ERROR = "20";
    public final String UNKOWN_SERVICE_ERROR = "21";
    public final String PRICE_ERROR = "22";
    public final String NOCROPS_ERROR = "23";
    public final String FARM_FARMER_ERROR = "24";
    public final String NO_ENOUGH_ACREAGE = "25";
    public final String UNKOWN_ERROR = "99";

    public final String CONDITION_ERROR = "condition error.";
    public final String CHECK_CREDIT_PASSWORD_ERROR = "check credit password error.";
    public final String PLANTING_ERROR = "no plough error.";
    public final String PLOUGH_ERROR = "plough error.";
    public final String FARM_ERROR = "farm error.";
    public final String FARMER_ERROR = "farmer error.";
    public final String FACTION_ERROR = "faction error.";
    public final String SERVICE_ERROR = "service error.";
    public final String RIGHT_ERROR = "right error.";
    public final String CROPS_ERROR = "crops error.";
    public final String SEED_ERROR = "seed error.";

    protected Farm farm;
    protected Farming operation;
    protected Service service;
    protected Faction action;
    protected Seed seed;
    protected Crops crops;
    protected Member farmer;
    protected MemberCredit credit;

    private int amount = 0;
    protected String password = "";

    protected String errorMessage = "";
    protected String successMessage = "";

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void addErrorMessage(String errorMessage) {
        this.errorMessage += errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public void addSuccessMessage(String successMessage) {
        this.successMessage += successMessage;
    }

    public ActionBase(Farming operation) {
        this.operation = operation;
        this.farmer = operation.getFarmer();
        this.farm = operation.getFarm();
        this.action = operation.getFaction();
        this.seed = operation.getSeed();
        loadObject();
    }

    private void loadObject() {
        try {
            if (farm != null && farm.getFarmId() != null)
                farm = FarmService.getById(farm.getFarmId());
            if (farmer != null && farmer.getMemberId() != null)
                farmer = MemberService.getMember(farmer.getMemberId());
            if (action != null && action.getFactionId() != null)
                action = FactionService.getById(action.getFactionId());
            if (action != null && action.getActionId() != null)
                service = ServiceService.getById(action.getActionId());
            if (farmer != null && farmer.getMemberId() != null)
                credit = MemberCreditService.getMemberCredit(farmer.getMemberId());
            if (seed != null && seed.getSeedId() != null)
                seed = SeedService.getById(seed.getSeedId());
            if (farm != null && seed != null)
                crops = CropService.getByFarmAndSeed(farm, seed);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
            throw new FarmingException(OBJECT_ERROR);
        }
    }
    
    public void doFarming() {
        try {
            check();
            if (!checkCreditBalance()) {
                throw new FarmingException(NO_ENOUGH_CREDIT_ERROR);
            }
            doPayment();
            otherWork();
            
            createOrChangeCrops();
            updateFarming(operation);
            this.addSuccessMessage("##操作成功!");
        } catch (FarmingException e) {
            log.error(e);
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            this.addErrorMessage("未知错误##");
            throw new FarmingException(UNKOWN_ERROR);
        }
    }
    
    public void execute(String password) {
        this.setPassword(password);
        try {
            check();
            if (isPayMode()) {
                if (!checkCreditPassword(password)) {
                    throw new FarmingException("PASSWORD_ERROR");
                }
                if (!checkCreditBalance()) {
                    throw new FarmingException(NO_ENOUGH_CREDIT_ERROR);
                }
            	saveFarming("1");
            }else{
            	otherWork();											//应在planBean中实现execute方法，但考虑现只有播种用到otherWork();所以，暂时就这样处理，方便
                createOrChangeCrops();
                saveFarming(null);
            }
            this.addSuccessMessage("##操作成功!");
        } catch (FarmingException e) {
            log.error(e);
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            this.addErrorMessage("未知错误##");
            throw new FarmingException(UNKOWN_ERROR);
        }
    }

    public boolean check() {
        checkData();

        checkRight();

        checkConditions();
        return true;
    }

    /**
     * 检测数据
     *
     * @return
     */
    private boolean checkData() {
        if (farm == null) {
            this.addErrorMessage("农庄不存在##");
            throw new FarmingException(FARM_ERROR);
        }
        if (farmer == null) {
            this.addErrorMessage("操作用户不存在##");
            throw new FarmingException(FARMER_ERROR);
        }
        if (action == null) {
            this.addErrorMessage("农场不提供该服务##");
            throw new FarmingException(FACTION_ERROR);
        }
        if (service == null) {
            this.addErrorMessage("没有此服务##");
            throw new FarmingException(SERVICE_ERROR);
        }
        return true;
    }

    /**
     * 检测用户操作权限
     *
     * @return
     */
    public boolean checkRight() {
        if (farmer.getMemberId().equals(farm.getMember().getMemberId())) return true;
        if (FriendService.isFriends(farmer, farm.getMember())) return true;
        if (FriendService.isFriends(farm.getMember(), farmer)) return true;

        //农场主可以给客户操作.
        if (farmer.getMemberId().equals(farm.getLand().getLord().getMemberId())) return true;
        this.addErrorMessage("您好，您没有权限进行此操作；<br/>提示：只能操作自己或朋友的农庄##");
        throw new FarmingException(RIGHT_ERROR);
        //return false;
    }

    /**
     * 检测服务条件(用于已种植作物服务的检测:浇水、施肥、除草、杀虫、采摘、收割...)
     *
     * @return
     */
    protected boolean checkConditions() {
        if (seed == null) {
            this.addErrorMessage("种子不存在##");
            throw new FarmingException(SEED_ERROR);
        }
        if (crops == null || crops.getSeedNumber() <= 0) {
            this.addErrorMessage("农作物不存在##");
            throw new FarmingException(CROPS_ERROR);
        }
        return true;
    }

    /**
     * 是否为支付模式
     */
    private boolean isPayMode() {
        String mode = operation.getPaymode();
        return (mode != null && mode.equals("1"));
    }

    /**
     * 是否支付费用
     *
     * @return
     */
    private boolean whetherToPay() {
        if (isPayMode()) {
            if (!checkCreditPassword(password)) {
                throw new FarmingException("PASSWORD_ERROR");
            }
            if (!checkCreditBalance()) {
                throw new FarmingException(NO_ENOUGH_CREDIT_ERROR);
            }
            return true;
        }
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
            success = UserService.checkPassword(farmer.getMemberId(), password);
            if (!success) {
                this.addErrorMessage("密码有误##");
            }
        } catch (Exception e) {
            log.error(e);
            throw new FarmingException(CHECK_CREDIT_PASSWORD_ERROR);
        }
        return success;
    }
    /**
     * 检测点卡余额
     * @return
     */
    private boolean checkCreditBalance() {
        amount = calcPrice();
        if (this.getAmount() == 0) return true;

        credit = MemberCreditService.getMemberCredit(farmer.getMemberId());
        if (credit == null) {
            this.addErrorMessage("您好，请您先冲值花币##");
            return false;
        } else if (credit.getCredit() < amount) {
            this.addErrorMessage("点卡余额不足，请及时冲值##");
            return false;
        }
        return true;
    }
    /**
     * 支付费用
     * @return
     */
    private boolean doPayment() {
        System.out.println("ActionBase.doPayment");
        if (amount == 0) return true;

        String reason = action.getActionName();
        if (seed != null)
            reason += " " + seed.getSeedName();
        System.out.println("reason = " + reason);

        MemberCreditBean bean = null;
        try {
            //给农场服务公司付款.
            bean = new MemberCreditBean(farmer);
            bean.pay(farm.getLand().getLord(), amount, reason);
            return true;
        } catch (Exception e) {
            if (bean != null)
                this.addErrorMessage("付款出错##");
            else
                this.addErrorMessage("付款出错##");
            e.printStackTrace();
            log.error(e);
            throw new FarmingException(PAYMENT_ERROR);
        }
    }

    private void saveFarming(String state) {
        System.out.println("FarmingBean.saveFarming");
        if (operation == null) return;
        try {
            operation.setFarmingId(Sequence.getId());
            operation.setStartTime(new Date());
            if(state!=null)	operation.setState(state);
            FarmingDAO dao = new FarmingDAO();
            dao.save(operation);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
            throw new FarmingException(CREATE_LOG_ERROR);
        }
    }

    private void updateFarming(Farming farming) {
        System.out.println("FarmingBean.updateFarming");
        if (farming == null || farming.getFarmingId()==null || "".equals(farming.getFarmingId())) return;
        try {
        	farming.setState("0");
            FarmingDAO dao = new FarmingDAO();
            dao.update(farming);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
            throw new FarmingException(CREATE_LOG_ERROR);
        }
    }
    
    /**
     * 计算费用 = 服务单价 * 面积；
     *
     * @return
     */
    protected Integer calcPrice() {
        //其他操作,按种植面积收费.
        float price = 0, qty = 0, count = 0;
        try {
            price = Integer.parseInt(action.getExpense());
            qty = crops.getSeedNumber();
            if (qty < 10) qty = 10;
            count = price * qty;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return (int) count;
    }

    /**
     * 新建或修改农作物信息
     */
    protected void createOrChangeCrops() {
        try {
            updateCropsPower(crops, 1, 1);
        } catch (Exception e) {
            log.error(e);
            throw new FarmingException(CREATE_OR_CHANGE_CROPS_ERROR);
        }
    }

    protected void updateCropsPower(Crops crops, int vitalPower, int maturity) {
        if (crops == null) return;
        CropsDAO dao = new CropsDAO();
        Crops crops1 = dao.findById(crops.getCropsId());
        crops1.setVitalPower(crops.getVitalPower() + vitalPower);
        crops1.setMaturity(crops.getMaturity() + maturity);
        dao.update(crops1);
    }


    /**
     * 计算农庄可种植面积：总面积-已种植面积；
     * 已种植面积:crops.seedNumber
     *
     * @return int;
     */
    protected int getNoPlantingAcreage() {
        int farm_acreage = 0;
        int acreage = 0;
        try {
            try {
                farm_acreage = farm.getAcreage().intValue();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            List<Crops> list = CropService.getByFarm(farm);
            for (Crops crops1 : list) {
                acreage += crops1.getSeedNumber();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new FarmingException(DAO_ERROR);
        }

        return farm_acreage - acreage;
    }

    protected void otherWork(){
    	
    }

}

