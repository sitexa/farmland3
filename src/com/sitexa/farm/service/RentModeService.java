package com.sitexa.farm.service;

import com.sitexa.farm.data.Land;
import com.sitexa.farm.data.RentMode;
import com.sitexa.farm.data.RentModeDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Oscar Peng, xnpeng@163.com
 * Date: 11-1-24
 * Time: 下午11:39
 */
@SuppressWarnings("unchecked")
public class RentModeService {

    public static void main(String[] args) {
        String s = getPriceById("2");
        System.out.println("s = " + s);
    }

    public static RentMode getById(String id) {
        RentModeDAO dao = new RentModeDAO();
        return dao.findById(id);
    }

    public static List<RentMode> getByLand(Land land) {
        List<RentMode> result = new ArrayList<RentMode>();
        if (land == null) return result;
        RentModeDAO dao = new RentModeDAO();
        return dao.findByLand(land);
    }

    public static String getPriceById(String modeId) {
        String price = null;
        RentMode mode = getById(modeId);
        if (mode != null) price = mode.getRentPrice() + "";
        return price;
    }
}
