package com.sitexa.farm.rest.myfarm;

import com.sitexa.farm.data.*;
import com.sitexa.farm.service.*;
import com.sitexa.framework.util.ImageUtil;
import com.sitexa.post.service.FarmPostService;
import com.sitexa.user.data.Member;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * User: xnpeng
 * Date: 2010-4-1
 * Time: 10:12:18
 */
@SuppressWarnings("unchecked")
public class MyfarmController extends MyfarmAction {
    private static String TYPE_NEWS = "14";
    private static String TYPE_ACTION = "15";
    private static String TYPE_NOTICE = "16";
    private static String TYPE_PLAN = "17";

    private static int CNT = 5;

    public HttpHeaders index() {
        member = getProfile();
        if (member == null) {
            try {
                ServletActionContext.getResponse()
                        .sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
                return null;
            } catch (IOException ignored) {
            }
        }

        farms = FarmService.getFarmByMember(member);
        List<FarmOwner> fos = FarmOwnerService.getFarms(member);
        for (FarmOwner farmOwner : fos) {
            farms.add(farmOwner.getFarm());
        }

        if (farms.size() > 0)
            farm = farms.get(0);

        if (farm != null) {
            FarmService.setScore(farm.getFarmId(), 1);
            visitors = FarmVisitService.getVisitorByFarm(farm, CNT);

            farmings.clear();
            farmings.addAll(farm.getFarmings());

            cropses.clear();
            cropses.addAll(farm.getCropses());

            seeds = SeedService.getSeeds(farm.getLand());

            factions = FactionService.getFactions(farm.getLand());

            friendFarms = FarmService.getFriendFarms(farm.getMember());

            newPosts = FarmPostService.getNewPosts(farm, TYPE_NEWS, CNT);
            newActions = FarmPostService.getNewPosts(farm, TYPE_ACTION, 1);
            newNotices = FarmPostService.getNewPosts(farm, TYPE_NOTICE, 1);
            newPlans = FarmPostService.getNewPosts(farm, TYPE_PLAN, 1);

            Set set = farm.getLand().getFarms();
            for (Object aSet : set) {
                Farm farm1 = (Farm) aSet;
                if (farm1.getMember() != null && farm1 != farm) neFarms.add(farm1);
            }
        } else {
            return new DefaultHttpHeaders("nofarm");
        }

        return new DefaultHttpHeaders("index").disableCaching();
    }

    public HttpHeaders show() {
        member = getProfile();
        if (member == null) {
            return index();
        }

        farm = FarmService.getById(id);

        if (farm != null) {

            FarmService.setScore(farm.getFarmId(), 1);
            visitors = FarmVisitService.getVisitorByFarm(farm, CNT);
            //访客
            if (!farm.getMember().getMemberId().equals(member.getMemberId())) {
                FarmVisit visit = new FarmVisit();
                visit.setFarm(farm);
                visit.setVisitor(member);
                FarmVisitService.create(visit);
            }

            farms = FarmService.getFarmByMember(farm.getMember());

            farmings.clear();
            farmings.addAll(farm.getFarmings());

            cropses.clear();
            cropses.addAll(farm.getCropses());
            if (seed != null && seed.getSeedId() != null) {
                String seedId = null;
                for (Crops c : cropses) {
                    seedId = c.getSeed().getSeedId();
                    if (seedId.equals(seed.getSeedId())) {
                        crops = c;
                        break;
                    }
                }
            }

            seeds = SeedService.getSeeds(farm.getLand());

            factions = FactionService.getFactions(farm.getLand());
            friendFarms = FarmService.getFriendFarms(farm.getMember());

            newPosts = FarmPostService.getNewPosts(farm, TYPE_NEWS, CNT);
            newActions = FarmPostService.getNewPosts(farm, TYPE_ACTION, 1);
            newNotices = FarmPostService.getNewPosts(farm, TYPE_NOTICE, 1);
            newPlans = FarmPostService.getNewPosts(farm, TYPE_PLAN, 1);

            Set set = farm.getLand().getFarms();
            for (Object aSet : set) {
                Farm farm1 = (Farm) aSet;
                if (farm1.getMember() != null) neFarms.add(farm1);
            }
        }

        return new DefaultHttpHeaders("index").disableCaching();
    }

