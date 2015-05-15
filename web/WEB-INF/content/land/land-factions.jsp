<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>
       <s:text name="root.title">
            <s:param>${land.landName}</s:param>
        </s:text>
    </title>
    <link href="<%=request.getContextPath()%>/land/land.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
</head>
<body>
<div id="container">
    <div id="header">
        <s:include value="../h.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="leftbar" class="leftcol">
            <s:include value="land-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
                <div class="t">
                    <div class="t-t">
                        <a href="<%=request.getContextPath()%>/land/land/${land.landId}">${land.landName}</a>
                            > 操作服务
                    </div>
                </div>
                <s:if test="factions.size>0">
                    <div class="content">
                        <s:iterator value="factions" status="its">
                            <div style="border-bottom:1px solid #ccf;margin:5px;padding:4px;">
                                <a href="<%=request.getContextPath()%>/land/faction/${factionId}">
                                <img src="<%=request.getContextPath()%>/image?type=service&picId=${actionId}" style="float:left;margin:4px;"/>
                                </a>
                                <a href="<%=request.getContextPath()%>/land/faction/${factionId}"><b>${actionName}</b></a><br>
                                <b>服务价格：</b>￥${expense}<br/>
                                <b>服务说明：</b>${contents}<br/>
                            </div>
                        </s:iterator>
                    </div>
                    <div class="clear"></div>
                </s:if>
                <s:include value="../paging.jsp"/>
                <s:form id="pageFrm" theme="simple" action="%{#request.contextPath}/land/land/%{land.landId}/factions"
                        method="get" cssStyle="display: none">
                    <s:hidden id="page.current" name="page.current" value="1"/>
                </s:form>
            </div>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="land-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>