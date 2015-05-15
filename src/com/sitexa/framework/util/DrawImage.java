package com.sitexa.framework.util;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.w3c.dom.css.Rect;

/**
 * 
 * @author leim
 * @category 编辑图片(在原有图片基础上)
 * 
 */
public class DrawImage {
	private BufferedImage buffImage = null;
	private Graphics2D g = null;
	public DrawImage(File imageFile){
		try {
			buffImage = ImageIO.read(imageFile);
			g = buffImage.createGraphics();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param TypePoing
	 * @param boundWidth
	 * @param boundColor
	 * @param fillColor
	 */

	public void drawImageByTP(String TypePoints, float boundWidth, Color boundColor, Color fillColor)
	{
		try  
	    {   
			List list = this.getParametersList(TypePoints);
			String type = list.get(0).toString();
			if(type.toUpperCase().equals("POLY")){
				int xPoints[] = (int[])list.get(1);
	    		int yPoints[] = (int[])list.get(2);
	    		int nPoints = Integer.parseInt(list.get(3).toString());
	    		this.drawPoly(xPoints, yPoints, nPoints, boundWidth, boundColor, fillColor);
			}else if(type.toUpperCase().equals("CIRCLE")){
				int xPoint = Integer.parseInt(list.get(1).toString());
	    		int yPoint = Integer.parseInt(list.get(2).toString());
	    		int radii = Integer.parseInt(list.get(3).toString());
	    		this.drawCircle(xPoint, yPoint, radii, boundWidth, boundColor, fillColor);
			}else if(type.toUpperCase().equals("RECT")){
				int x = Integer.parseInt(list.get(1).toString());
	    		int y = Integer.parseInt(list.get(2).toString());
				int width = Integer.parseInt(list.get(3).toString());
	    		int height = Integer.parseInt(list.get(4).toString());
	    		this.drawRect(x, y, width, height, boundWidth, boundColor, fillColor);
			}
	    } catch (Exception e)   
	    {   
	        System.out.println(e.getMessage());   
	    }   
	  
	}
	
	/**
	 * 画多边开
	 * @param xPoints		X坐标[]
	 * @param yPoints		Y坐标[]
	 * @param nPoints		坐标数
	 * @param boundWidth	边框宽度	
	 * @param boundColor	边框颜色
	 * @param fillColor		填充颜色		说明:如果颜色为("" || null)则不填充 
	 */
	public void drawPoly(int[] xPoints, int[] yPoints, int nPoints, float boundWidth, Color boundColor, Color fillColor)
	{
		try  
	    {   
	        //g.setBackground(Color.WHITE);   
	        g.setColor(boundColor);   
	        g.setStroke(new BasicStroke(boundWidth));//线宽
	        
	        Polygon polygon = new Polygon(xPoints,yPoints, nPoints);
	        //画边框
	        g.setColor(boundColor);
	        g.drawPolygon(polygon);
	        //填充
	        if(fillColor!=null){
	        	g.setColor(fillColor);
	        	g.fillPolygon(polygon);
	        }
	        //g.dispose();   
	    } catch (Exception e)   
	    {   
	        System.out.println(e.getMessage());   
	    }   
	  
	}
	public void drawRect(int x, int y, int width, int height, float boundWidth, Color boundColor, Color fillColor)
	{
		try  
	    {   
	        //g.setBackground(Color.WHITE);   
	        g.setColor(boundColor);   
	        g.setStroke(new BasicStroke(boundWidth));//线宽
	        
	        Rectangle rect = new Rectangle(x, y, width, height);
	        
	        //画边框
	        g.setColor(boundColor);
	        g.draw(rect);
	        //填充
	        if(fillColor!=null){
	        	g.setColor(fillColor);
	        	g.fill(rect);
	        }
	        //g.dispose();   
	    } catch (Exception e)   
	    {   
	        System.out.println(e.getMessage());   
	    }   
	  
	}
	
	public void drawCircle(int xPoint, int yPoint, int radii, float boundWidth, Color boundColor, Color fillColor)
	{
		xPoint-=radii;
		yPoint-=radii;
		g.setColor(boundColor);   
        g.setStroke(new BasicStroke(boundWidth));//线宽
        
        //画边框
        g.setColor(boundColor);
        g.drawArc(xPoint, yPoint, radii*2, radii*2, 0, 360);
        //填充
        if(fillColor!=null){
        	g.setColor(fillColor);
        	g.fillArc(xPoint, yPoint, radii*2, radii*2, 0, 360);
        }
	}
	public BufferedImage getImage() 
	{
		g.dispose();
		buffImage.flush();   
	    return buffImage;   
	}
	
	/**  
	* 生成新图片到本地  
	*/  
	public void writeImageLocal(String newImage)   
	{   
	    if (newImage != null && buffImage != null)   
	    {   
	        try  
	        {   
	            File outputfile = new File(newImage);   
	            ImageIO.write(buffImage, "jpg", outputfile);  
	            g.dispose();
	            System.out.println(newImage); 
	        } catch (IOException e)   
	        {   
	            System.out.println(e.getMessage());   
	        }   
	    }   
	}  

	/**
	 * @author leim 2009.1119
	 * getParametersList			处理参数字符串
	 * @param str					参数字符串
	 * @return	ParametersList		处理好的参数列表
	 */
	public List getParametersList(String str){
		List ParametersList = null;
		String type = str.substring(0, str.indexOf(":"));
		String ParametersStr = str.substring(str.indexOf(":")+1, str.length());
		
		if(type != null && !"".equals(type.trim())){
			if("POLY".equals(type.trim().toUpperCase())){
				ParametersList = this.getPolyParametersList(type,ParametersStr);
			}else if("CIRCLE".equals(type.trim().toUpperCase())){
				ParametersList = this.getCircleParametersList(type,ParametersStr);
			}else if("RECT".equals(type.trim().toUpperCase())){
				ParametersList = this.getRectParametersList(type,ParametersStr);
			}
		}
		return ParametersList;
	}
	/**
	 * @author leim 2009.11.19
	 * getPolyPointList			根据type,ParametersStr处理出矩形所基本参数
	 * @param type				图形类型
	 * @param ParametersStr		参数字符串	(格式如:"123,213,546")
	 * @return ParametersList[4]{类型,X坐标[],Y坐标[],坐标数}	
	 */
	@SuppressWarnings("unchecked")
	public List getPolyParametersList(String type,String ParametersStr){
		List ParametersList = null;
		String[] points = ParametersStr.split(",");
		if(points != null && points.length >=6){		//画多边形至少三个点
			ParametersList = new ArrayList();
			int[] xPoints = new int[points.length/2];
			int[] yPoints = new int[points.length/2];
			ParametersList.add(type);
			for(int i=0; i<points.length/2; i++){
				xPoints[i] = Integer.parseInt(points[i*2]);
				yPoints[i] = Integer.parseInt(points[i*2+1]);
			}
			ParametersList.add(xPoints);
			ParametersList.add(yPoints);
			ParametersList.add(points.length/2);
		}
		return ParametersList;
	}
	/**
	 * @author leim 2009.11.19
	 * getPolyPointList			根据type,ParametersStr处理出圆形所基本参数
	 * @param type				图形类型
	 * @param ParametersStr		参数字符串	(格式如:"123,213,546")
	 * @return ParametersList[4]{类型,X坐标,Y坐标,半径}	
	 */
	@SuppressWarnings("unchecked")
	public List getCircleParametersList(String type,String ParametersStr){
		List ParametersList = null;
		String[] parameters = ParametersStr.split(",");
		if(parameters != null && parameters.length == 3){		//圆只有三个参数
			ParametersList = new ArrayList();
			ParametersList.add(type);
			ParametersList.add(Integer.parseInt(parameters[0]));
			ParametersList.add(Integer.parseInt(parameters[1]));
			ParametersList.add(Integer.parseInt(parameters[2]));
		}
		return ParametersList;
	}
	
	@SuppressWarnings("unchecked")
	public List getRectParametersList(String type,String ParametersStr){
		List ParametersList = null;
		String[] parameters = ParametersStr.split(",");
		if(parameters != null && parameters.length == 4){		//矩形只有三个参数
			ParametersList = new ArrayList();
			ParametersList.add(type);
			ParametersList.add(Integer.parseInt(parameters[0]));
			ParametersList.add(Integer.parseInt(parameters[1]));
			ParametersList.add(Integer.parseInt(parameters[2])-Integer.parseInt(parameters[0]));
			ParametersList.add(Integer.parseInt(parameters[3])-Integer.parseInt(parameters[1]));
		}
		return ParametersList;
	}
	
	/**
	 * @author leim 2009.11.19
	 * 验证字符串是否为数字
	 * @param str	
	 * @return boolean
	 */
	public boolean testInt(String str){
		String regEx = "^\\d+$";	//数值:"[0-9]+[\\.]?[0-9]+$"
        return Pattern.compile(regEx).matcher(str).find();
	}
	
	/**
	 * @author leim 2009.11.19
	 * 验证字符串是否为以","分隔的数字(格式如:"123,213,546")
	 * @param str	
	 * @return boolean
	 */
	public boolean testIntStr(String str){
		String regEx = "^\\d+(,\\d+)*$";	//数值:"[0-9]+[\\.]?[0-9]+$"
        return Pattern.compile(regEx).matcher(str).find();
	}
	public static boolean testParamater(String str){
		boolean  right = false;
		String regPoly = "^(poly:)(\\d+,\\d+)(,\\d+,\\d+){2,}$";
		String  regCircle = "^(circle:)\\d+(,\\d+){2}$";
		String regRect = "^(rect:)(\\d+,\\d+)(,\\d+,\\d+)$";
		right = Pattern.compile(regPoly,Pattern.CASE_INSENSITIVE).matcher(str).find()
				|| Pattern.compile(regCircle,Pattern.CASE_INSENSITIVE).matcher(str).find()
				|| Pattern.compile(regRect,Pattern.CASE_INSENSITIVE).matcher(str).find();
		return right;
	}
	//---------------------------相关方法-----------------------------------
    /**
     * @param file
     * @param path
     * @param fileName
     * @author leim 2009.11.23
     * 保存文件
     */
    public static void saveFile(File file, String path, String fileName) {
        if (file != null && file.isFile() && file.exists()) {
            try {
                File upLoadFile = new File(path, fileName);
                if (!upLoadFile.getParentFile().exists()) {
                    upLoadFile.getParentFile().mkdirs();
                }
                InputStream is = new FileInputStream(file);
                OutputStream os = new FileOutputStream(upLoadFile);
                byte[] buffer = new byte[1024];
                int length = 0;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, buffer.length);
                }
                os.close();
                is.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
