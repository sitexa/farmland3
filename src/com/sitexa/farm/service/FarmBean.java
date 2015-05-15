package com.sitexa.farm.service;

import com.sitexa.farm.data.*;
import com.sitexa.sys.Sequence;
import com.sitexa.user.data.Member;
import com.sitexa.user.service.MemberCreditBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

/**
 * User: xnpeng
 * Date: 2010-1-25
 * Time: 11:40:05
 */
public class FarmBean {
    private static Log log = LogFactory.getLog(Farm.class);

    public final String OBJECT_NULL_ERROR = "10";
    public final String DAO_ERROR = "11";
    public final String PAYMENT_ERROR = "12";
    public final String SOLD_ERROR = "13";
    public final String NO_ENOUGH_CREDIT_ERROR = "14";
    public final String INIT_FARM_ERROR = "15";
    public final String CREDIT_ERROR = "16";

    private Farm farm;

    private String success_string = "";
    private String error_string = "";

    public String getSuccessInfo() {
        return success_string;
    }

    public String getErrorInfo() {
        return error_string;
    }

    public FarmBean(Farm farm) {
        System.out.println("FarmBean.FarmBean");
        this.farm = farm;
        init();
    }

    private void init() {
        if (farm == null || farm.getFarmId() == null || "".equals(farm.getFarmId())) throw new FarmException(OBJECT_NULL_ERROR);
        try {
            FarmDAO dao = new FarmDAO();
            farm = dao.findById(farm.getFarmId());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new FarmException(DAO_ERROR);
        }
    }

    public boolean buyFarm(Member member, String pwd) {
        System.out.println("FarmBean.buyFarm");
        if (member == null) {
            error_string += "用户错误!";
            return false;
        }

        if (farm.getMember() != null) {
            error_string += "该农庄已经出售##";
            throw new FarmException(SOLD_ERROR);
        }

        int price = 0;
        try {
            price = Integer.parseInt(farm.getRentPrice());
        } catch (Exception e) {
            log.error("农场价格转换错误!");
        }

        MemberCreditBean bean = null;
        try {
            bean = new MemberCreditBean(member);
            bean.pay(farm.getLand().getLord(), price, pwd, "购买农庄");
        } catch (Exception e) {
            if (bean != null) {
                error_string += bean.getErrorInfo();
                log.error(error_string);
            } else {
                error_string += e.getMessage();
                log.error(e.getMessage());
            }
            throw new FarmException(PAYMENT_ERROR);
        }

        try {
            FarmDAO dao = new FarmDAO();
            farm = dao.findById(farm.getFarmId());
            farm.setMember(member);
            farm.setSite(farm.getLand().getSite());
            farm.setRentDate(new Date());
            dao.update(farm);
        } catch (Exception e) {
            error_string += "数据存取错误##";
            log.error(e.getMessage());
            throw new FarmException(DAO_ERROR);
        }
        try {
            initFarm();
        } catch (Exception e) {
            error_string += "农庄初始化错误##";
            log.error(e.getMessage());
            throw new FarmException(INIT_FARM_ERROR);
        }
        System.out.println("FarmBean.buyFarm end");
        return true;
    }

    /**
     * 购买农庄之后,对农庄进行初始化,对t_farm相关表进行清理.
     */
    private void initFarm() {
        System.out.println("FarmBean.initFarm");
        try {
            FarmDAO dao = new FarmDAO();
            farm = dao.findById(farm.getFarmId());
            farm.setPloughStatus("0");
            dao.update(farm);
        } catch (Exception e) {
            error_string += "数据存取错误##";
            log.error(e.getMessage());
            throw new FarmException(DAO_ERROR);
        }

/*
        try {
            CropStatusDAO dao = new CropStatusDAO();
            dao.deleteCropStatus(farm);
        } catch (Exception e) {
            error_string += "数据存取错误##";
            log.error(e.getMessage());
            throw new FarmException(DAO_ERROR);
        }
*/

        try {
            CropsDAO dao = new CropsDAO();
            dao.deleteCrops(farm);
        } catch (Exception e) {
            error_string += "数据存取错误##";
            log.error(e.getMessage());
            throw new FarmException(DAO_ERROR);
        }

        try {
            FarmingDAO dao = new FarmingDAO();
            dao.deleteFarming(farm);
        } catch (Exception e) {
            error_string += "数据存取错误##";
            log.error(e.getMessage());
            throw new FarmException(DAO_ERROR);
        }

        try {
            FarmOwnerDAO dao = new FarmOwnerDAO();
            dao.removeOwners(farm);
        } catch (Exception e) {
            error_string += "数据存取错误##";
            log.error(e.getMessage());
            throw new FarmException(DAO_ERROR);
        }

        System.out.println("FarmBean.initFarm end");
    }

    public void takeBack() {
        System.out.println("FarmBean.takeBack");
        try {
            initFarm();
        } catch (Exception e) {
            error_string += "初始化错误##";
            throw new FarmException(INIT_FARM_ERROR);
        }

        try {
            FarmDAO dao = new FarmDAO();
            farm = dao.findById(farm.getFarmId());
            farm.setMember(null);
            dao.update(farm);
            success_string += "成功回收农庄##";
        } catch (Exception e) {
            error_string += "数据存取错误##";
            log.error(e.getMessage());
            throw new RuntimeException(DAO_ERROR);
        }
    }

    public void addOwner(Member member) {
        if (member == null) return;
        try {
            FarmOwnerDAO dao = new FarmOwnerDAO();
            FarmOwner fo = dao.findByFarmAndMember(farm, member);
            if (fo != null) return;
            fo = new FarmOwner();
            fo.setFarm(farm);
            fo.setMember(member);
            fo.setJoinDate(new Date());
            fo.setOwnerId(Sequence.getId());
        } catch (Exception e) {
            error_string += "数据存取错误##";
            log.error(e.getMessage());
            throw new RuntimeException(DAO_ERROR);
        }
    }

    public void delOwner(Member member) {
        if (member == null) return;
        try {
            FarmOwnerDAO dao = new FarmOwnerDAO();
            dao.removeOwner(farm, member);
        } catch (Exception e) {
            error_string += "数据存取错误##";
            log.error(e.getMessage());
            throw new RuntimeException(DAO_ERROR);
        }
    }

    public static void main(String[] args) {

    }

}
