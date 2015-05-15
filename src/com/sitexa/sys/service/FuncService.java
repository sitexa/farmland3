package com.sitexa.sys.service;

import com.sitexa.sys.data.Func;
import com.sitexa.sys.data.FuncDAO;

/**
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-20
 * Time: 16:37:07
 */
public class FuncService {


    public static void main(String[] args) {
        Func vo = new Func("1", "manage site", "site/site");
        FuncService fs = new FuncService();
//        fs.create(vo);
        fs.delete("1");

    }

    public FuncService() {
    }

    public Func getById(String id) {
        FuncDAO dao = new FuncDAO();
        return dao.findById(id);
    }

    public void create(Func func) {
        FuncDAO dao = new FuncDAO();
        dao.save(func);
    }

    public void delete(String id) {
        FuncDAO dao = new FuncDAO();
        Func vo = dao.findById(id);
        dao.delete(vo);
    }

    public void update(Func func) {
        FuncDAO dao = new FuncDAO();
        dao.save(func);
    }


}
