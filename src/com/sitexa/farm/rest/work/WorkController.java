package com.sitexa.farm.rest.work;

import com.sitexa.farm.data.Faction;
import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.FarmOwner;
import com.sitexa.farm.data.Seed;
import com.sitexa.farm.service.*;
import com.sitexa.framework.util.ImageUtil;
import com.sitexa.post.service.CsaService;
import com.sitexa.post.service.FarmPostService;
import com.sitexa.user.data.Member;
import com.sitexa.user.service.MemberCreditService;
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
 * Date: 2010-7-26
 * Time: 21:29:35
 */
@SuppressWarnings("unchecked")
public class WorkController extends WorkAction {
    private static String TYPE_NEWS = "14";
    private static String TYPE_ACTION = "15";
    private static String TYPE_NOTICE = "16";
    private static String TYPE_PLAN = "17";
    private static String TYPE_PICK = "18";


    private static int CNT = 5;

    public HttpHeaders index() {
        System.out.println("WorkController.index");
        member = getProfile();
        if (member == null) {
            try {
                ServletActionContext.getResponse()
                        .sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
                return null;
            } catch (IOException ignored) {
            }
        }

        credit = MemberCreditService.getMemberCredit(member.getMemberId());

        farms = FarmService.getFarmByMember(member);
        List<FarmOwner> fos = FarmOwnerService.getFarms(member);
        for (FarmOwner farmOwner : fos) {
            farms.add(farmOwner.getFarm());
        }

        if (farms.size() > 0)
            farm = farms.get(0);

        if (farm != null) {
            FarmService.setScore(farm.getFarmId(), 1);

            farmings = FarmingService.getFarmingActive(farm, page);
            cropses.clear();
            cropses.addAll(farm.getCropses());
            seeds = SeedService.getSeeds(farm.getLand());
            factions = FactionService.getFactionsInUse(farm.getLand());
            friendFarms = FarmService.getFriendFarms(farm.getMember());

            visitors = FarmVisitService.getVisitorByFarm(farm, CNT);
            newPosts = FarmPostService.getNewPosts(farm, null, CNT);
//            newActions = FarmPostService.getNewPosts(farm, TYPE_ACTION, 1);
//            newNotices = FarmPostService.getNewPosts(farm, TYPE_NOTICE, 1);
//            newPlans = FarmPostService.getNewPosts(farm, TYPE_PLAN, 1);
            newPicks = FarmPostService.getNewPosts(farm, TYPE_PICK, CNT);
            newNotices = CsaService.getNewNotice(1);

            Set set = farm.getLand().getFarms();
            for (Object aSet : set) {
                Farm farm1 = (Farm) aSet;
                if (farm1.getMember() != null && farm1 != farm) neFarms.add(farm1);
            }

            faction = null;
        } else {
            return new DefaultHttpHeaders("nofarm");
        }
        return new DefaultHttpHeaders("index");
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders show() {
        System.out.println("WorkController.show");
        member = getProfile();
        if (member == null) {
            return index();
        }

        farm = FarmService.getById(id);

        if (farm != null) {
            System.out.println("farm.getFarmName() = " + farm.getFarmName());
            FarmService.setScore(farm.getFarmId(), 1);
            credit = MemberCreditService.getMemberCredit(farm.getMember().getMemberId());

            farms = FarmService.getFarmByMember(farm.getMember());
            List<FarmOwner> fos = FarmOwnerService.getFarms(farm.getMember());
            for (FarmOwner farmOwner : fos) farms.add(farmOwner.getFarm());
            String seedId = ServletActionContext.getRequest().getParameter("seedId");
            if (seedId != null && !"".equals(seedId)) {
                seed = SeedService.getById(seedId);
                farmings = FarmingService.getFarmingActive(farm.getFarmId(), seedId, page);
            } else {
                farmings = FarmingService.getFarmingActive(farm, page);
            }
            cropses.clear();
            cropses.addAll(farm.getCropses());
            seeds = SeedService.getSeeds(farm.getLand());
            factions = FactionService.getFactionsInUse(farm.getLand());
            friendFarms = FarmService.getFriendFarms(farm.getMember());

            visitors = FarmVisitService.getVisitorByFarm(farm, CNT);
            newPosts = FarmPostService.getNewPosts(farm, null, CNT);
//            newActions = FarmPostService.getNewPosts(farm, TYPE_ACTION, 1);
//            newNotices = FarmPostService.getNewPosts(farm, TYPE_NOTICE, 1);
//            newPlans = FarmPostService.getNewPosts(farm, TYPE_PLAN, 1);
            newPicks = FarmPostService.getNewPosts(farm, TYPE_PICK, CNT);
            newNotices = CsaService.getNewNotice(1);
            System.out.println("newPosts.size() = " + newPosts.size());
            System.out.println("newPicks = " + newPicks.size());
            System.out.println("newNotices = " + newNotices.size());
            Set set = farm.getLand().getFarms();
            for (Object aSet : set) {
                Farm farm1 = (Farm) aSet;
                if (farm1.getMember() != null && farm1 != farm) neFarms.add(farm1);
            }

            faction = null;
            System.out.println("WorkController.show end.");
        } else {
            return new DefaultHttpHeaders("nofarm");
        }
        return new DefaultHttpHeaders("index");
    }

    /**
     * 农庄操作，需要收集8个参数：farm,farmer,faction/plan,seed,paymode,amount,contents,starttime
     * 如果是委托操作，则需要经农场主确认并处理，从用户的帐户中扣除所需费用，最后置state为1。
     *
     * @return HttpHeaders;
     */
    public HttpHeaders operation() {
        System.out.println("WorkController.opertion");
        member = getProfile();
        if (member == null) return index();
        try {

            farming.setFarmer(member);
            farming.setStartTime(new Date());

            farm = FarmService.getById(farm.getFarmId());
            if (farm == null) throw new RuntimeException("农场为空.");
            farming.setFarm(farm);

            faction = FactionService.getByLandAndAction(farm.getLand().getLandId(), faction.getActionId());
            if (faction == null) throw new RuntimeException("操作为空.");
            farming.setFaction(faction);

            seed = SeedService.getById(seed.getSeedId());
            if (seed == null) throw new RuntimeException("品种为空.");
            farming.setSeed(seed);

            String mode = farming.getPaymode();
            String amount = farming.getAmount();

            if ("on".equals(mode) || "true".equals(mode)) {
                int amt = 0;
                try {
                    amt = Integer.parseInt(amount);
                } catch (Exception e1) {
                }
                farming.setPaymode("1");
                farming.setAmount(amt + "");
            } else {
                farming.setPaymode("0");
                farming.setAmount("0");
            }

            if (!haveRight()) {
                addActionError("你无权操作！");
                return show();
            }

            FarmService.setScore(farm.getFarmId(), 1);

            WorkBean bean = new WorkBean(farming);
            bean.execute();
            addActionMessage("操作完成。");

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("操作未成功：" + e.getMessage());
        }

        if (isFarmOwner()) {
            return index();
        } else {
            id = farm.getFarmId();
            return show();
        }
    }

    public HttpHeaders upload() {
        System.out.println("WorkController.upload");
        return new DefaultHttpHeaders("upload").disableCaching();
    }

    public HttpHeaders savePicture() {
        System.out.println("WorkController.savePicture");
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

    public void getSeedImage() {
        System.out.println("WorkController.getSeedImage");
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

    private boolean isFarmOwner() {

        if (member == null || farm == null) return false;

        if (member.getMemberId().equals(farm.getMember().getMemberId())) return true;

        return false;
    }

    private boolean acceptType(String type) {
        return ("image/gif".equalsIgnoreCase(type)) ||
                ("image/jpg".equalsIgnoreCase(type)) ||
                ("image/jpeg".equalsIgnoreCase(type)) ||
                ("image/pjpeg".equalsIgnoreCase(type)) ||
                ("image/bmp".equalsIgnoreCase(type)) ||
                ("image/png".equalsIgnoreCase(type));
    }


}
