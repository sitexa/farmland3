<%--
  User: xnpeng
  Date: 2009-5-3
  Time: 23:47:50
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        农庄管理
    </title>
    <link href="<%=request.getContextPath()%>/style/index.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="header">
    <s:include value="../header.jsp"/>
</div>
<div id="mainwrapper">
    <div id="leftbar" class="leftcol"><s:include value="work-left.jsp"/></div>
    <div id="centerbar" class="centercol">
        <div class="box">
            <div class="title">修改作物</div>
            <div class="content">
                <s:form id="frmFarm" theme="simple" action="%{#request.contextPath}/admin/work">
                    <s:hidden id="farmId" name="farm.farmId"/>
                    <s:hidden name="crops.cropsId"/>
                    作物编号：${crops.cropsId}<br/>
                    作物名称：${crops.seed.seedName}<br/>
                    种植面积：${crops.seedNumber}平方米<br/>
                    回复内容:<br/><s:textarea name="description" cols="60" rows="4"></s:textarea><br>
                    <s:submit cssClass="formbutton" value="提交" method="update"/>
                </s:form>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    <div class="clear"></div>
</div>
<div id="footer">
    <s:include value="../footer.jsp"/>
</div>
</body>
</html>