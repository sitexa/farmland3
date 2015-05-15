package com.sitexa.framework.util;

import com.sitexa.framework.exception.BaseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;

/**
 * @class: TODO
 */
public class FileUtil {

    private static Log logger = LogFactory.getLog(FileUtil.class);
    private static final long serialVersionUID = 572146812454l;
    private static final int BUFFER_SIZE = 1024 * 16;

    public static void copyFile(File srcFile, File dstFile) throws BaseException {
        if (srcFile == null || dstFile == null)
            BaseException.threw("FileUtil.copyFile: srcFile is null or dstFile is null!");

        if (!dstFile.exists()) {
            try {
                dstFile.getParentFile().mkdirs();
                dstFile.createNewFile();
            } catch (Exception e) {
                BaseException.threw(e);
            }
        }
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(srcFile), BUFFER_SIZE);
            out = new BufferedOutputStream(new FileOutputStream(dstFile), BUFFER_SIZE);
            byte[] buffer = new byte[BUFFER_SIZE];
            while (in.read(buffer) > 0) {
                out.write(buffer);
            }
        } catch (Exception e) {
            BaseException.threw(e);
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error(FileUtil.class, e);
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error(FileUtil.class, e);
                }
            }
        }
    }

    public static String getExtention(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos);
    }

    public static void close(Closeable is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                logger.error(FileUtil.class, e);
            }
        }
    }

    public static void closeAndDelete(Closeable is, File file) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                logger.error(FileUtil.class, e);
            }
        }
        if (file != null && file.exists())
            file.delete();
    }

    public static String dirTree(File file) {
        StringBuffer sb = new StringBuffer();
        if (file.isDirectory()) {
            sb.append("<tree id='").append(file.getName()).append("'>");
            File[] files = file.listFiles();
            for (File file1 : files) {
                sb.append(dirTree(file1));
            }
            sb.append("</tree>");
        } else if (file.isFile()) {
            sb.append("<item id='").append(file.getName()).append("'/>");
        }
        return sb.toString();
    }

}

