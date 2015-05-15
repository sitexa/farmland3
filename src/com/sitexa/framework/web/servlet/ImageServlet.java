package com.sitexa.framework.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-24
 * Time: 10:13:38
 */
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("image/jpeg");

        BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);

        Graphics g = bufferedImage.createGraphics();
        g.setColor(Color.blue);
        g.fillOval(0, 0, 199, 199);
        g.dispose();

        ImageIO.write(bufferedImage, "jpg", res.getOutputStream());

    }
}
