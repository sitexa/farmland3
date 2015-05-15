/**
* @作者 leim
* @创建日期 2010-5-12
* @版本 V 1.0
*/ 
package com.sitexa.service;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import com.sitexa.framework.config.StrutsConfig;
import com.sitexa.framework.util.FileUtil;
import com.sitexa.framework.util.ImageUtil;

public class UpfileService {
    private static final String TEMP_DIR = "temp";
    
    public static String createFile(File file, String fileName){
    	String path = file.getParent() + File.separator + TEMP_DIR;
    	String newFilename = UUID.randomUUID().toString().replaceAll("-", "") + FileUtil.getExtention(fileName);
    	
    	File dstFile = new File(path + File.separator + newFilename);
    	try {
			FileUtils.copyFile(file, dstFile);
			file.deleteOnExit();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	//file.renameTo(dstFile); 与盘的格式有关：fat32不到到NTFS
    	
    	return newFilename;
    }
    
    public static byte[] getImage(String imageName){
    	byte[] imgData = null;
    	String path = StrutsConfig.getSaveDir() + File.separator + TEMP_DIR + File.separator;
    	File file = new File(path + File.separator + imageName);
		try {
			Image img = ImageUtil.abbrev(ImageIO.read(new FileInputStream(file)));
			imgData = ImageUtil.getCompressedImage(img);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return imgData;
    }
}
