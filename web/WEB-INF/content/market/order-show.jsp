<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title> 花木兰 - 购物车 </title>
    <link href="<%=request.getContextPath()%>/style/index.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/market/css/order.css" rel="stylesheet" type="text/css"/>
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
                        <s:hidden name="order.orderId"/>
                        <table>
                            <tr>
                                <th>项目号</th>
                                <th>商品名</th>
                                <th>价格</th>
                                <th>操作</th>
                            </tr>
                            <s:iterator value="order.orderItems">
                                <tr>
                                    <td>${orderItemId}</td>
                                    <td>${itemName}</td>
                                    <td>${finalPrice}</td>
                                    <td>
                                        <a href="<%=request.getContextPath()%>/market/order-item/${orderItemId}/destroy">删除</a>
                                    </td>
                                </tr>
                            </s:iterator>
                            <tr>
                                <td></td>
                                <td><strong>Total Price:</strong></td>
                                <td><strong>${order.currencyValue}</strong></td>
                                <td>
                                    <a href="<%=request.getContextPath()%>/market/order/${orderId}/bill">结帐</a>
                                </td>
                            </tr>
                        </table>
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