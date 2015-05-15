package com.sitexa.farm.service;

import com.sitexa.farm.data.*;
import com.sitexa.sys.Sequence;
import com.sitexa.user.data.Member;
import com.sitexa.user.data.MemberCredit;
import com.sitexa.user.service.MemberCreditService;
import com.sitexa.user.service.MemberService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-7-30
 * Time: 9:57:20
 */
public class WorkBean {
    private static Log log = LogFactory.getLog(WorkBean.class);
    public final String OBJECT_ERROR = "Object Load Error.";

    protected Farm farm;
    protected Farming operation;
    protected Service service;
    protected Faction faction;
    protected Seed seed;
    protected Crops crops;
    protected Member farmer;
    protected MemberCredit credit;

    public WorkBean(Farming operation) {
        this.operation = operation;
        this.farmer = operation.getFarmer();
        this.farm = operation.getFarm();
        this.faction = operation.getFaction();
        this.seed = operation.getSeed();
        loadObject();
    }

    private void loadObject() {
        try {
            if (seed != null && seed.getSeedId() != null)
                seed = SeedService.getById(seed.getSeedId());

            if (farm != null && farm.getFarmId() != null) {
                farm = FarmService.getById(farm.getFarmId());
                crops = CropService.getByFarmAndSeed(farm, seed);
            }

            if (farmer != null && farmer.getMemberId() != null) {
                farmer = MemberService.getMember(farmer.getMemberId());
                credit = MemberCreditService.getMemberCredit(farmer.getMemberId());

            }
            if (faction != null && faction.getFactionId() != null) {
                faction = FactionService.getById(faction.getFactionId());
                service = ServiceService.getById(faction.getActionId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
            throw new FarmingException(OBJECT_ERROR);
        }
    }

    public void execute() {
        try {
            doCheck();
            createOrChangeCrops();
            saveFarming();
        } catch (Exception e) {
            e.printStackTrace();
            throw new FarmingException("操作出错：" + e.getMessage());
        }
    }

    private void doCheck() {
        if (farm == null) {
            throw new FarmingException("农庄不存在##");
        }
        if (farmer == null) {
            throw new FarmingException("操作用户不存在##");
        }
        if (faction == null) {
            throw new FarmingException("农场不提供该服务##");
        }
        if (seed == null) {
            throw new FarmingException("没有此作物##");
        }
    }

    private void createOrChangeCrops() {
        //播种,如果已经存在，则更新，否则，创建。
        if (ServiceEnum.PLANT.value().equals(faction.getActionId())) {
            if (crops == null) {
                crops = new Crops();
                crops.setCropsId(Sequence.getId());
                crops.setFarm(farm);
                crops.setSeed(seed);
                crops.setSeedNumber(10);
                crops.setStartDate(new Date());
                crops.setSeedNumber(operation.getQuantity());
                crops.setMaturity(1);
                crops.setVitalPower(1);
                CropsDAO dao = new CropsDAO();
                dao.save(crops);
            } else {
                crops.setSeedNumber(crops.getSeedNumber() + operation.getQuantity());
                crops.setVitalPower(crops.getVitalPower() + 1);
                crops.setMaturity(crops.getMaturity() + 1);
                CropsDAO dao = new CropsDAO();
                dao.update(crops);
            }
        }
        //管理
        else if (ServiceEnum.MANAGE.value().equals(faction.getActionId())) {
            crops.setVitalPower(crops.getVitalPower() + 1);
            crops.setMaturity(crops.getMaturity() + 1);
            CropsDAO dao = new CropsDAO();
            dao.update(crops);
        }
        //收割
        else if (ServiceEnum.DELETE.value().equals(faction.getActionId())) {
            CropsDAO dao = new CropsDAO();
            dao.delete(crops);
        } else {
            throw new RuntimeException("Not implemented.");
        }
    }

    private void saveFarming() {
        FarmingDAO dao = new FarmingDAO();
        operation.setFarmingId(Sequence.getId());
        dao.save(operation);
    }

}
