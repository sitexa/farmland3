package com.sitexa.rest;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-10-23
 * Time: 23:31:33
 */
public class RateController extends ActionSupport {
    protected List<File> upload = new ArrayList<File>();
    protected List<String> uploadFileName = new ArrayList<String>();
    protected List<String> uploadContentType = new ArrayList<String>();

    public List<File> getUpload() {
        return upload;
    }

    public void setUpload(List<File> upload) {
        this.upload = upload;
    }

    public List<String> getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(List<String> uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public List<String> getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(List<String> uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public void vote() {
        String rating = ServletActionContext.getRequest().getParameter("rating");
        if (rating != null) {
            String sess = (String) ServletActionContext.getRequest().getSession().getAttribute("rate");
            if (sess == null)
                sess = rating;
            else
                sess += "," + rating;
            ServletActionContext.getRequest().getSession().setAttribute("rate", sess);
            String[] r = sess.split(",");
            int t = 0;
            for (String s : r) {
                int si = Integer.parseInt(s);
                t += si;
            }
            int c = r.length;
            float a = (float) t / c;

            String s = "<ratings><average>" + a + "</average><count>" + c + "</count></ratings>";
            try {
                ServletActionContext.getResponse().setHeader("Content-type", "text/xml");
                ServletActionContext.getResponse().getOutputStream().print(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadFile() {
        System.out.println("RateController.uploadFile");
        System.out.println("upload.size() = " + upload.size());
        if (upload.size() > 0) {
            System.out.println("###1");
            for (int i = 0; i < upload.size(); i++) {
                System.out.println("###2");

                try {
                    //todo upload cannot convert to file,
                    File file = upload.get(i);
                    System.out.println("###3");
                    File f = new File("e:/temp/x");
                    System.out.println("###4");
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
                    out.writeObject(in.readObject());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (RuntimeException re) {
                    re.printStackTrace();
                }
            }
        }
    }
}