    public HttpHeaders tools() {
        member = getProfile();
        if (member == null) internalRedirects("logon");
        //当前操作农庄
        String farmId = ServletActionContext.getRequest().getParameter("farmId");
        farm = FarmService.getById(farmId);
        //对自己的地或者朋友的地有操作权限
        if (!haveRight()) {
            //判断是否有权限操作，如果没有权限，刚返回查看页面。
            addActionError("您没有操作权限。");
            return new DefaultHttpHeaders("operated").disableCaching();
        }

        String factionId = ServletActionContext.getRequest().getParameter("factionId");
        faction = FactionService.getById(factionId);
        if (faction != null && farm != null) {
            seeds = StoreService.getSeedsFromStore(farm, member);
            farmSeeds = CropService.getSeedsByFarm(farm);
        }
        farmings.clear();
        farmings.addAll(FarmingService.getFarming(farm, faction));

        if (faction != null) {
            if (ServiceEnum.PLOUGH.value().equals(faction.getActionId())
                    || ServiceEnum.PLOUGH_MACHINE.value().equals(faction.getActionId())) {
                return new DefaultHttpHeaders("plough").disableCaching();
            } else if (ServiceEnum.PLANTING.value().equals(faction.getActionId())) {
                ServletActionContext.getRequest().setAttribute("noPlantingAcreage", getNoPlantingAcreage(farm));
                return new DefaultHttpHeaders("plant").disableCaching();
            } else if (ServiceEnum.FERTILIZE.value().equals(faction.getActionId())) {
                return new DefaultHttpHeaders("fertilize").disableCaching();
            } else if (ServiceEnum.WEED.value().equals(faction.getActionId())) {
                return new DefaultHttpHeaders("weed").disableCaching();
            } else if (ServiceEnum.WATERING.value().equals(faction.getActionId())) {
                return new DefaultHttpHeaders("watering").disableCaching();
            } else if (ServiceEnum.KILL_INSECT.value().equals(faction.getActionId())) {
                return new DefaultHttpHeaders("kill").disableCaching();
            } else if (ServiceEnum.PICK.value().equals(faction.getActionId())) {
                return new DefaultHttpHeaders("pick").disableCaching();
            } else if (ServiceEnum.HARVEST.value().equals(faction.getActionId())) {
                return new DefaultHttpHeaders("harvest").disableCaching();
            } else {
                return new DefaultHttpHeaders("other").disableCaching();
            }
        }
        return new DefaultHttpHeaders("tools").disableCaching();
    }

    public HttpHeaders status() {
        String cropsId = ServletActionContext.getRequest().getParameter("cropsId");
        String farmId = ServletActionContext.getRequest().getParameter("farmId");
        String seedId = ServletActionContext.getRequest().getParameter("seedId");

        if (cropsId != null && !"".equals(cropsId)) {
            crops = CropService.getById(cropsId);
        } else if ((farmId != null && !"".equals(farmId)) && (seedId != null && !"".equals(seedId))) {
            crops = CropService.getByFarmAndSeed(farm, seed);
        }
        return new DefaultHttpHeaders("status").disableCaching();
    }

    public HttpHeaders operate() {
        System.out.println("MyfarmController.operate");
        //当前用户
        member = getProfile();
        if (member == null) return index();
        ActionBase biz = null;
        try {
            //当前操作农庄
            farm = FarmService.getById(farm.getFarmId());
            //当前id
            id = farm.getFarmId();
            //对自己的地或者朋友的地有操作权限
            if (!haveRight()) {
                //判断是否有权限操作，如果没有权限，刚返回查看页面。
                addActionError("您没有操作权限。");
                return new DefaultHttpHeaders("operated").disableCaching();
            }

            FarmService.setScore(farm.getFarmId(), 1);

            farming.setFarmer(member);
            farming.setFarm(farm);

            if (farming.getSeed() != null && farming.getSeed().getSeedId() != null) {
                seed = SeedService.getById(farming.getSeed().getSeedId());
                farming.setSeed(seed);
            }
            faction = FactionService.getById(faction.getFactionId());
            farming.setFaction(faction);
            //farming.setRemark(creditPassword);
            farming.setStartTime(new Date());
            if (farming.getPaymode().equals("true"))
                farming.setPaymode("1");
            else
                farming.setPaymode("0");

            biz = getAction(farming);
/*
            if (!biz.checkRight()) {
                addActionError(biz.getErrorInfo());
                return show();
            }

            if (!biz.prepare()) {
                addActionError(biz.getErrorInfo());
                return show();
            }
*/
            biz.execute(creditPassword);

            addActionMessage(biz.getSuccessMessage());

            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
            if (biz != null)
                addActionError("错误:" + biz.getErrorMessage());
            else
                addActionError("错误:" + e.getMessage());
        }
        //return show();
        return new DefaultHttpHeaders("operated").disableCaching();
    }

