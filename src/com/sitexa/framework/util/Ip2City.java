package com.sitexa.framework.util;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * User: xnpeng
 * Date: 2009-8-9
 * Time: 0:38:16
 */
public class Ip2City {

    static String CNC = "网通";
    static String CMCC = "移动";
    static String CRTC = "铁通";
    static String CERNET = "CERNET";
    static String CHINANET = "电信";
    static String UNICOM = "联通";
    static String OTHERNET_GROUP = "";

    static String service = "http://www.ikaka.com/ip/ipjs.asp?ip=";

    public static void main(String[] args) {
        String ip = "222.247.112.98";
        ip = args[0];
        String cityname = getCity(ip);
        System.out.println("cityname = " + cityname);
    }

    /**
     * 服务IP地址已经被ikaka屏蔽,因此连接不能成功,获取不到地址.
     *
     * @param ip;
     * @return cityname;
     */
    public static String getCity(String ip) {
        //todo...
        if (true) return null;
        String c = gopher(ip);
        if (c == null) return null;
        return extract(c, ip);
    }

    private static String extract(String content, String ip) {
        String result = null;
        if (content == null) return null;
        String p = "查询IP：" + ip + " &nbsp;地理位置：";
        int i = content.indexOf(p);
        int len = p.length();
        int j = content.indexOf("</span>", i + 1);
        if (i > 0 && j > 0)
            result = content.substring(i + len, j);
        if (result != null) {
            if (result.indexOf(CNC) > 0) {
                result = result.substring(0, result.indexOf(CNC));
            } else if (result.indexOf(CHINANET) > 0) {
                result = result.substring(0, result.indexOf(CHINANET));
            } else if (result.indexOf(CRTC) > 0) {
                result = result.substring(0, result.indexOf(CRTC));
            } else if (result.indexOf(CERNET) > 0) {
                result = result.substring(0, result.indexOf(CERNET));
            } else if (result.indexOf(CHINANET) > 0) {
                result = result.substring(0, result.indexOf(CHINANET));
            } else if (result.indexOf(CMCC) > 0) {
                result = result.substring(0, result.indexOf(CMCC));
            }
        }
        return result;
    }

    private static String gopher(String ip) {
        String result = null;
        try {
            URL url = new URL(service + ip);
            String host = url.getHost();
            int port = url.getPort();
            if (port == -1) port = 80;
            String filename = url.getFile();

            Socket socket = new Socket(host, port);
            InputStream from_server = socket.getInputStream();
            PrintWriter to_server = new PrintWriter(socket.getOutputStream());
            to_server.print("GET " + filename + "\n\n");
            to_server.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(from_server, "gbk"));
            StringBuffer buf = new StringBuffer();
            String tmp = br.readLine();
            while (tmp != null) {
                buf.append(tmp);
                tmp = br.readLine();
            }
            result = buf.toString();
            socket.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
