package com.sitexa.farm.service;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.FarmOwner;
import com.sitexa.farm.data.FarmOwnerDAO;
import com.sitexa.sys.Sequence;
import com.sitexa.user.data.Member;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-30
 * Time: 22:02:26
 */
public class FarmOwnerService {

    private static Log log = LogFactory.getLog(FarmOwnerService.class);

    public static FarmOwner getById(String id) {
        if (id == null || "".equals(id)) return null;

        FarmOwnerDAO dao = new FarmOwnerDAO();
        return dao.findById(id);
    }

    @SuppressWarnings("unchecked")
    public static List<FarmOwner> getFarms(Member member) {
        List<FarmOwner> result = new ArrayList<FarmOwner>();
        if (member == null) return result;
        FarmOwnerDAO dao = new FarmOwnerDAO();
        result = dao.findByProperty("member", member);
        return result;
    }

    public static void create(Farm farm, Member member) {
        if (farm == null || member == null) return;

        FarmOwnerDAO dao = new FarmOwnerDAO();
        FarmOwner fo = dao.findByFarmAndMember(farm, member);
        if (fo != null) return;

        fo = new FarmOwner();
        fo.setFarm(farm);
        fo.setMember(member);
        fo.setJoinDate(new Date());
        fo.setOwnerId(Sequence.getId());

        dao.save(fo);
    }

    public static void create(FarmOwner fo) {
        System.out.println("FarmOwnerService.create");

        if (fo == null) return;
        FarmOwnerDAO dao = new FarmOwnerDAO();
        FarmOwner fo2 = dao.findByFarmAndMember(fo.getFarm(), fo.getMember());
        if (fo2 != null) return;
        System.out.println("FarmOwnerService.create 2");
        fo.setOwnerId(Sequence.getId());
        fo.setJoinDate(new Date());
        dao.save(fo);
        System.out.println("FarmOwnerService.create 3");
    }

    public static void delete(FarmOwner fo) {
        if (fo == null || "".equals(fo.getOwnerId())) return;
        FarmOwnerDAO dao = new FarmOwnerDAO();
        FarmOwner fo2 = dao.findById(fo.getOwnerId());
        dao.delete(fo2);
    }

    public static void update(FarmOwner fo) {
        if (fo == null || "".equals(fo.getOwnerId())) return;
        FarmOwnerDAO dao = new FarmOwnerDAO();
        FarmOwner fo2 = dao.findById(fo.getOwnerId());
        fo2.setRemark(fo.getRemark());
        dao.update(fo2);
    }
}
