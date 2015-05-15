package com.sitexa.rest;

import com.sitexa.framework.util.FileUtil;
import org.apache.struts2.ServletActionContext;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class TestController {

    public Connection getConnection() {
        Connection conn = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/sitexaDB");
            conn = ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void testDB() {
        String sql = "select * from t_category";
        String result = "";
        Statement stm;
        Connection conn = getConnection();
        try {
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String s = rs.getString(1);
                result += s + "<br/>";
            }
            ServletActionContext.getResponse().getOutputStream().print(result);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawOval() {
        HttpServletResponse res = ServletActionContext.getResponse();
        res.setContentType("image/jpeg");

        BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);

        Graphics g = bufferedImage.createGraphics();
        g.setColor(Color.blue);
        g.fillOval(0, 0, 199, 199);
        g.dispose();

        try {
            ImageIO.write(bufferedImage, "jpg", res.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void codeImage() throws IOException {
        int width = 200, height = 100;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);

        g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 40 * i + 20, 55);
        }
        g.dispose();

        ImageIO.write(image, "JPEG", ServletActionContext.getResponse().getOutputStream());

    }

    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public void helloWorld() throws IOException {
        ServletActionContext.getResponse().getOutputStream().print("Hello World!");
    }

    public void compute() throws IOException {
        int x = Integer.parseInt(ServletActionContext.getRequest().getParameter("x"));
        int y = Integer.parseInt(ServletActionContext.getRequest().getParameter("y"));
        String op = ServletActionContext.getRequest().getParameter("op");

        if (op.equals("+")) {
            ServletActionContext.getResponse().getOutputStream().print(x + y);
        } else if (op.equals("-")) {
            ServletActionContext.getResponse().getOutputStream().print(x - y);
        } else if (op.equals("*")) {
            ServletActionContext.getResponse().getOutputStream().print(x * y);
        } else if (op.equals("/")) {
            ServletActionContext.getResponse().getOutputStream().print(x / y);
        } else {
            ServletActionContext.getResponse().getOutputStream()
                    .print("Hi,what are you doing?");
        }
    }

    public void dir() throws IOException {
        File file = new File("E:/TEMP");
        String xml = "<?xml version='1.0' encoding='utf-8'?>";
        xml += FileUtil.dirTree(file);
        ServletActionContext.getResponse().getOutputStream().print(xml);
    }
}
