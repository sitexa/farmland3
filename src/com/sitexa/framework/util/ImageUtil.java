package com.sitexa.framework.util;

import com.sitexa.framework.Constants;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-24
 * Time: 19:19:12
 */
public class ImageUtil {

    private static final int W = 100;
    private static final int H = 75;
    private static final int WIDTH = 600;
    private static final float qlt = 1.0f;

    public ImageUtil() {
    }

    public static Image readImageFile(String filename) throws IOException {
        Image image;
        File source = new File(filename);
        image = ImageIO.read(source);
        return image;
    }

    public static Image readImageStream(File file) throws IOException {
        Image image;
        InputStream is = new BufferedInputStream(new FileInputStream(file));
        image = ImageIO.read(is);
        return image;
    }

    public static Image readImageURL(String address) throws IOException {
        Image image;
        URL url = new URL(address);
        image = ImageIO.read(url);
        return image;
    }

    public static void writeImage(Image img, String filename) throws IOException {

        try {
            BufferedImage image;
            image = (BufferedImage) img;
            OutputStream outPutStream = new FileOutputStream(filename);
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

    public static void writeImage(Image img, File file) throws IOException {
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
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

    public static BufferedImage marker(File file, File marker) throws IOException {
        try {
            BufferedImage image = (BufferedImage) readImageStream(file);
            try {
                BufferedImage mark = (BufferedImage) readImageStream(marker);
                image = resizeImage(image, WIDTH);
                return waterMarker(image, mark);
            } catch (Exception e1) {
                return marker(file, Constants.WATERMARKER);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
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


    public static BufferedImage abbrev(Image image) {
        BufferedImage img = (BufferedImage) image;
/*
        int w = img.getWidth();
        int h = img.getHeight();
        int x, y;
        if (w > 80 || h > 80) {
            if (w > h) {
                x = 80;
                y = x * h / w;
            } else {
                y = 80;
                x = y * w / h;
            }
        } else {
            return img;
        }
*/
        BufferedImage tag = new BufferedImage(W, H, BufferedImage.TYPE_INT_RGB);
        Graphics g = tag.getGraphics();
        g.drawImage(img, 0, 0, W, H, null);
        g.dispose();
        return tag;
    }

    public static void marshall(Serializable[] objects, String file) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(objects);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtil.close(out);
        }
    }

    public static Object[] unmarshall(String file) {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(file));
            Object[] objects = (Object[]) in.readObject();
            return objects;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            FileUtil.close(in);
        }
        return null;
    }

    public static byte[] getCompressedImage(BufferedImage image) {
        byte[] imageData = null;

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            imageData = baos.toByteArray();
        } catch (IOException ex) {
            imageData = null;
        }
        return imageData;
    }

    public static byte[] getCompressedImage(Image img) {
        byte[] imageData = null;
        try {
            BufferedImage image = (BufferedImage) img;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            imageData = baos.toByteArray();
        } catch (IOException ex) {
            imageData = null;
        }
        return imageData;
    }

    public static BufferedImage getDecompressedImage(byte[] imageData) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
            return ImageIO.read(bais);
        } catch (IOException ex) {
            return null;
        }
    }

    public static void deleteFromFileSystem(String filename) {
        System.out.println("delete image file");
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
    }
}
