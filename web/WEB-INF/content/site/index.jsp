<%--
  User: xnpeng
  Date: 2009-5-22
  Time: 23:47:49
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/master.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="header">
    <s:include value="../h.jsp"/>
</div>
<div id="mainwrapper">
    <div id="leftbar" class="leftcol"></div>
    <div id="centerbar">
        <div class="box">
            <div class="title">出错了</div>
            <div class="content">
                <p style="color:red">
                    对不起,您的操作出错了! </p>
                <s:actionmessage cssClass="message"/>
                <s:actionerror cssClass="error"/>
            </div>
        </div>
    </div>
    <div id="rightbar" class="rightcol"></div>
</div>
<div id="footer">
    <s:include value="../f.jsp"/>
</div>
</body>
</html>