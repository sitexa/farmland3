package com.sitexa;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;

public class Test {

    private static int WIDTH = 800;
    private static final float qlt = 1.0f;

    public static void main(String[] args) {
        make();
    }

    private static void make() {
        String markfile = "E:\\TEMP\\logo.JPG";
        String filename = "E:\\TEMP\\dsg.JPG";
        String filename2 = "E:\\TEMP\\";
        try {
            File f = new File(filename);
            File mark = new File(markfile);
            Image image = marker(f, mark);
            filename2 += new Date().getTime() + ".jpg";
            writeImage(image, filename2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage marker(File file, File mark) throws IOException {
        try {
            BufferedImage image = (BufferedImage) readImageStream(file);
            BufferedImage marker = (BufferedImage) readImageStream(mark);
            image = resizeImage(image, WIDTH);
            return waterMarker(image, marker);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static BufferedImage marker(File file, String text) throws IOException {
        try {
            BufferedImage image = (BufferedImage) readImageStream(file);
            image = resizeImage(image, WIDTH);
            return waterMarker(image, text);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static Image readImageStream(File file) throws IOException {
        Image image;
        InputStream is = new BufferedInputStream(new FileInputStream(file));
        image = ImageIO.read(is);
        return image;
    }

    public static BufferedImage resizeImage(Image img, int width) {
        BufferedImage image = (BufferedImage) img;
        int orig_width = image.getWidth();
        int orig_height = image.getHeight();
        if (orig_width < width) width = orig_width;
        int height = width * orig_height / orig_width;
        BufferedImage image2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image2.getGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        return image2;
    }

    public static BufferedImage waterMarker(Image img, Image mark) {
        BufferedImage mk = (BufferedImage) mark;
        BufferedImage image = (BufferedImage) img;
        Graphics2D g2d = image.createGraphics();


        AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        g2d.setComposite(alpha);

        int x = (image.getWidth() - (int) mk.getWidth());
        int y = (image.getHeight() - (int) mk.getHeight());

        g2d.drawImage(mk, x, y, null);

        g2d.dispose();

        return image;
    }

    public static BufferedImage waterMarker(Image img, String text) {
        BufferedImage image = (BufferedImage) img;
        Graphics2D g2d = image.createGraphics();

        AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        g2d.setComposite(alpha);

        g2d.setColor(Color.white);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setFont(new Font("Arial", Font.BOLD, 30));

        FontMetrics fontMetrics = g2d.getFontMetrics();
        Rectangle2D rect = fontMetrics.getStringBounds(text, g2d);

        int centerX = (image.getWidth() - (int) rect.getWidth()) / 2;
        int centerY = (image.getHeight() - (int) rect.getHeight());

        g2d.drawString(text, centerX, centerY);

        g2d.dispose();

        return image;
    }

    public static void writeImage(Image img, String filename) throws IOException {
        File file = new File(filename);
        try {
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            BufferedImage image;
            image = (BufferedImage) img;
            OutputStream outPutStream = new FileOutputStream(file);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outPutStream);
            JPEGEncodeParam jprm = JPEGCodec.getDefaultJPEGEncodeParam(image);
            jprm.setQuality(qlt, true);
            encoder.encode(image, jprm);
            outPutStream.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
        catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
