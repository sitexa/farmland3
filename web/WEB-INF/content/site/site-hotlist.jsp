<%--
  User: xnpeng
  Date: 2009-8-1
  Time: 23:04:54
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="verify-v1" content="k259ptqdEx4rpDBG1K1KzYoCS/of2lbHViWkT8YuzFg=">
    <s:i18n name="keywords">
        <meta name="keywords" content="<s:text name='keywords'/>">
    </s:i18n>
    <title><s:text name="root.title"><s:param>热度社区</s:param></s:text>
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
            <div class="title">社区热度榜</div>
            <div class="content" style="padding-left:10px">
                <s:iterator value="hotSiteList" status="its">
                    ${its.index+1} <a href="<%=request.getContextPath()%>/post/post?siteId=${siteId}">${name}</a>
                    <s:if test="(#its.index+1)%5 == 0"><br></s:if>
                </s:iterator>
            </div>
        </div>
    </div>
    <div id="rightbar"><s:include value="site-right.jsp"/></div>
</div>
<div id="footer">
    <s:include value="../f.jsp"/>
</div>
</body>
</html>