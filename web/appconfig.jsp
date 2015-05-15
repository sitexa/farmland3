<%@ page import="com.sitexa.framework.config.AppConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head><title>app config</title>
</head>
<body>
<p><%=request.getRequestURI()%><br/>
  <br>
app.name:<%=AppConfig.getProperty("app.name")%> <br>
imgurl:<%=AppConfig.getProperty("imgurl")%> <br>
</p>
<p>
  <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="32" height="32" title="´ÓÌïÒ°µ½²Í×À">
    <param name="movie" value="media/farm.flv">
    <param name="quality" value="high">
    <embed src="media/farm.flv" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="32" height="32"></embed>
  </object>
</p>
</body>
</html>