    public void getSeedImage() {
        byte[] imgData = null;
        String seedId = ServletActionContext.getRequest().getParameter("seedId");
        if (seedId != null || !"".equalsIgnoreCase(seedId)) {
            Seed seed = SeedService.getById(seedId);
            if (seed != null) {
                imgData = seed.getImg();
            }

        }
        if (imgData != null) {
            BufferedImage img = ImageUtil.getDecompressedImage(imgData);
            try {
                ImageIO.write(img, "JPEG", ServletActionContext.getResponse().getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getActionImage() {
        byte[] imgData = null;
        String actionId = ServletActionContext.getRequest().getParameter("actionId");
        if (actionId != null || !"".equalsIgnoreCase(actionId)) {
            Faction svc = FactionService.getById(actionId);
            imgData = svc.getImg();
        }
        if (imgData != null) {
            BufferedImage img = ImageUtil.getDecompressedImage(imgData);
            try {
                ImageIO.write(img, "JPEG", ServletActionContext.getResponse().getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public HttpHeaders upload() {
        return new DefaultHttpHeaders("upload").disableCaching();
    }

    public HttpHeaders savePicture() {
        farm = FarmService.getById(id);
        if (farm == null) return null;
        if (!haveRight()) return null;

        if (!acceptType(uploadContentType)) return null;

        String title = this.getParameter("title");
        String description = this.getParameter("description");

        JSONObject jsonObj = FarmService.createFarmPicture(upload, uploadFileName, id, title, description);
        ServletActionContext.getRequest().setAttribute("msg", jsonObj != null ? "上传成功！" : "上传失败！");
        return new DefaultHttpHeaders("uploaded");
    }

    private boolean haveRight() {
        member = getProfile();
        if (member == null) return false;
        if (farm == null || farm.getMember() == null) return false;
        for (Object o : farm.getOwners()) {
            FarmOwner owner = (FarmOwner) o;
            if (owner.getMember().getMemberId().equals(member.getMemberId())) return true;
        }
        Member lord = farm.getLand().getLord();
        if (member.getMemberId().equals(lord.getMemberId())) return true;
        if (!farm.getMember().getMemberId().equals(member.getMemberId())) return false;
        return true;
    }

    private boolean acceptType(String type) {
        return ("image/gif".equalsIgnoreCase(type)) ||
                ("image/jpg".equalsIgnoreCase(type)) ||
                ("image/jpeg".equalsIgnoreCase(type)) ||
                ("image/pjpeg".equalsIgnoreCase(type)) ||
                ("image/bmp".equalsIgnoreCase(type)) ||
                ("image/png".equalsIgnoreCase(type));
    }

    private ActionBase getAction(Farming farming) {
        ActionBase action = null;
        String actionId = farming.getFaction().getActionId();
        if (ServiceEnum.PLANTING.value().equals(actionId)) {
            action = new PlantBean(farming);
        } else if (ServiceEnum.WATERING.value().equals(actionId)) {
            action = new WateringBean(farming);
        } else if (ServiceEnum.FERTILIZE.value().equals(actionId)) {
            action = new FertilizeBean(farming);
        } else if (ServiceEnum.WEED.value().equals(actionId)) {
            action = new WeedBean(farming);
        } else if (ServiceEnum.KILL_INSECT.value().equals(actionId)) {
            action = new KillBean(farming);
        } else if (ServiceEnum.PICK.value().equals(actionId)) {
            action = new PickBean(farming);
        } else if (ServiceEnum.HARVEST.value().equals(actionId)) {
            action = new HarvestBean(farming);
        } else if (ServiceEnum.PLOUGH.value().equals(actionId)
                || ServiceEnum.PLOUGH_MACHINE.value().equals(actionId)) {
            action = new PloughBean(farming);
        } else {
            action = new OtherBean(farming);
        }
        return action;
    }

    /**
     * 计算农庄可种植面积：总面积-已种植面积；
     * 已种植面积:crops.seedNumber
     *
     * @return int;
     */
    protected int getNoPlantingAcreage(Farm farm) {
        int farm_acreage = 0;
        int acreage = 0;
        try {
            farm_acreage = farm.getAcreage().intValue();
            List<Crops> list = CropService.getByFarm(farm);
            for (Crops crops1 : list) {
                acreage += crops1.getSeedNumber();
            }
        } catch (Exception e) {
        }
        return farm_acreage - acreage;
    }
}
