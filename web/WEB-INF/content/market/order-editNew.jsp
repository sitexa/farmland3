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
                <div class="title">购物车</div>
                <div class="content">
                    <s:if test="order!=null">
                        <s:form name="frmPO" theme="simple" action="%{#request.contextPath}/market/purchase/%{order.orderId}/bill">
                            <s:hidden name="order.orderId"/>
                            <s:iterator value="order.orderItems">
                                ${orderItemId} ${itemId} ${itemName} ${itemPrice} ${finalPrice}<br>
                            </s:iterator>
                            <strong>Total Price:${order.currencyValue}</strong> <br>
                            <s:submit cssClass="formbutton" value="结帐" method="bill"/>
                        </s:form>
                    </s:if>
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