<%@ page import="com.sitexa.framework.config.AppConfig" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/buy/buy.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
	<script type="text/javascript">
		$(function(){
			$(".img-text-list li").each(function(){
				$(this).hover(function(){$(this).css("background","#E9FFD2")},function(){$(this).css("background","")})
						//.click(function(){location.href = $(this).find(".tit a").attr("href")})
			})
		})
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
				<div class="t">
					<div class="t-t">
						操作服务
					</div>
				</div>
				<s:if test="factions.size>0">
				<div class="img-text-list">
					<ul>
						<s:iterator value="factions" status="its">
						<li>
							<div class="tit"><a href="<%=request.getContextPath()%>/buy/faction/${farmingActionId}">${actionName}</a></div>
							<span class="blank2"></span>
							<div class="body">
								<div class="left">
									<div class="img-box">
										<img src="<%=request.getContextPath()%>/image?type=service&picId=${actionId}"/>
									</div>
								</div>
								<!--<div class="right">3</div>-->
								<div class="middle">
									<font class="bold">服务价格：</font>￥${expense}<br />
									<font class="bold">服务说明：</font>${contents}<br />
									<font class="bold">备　　注：</font>${remark}<br />
								</div>
							</div>
						</li>
						</s:iterator>
					</ul>
					<div class="clear"></div>
				</div>
				</s:if>

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