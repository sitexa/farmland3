package com.sitexa.framework.web.servlet;

import com.sitexa.framework.web.listener.UploadListener;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-4-22
 * Time: 15:37:44
 */
public class TestUpload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // create file upload factory and upload servlet
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        // set file upload progress listener
        UploadListener listener = new UploadListener();
        HttpSession session = request.getSession();
        session.setAttribute("LISTENER", listener);
        // upload servlet allows to set upload listener
        upload.setProgressListener(listener);

        List items = null;
        FileItem fileItem = null;
        String filename = null;
        try {
            // iterate over all uploaded files
            items = upload.parseRequest(request);
            for (Iterator i = items.iterator(); i.hasNext();) {
                fileItem = (FileItem) i.next();
                if (!fileItem.isFormField()) {
                    if (fileItem.getSize() > 0) {
                        System.out.println("TestUpload.doPost");
                        // code that handle uploaded fileItem
                        // don't forget to delete uploaded files after you done with them! Use fileItem.delete();
                    }
                }
            }
            // indicate that the upload was successfull
            response.getWriter().write("upload successful");
        } catch (FileUploadException e) {
            response.getWriter().write(e.getMessage());
        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
        } finally {
            session.removeAttribute("LISTENER");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        HttpSession session = null;
        UploadListener listener = null;
        long contentLength = 0;

        if (((session = request.getSession()) == null) ||
                ((listener = (UploadListener) session.getAttribute("LISTENER")) == null) ||
                ((contentLength = listener.getContentLength()) < 1)) {
            out.write("");
            out.close();
            return;
        }

        response.setContentType("text/html");
        long percentComplite = ((100 * listener.getBytesRead()) / contentLength);
        out.print(percentComplite);
        out.close();
    }
}
