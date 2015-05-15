/**
* @作者 leim
* @创建日期 2010-5-12
* @版本 V 1.0
*/ 
package com.sitexa.rest;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.sitexa.action.UpfileAction;
import com.sitexa.framework.util.FileUtil;
import com.sitexa.service.UpfileService;

public class UpfileController extends UpfileAction{
	private static Log log = LogFactory.getLog(UpfileController.class);
	
    public void index() {
    	System.out.println("UpfileController.index");
    	HttpServletRequest request = ServletActionContext.getRequest();
    	String type = request.getParameter("type");
        
    	String newFilename = UpfileService.createFile(upload.get(0), uploadFileName.get(0));
        try {
        	ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().print(newFilename);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
