<%--
  User: xnpeng
  Date: 2009-5-8
  Time: 15:58:08
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/site.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="container">
	<div id="header">
	    <s:include value="../h.jsp"/>
	</div>
	<div id="mainwrapper">
	    <div id="leftbar">
	    	<s:include value="site-menu.jsp"/>
		</div>
	    <div id="centerbar" class="centercol">
	        <div class="box">
	            <s:actionmessage/>
	            <s:actionerror/>
	        </div>
	    </div>
	    <div id="rightbar"><s:include value="site-right.jsp"/></div>
	</div>
	<div class="clear"></div>
	<div id="footer">
	    <s:include value="../f.jsp"/>
	</div>
</div>
</body>
</html>