<%--
  User: xnpeng
  Date: 2009-4-16
  Time: 17:35:03
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/index.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="container">
    <div id="header">
        <s:include value="../h.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="leftbar" class="leftcol">
            <s:include value="post-menu.jsp"/>
        </div>
        <div id="centerbar">
            <div class="box">
                <div class="title">出错了</div>
                <div class="content">
                    <s:actionerror cssStyle="color:red"/>
                </div>
            </div>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="post-right.jsp"/>
        </div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>