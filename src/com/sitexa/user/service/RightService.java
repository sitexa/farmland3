package com.sitexa.user.service;

import com.sitexa.user.data.Position;
import com.sitexa.user.data.Right;
import com.sitexa.user.data.RightDAO;

import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-4-4
 * Time: 6:59:45
 */
public class RightService {
    public static Right getRight(String id) {
        RightDAO dao = new RightDAO();
        return dao.findById(id);
    }

    public static List<Right> getRightList() {
        RightDAO dao = new RightDAO();
        return dao.findAll();
    }

    public static List<Position> getRightPosition(Right right) {
        return null;
    }

    public static List<Right> getPositionRight(Position position) {
        return null;
    }
}
