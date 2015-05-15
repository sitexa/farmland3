package com.sitexa.user.service;

import com.sitexa.user.data.Position;
import com.sitexa.user.data.PositionDAO;

import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-4-4
 * Time: 7:10:16
 */
public class PositionService {

    public static Position getPosition(String id) {
        PositionDAO dao = new PositionDAO();
        return dao.findById(id);
    }

    public static List<Position> getPositionList() {
        PositionDAO dao = new PositionDAO();
        return dao.findAll();
    }

    public static void main(String[] args) {
        List list = getPositionList();
        for (int i = 0; i < list.size(); i++) {
            Position position = (Position) list.get(i);
            System.out.println("position.toString() = " + position.toString());
        }
    }

}
