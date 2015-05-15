<%@ page import="com.sitexa.framework.config.AppConfig" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>购买农庄</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/buy/buy.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
	<script type="text/javascript">
		function testFarm(){
			var password = $.trim($("#password").val());
			if(password==""){
				alert("请您输入密码。");
				return false;
			}
			return true;
		}
	</script>
</head>
<body>
<div id="container">
    <div id="header">
        <s:include value="../h.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="leftbar" class="leftcol">
            <s:include value="buyfarm-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
				<div class="title">
					<div class="t-text">
						<div class="tag-flowerpot"></div>
						购买农庄
					</div>
				</div>
				<div class="content" style="padding:5px">
					<form id="buyFarm" action="<%=request.getContextPath()%>/buy/farm/${farm.farmId}/pay" method="post">
						<s:hidden name="farm.farmId"/>
						<s:property value="farm.farmName"/><br/>
						农庄面积：<s:property value="farm.acreage"/> 平方米<br/>
						农庄价格：￥<s:property value="farm.acreage*120"/>(即：<s:property value="farm.rentPrice"/>花币)<br/>
						支付密码：<input type="password" id="password" name="password" value="" /><br />
						<input type="submit" value="购 买" onclick="return testFarm()"/>
						<input type="button" value="返 回" onclick="history.back()"/> 
						<s:actionerror cssStyle="color:red"/>
					</form>
				</div>
            </div>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="buyfarm-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>