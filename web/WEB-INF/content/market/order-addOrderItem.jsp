<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title> 购买商品 </title>
    <link href="<%=request.getContextPath()%>/style/index.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="container">
    <div id="header">
        <s:include value="../h.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="leftbar" class="leftcol">
            <s:include value="order-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
                <div class="title">
                    商品内容
                </div>
                <div class="content">
                    <s:form id="frmOrderItem" theme="simple" action="%{#request.contextPath}/market/order" method="post">
                        <s:hidden name="objectId"/><s:hidden name="orderItem.itemId"/>
                        商品分类：<s:if test="objectId==OBJECT_FARM">农庄</s:if> <br>
                        商品编号：<s:property value="orderItem.itemId"/><br>
                        商品名称：<s:property value="orderItem.itemName"/><br>
                        商品价格：<s:property value="orderItem.itemPrice"/><br>
                        商品数量：<s:property value="orderItem.itemQuantity"/><br>
                        <s:submit cssClass="formbutton" method="create" value="购买"/>
                    </s:form>
                </div>
            </div>
            <div class="clear"></div>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="order-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>