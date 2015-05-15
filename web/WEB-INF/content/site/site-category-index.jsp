<%--
  User: xnpeng
  Date: 2009-5-8
  Time: 16:00:06
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><s:text name="root.title"><s:param>${site.name}</s:param></s:text>
    </title>
    <link href="<%=request.getContextPath()%>/style/site.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="header">
    <s:include value="../h.jsp"/>
</div>
<div id="mainwrapper">
    <div id="leftbar"><s:include value="site-menu.jsp"/></div>
    <div id="centerbar">
        <div class="box">
            <s:actionmessage/>
            <s:actionerror/>
        </div>
    </div>
    <div id="rightbar"><s:include value="site-right.jsp"/></div>
</div>
<div id="footer">
    <s:include value="../f.jsp"/>
</div>
</body>
</html>