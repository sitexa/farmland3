<%--
  User: xnpeng
  Date: 2009-10-13
  Time: 19:23:38
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/index.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="container">
<div id="header">
    <s:include value="../h.jsp"/>
</div>
<div id="mainwrapper">
    <div id="leftbar" class="leftcol">
        <s:include value="purchase-menu.jsp"/>
    </div>
    <div id="centerbar" class="centercol">
        <div class="box">
	        <div class="title">
	            <ul>
					<li class="title_left9"></li>
				  	<li class=title_text>商品内容</li>
				  	<li class="title_right"></li>
	                <div class="clear"></div>
	            </ul>
	        </div>
            <div class="content">
                <s:form id="frmPurchase" theme="simple" action="%{#request.contextPath}/market/purchase/%{orderItem.itemId}" method="post">
                    <s:hidden name="objectId"/><s:hidden name="orderItem.itemId"/>
                    商品分类：<s:if test="objectId==OBJECT_FARM">农庄</s:if> <br>
                    商品编号：<s:property value="orderItem.itemId"/><br>
                    商品名称：<s:textfield name="orderItem.itemName" readonly="true"/><br>
                    商品价格：<s:textfield name="orderItem.itemPrice" readonly="true"/><br>
                    商品数量：<s:textfield name="orderItem.itemQuantity"/><br>
                    <!--<s:submit cssClass="formbutton" method="create" value="购买"/>--><s:submit cssClass="formbutton" method="bill" value="结帐"/>
                </s:form>
            </div>
        </div>
    </div>
    <div id="rightbar" class="rightcol">
        <s:include value="purchase-right.jsp"/>
    </div>
	<div class="clear"></div>
</div>
<div id="footer">
    <s:include value="../f.jsp"/>
</div>
</div>
</body>
</html>