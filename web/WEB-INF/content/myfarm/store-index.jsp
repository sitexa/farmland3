<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>${faction.actionName }</title>
	<link href="<%=request.getContextPath()%>/myfarm/myfarm-tools.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/myfarm/myfarm-shop.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
	<script type="text/javascript">
		var path = "<%=request.getContextPath()%>/myfarm/store";
		var dialogUrl = new Object();
		$(function(){
			dialogUrl.seed = path + "!seedPage?farmId="+$("#farmId").val()+"&itemId=";
			dialogUrl.plan = path + "!planPage?farmId="+$("#farmId").val()+"&itemId=";
		})
		
		function load(url){
			$("#list").load(url,null,null);
		}
		
		function showDialog(group, commodityId){
			var dialogLeft = (screen.width-650)/2;
			var dialogTop = (screen.height-550)/2;
			var val = window.showModalDialog(dialogUrl[group]+commodityId,"","dialogWidth=650px;dialogHeight=550px;dialogLeft="+dialogLeft+";dialogTop="+dialogTop+";help=0;resizable=0;status=0;scroll=0");
		}
		/*
		var buyPageUrl = new Object();
		buyPageUrl.seed = path + "!buySeed";
		buyPageUrl.plan = path + "!buyPlan";
		*/
		function buyPage(group, farmId, commodityId, pwd, number){
			$.get(
					path + "!buy",
					{group:group, farmId:farmId, id:commodityId, pwd:pwd, number:number}, 
					function (data) {
						if(data){
							alert(data);
						}else{
							alert("删除失败，请重新操作。");
						}
					}
			);
		}
	</script>
</head>
<body>
	<div class="window">
		<div class="header">仓&nbsp;&nbsp;库</div>
		<div class="body">
			<input type="hidden" id="farmId" value="${farm.farmId}" />
			<div class="box" style="text-align:left">
				<a href="javascript:void(0)" onclick="load('<%=request.getContextPath()%>/myfarm/store!seeds?farmId=${farm.farmId }')">种子/种苗</a>&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="load('<%=request.getContextPath()%>/myfarm/store!plans?farmId=${farm.farmId }')">服务套餐</a>&nbsp;&nbsp;
				<a href="javascript:void(0)">农用材料</a>
			</div>
			<div class="box" style="height:400px;overflow-y:auto">
				<div id="list" style="float:left;padding:10px;">
				<s:iterator value="goods" var="object">
					<div class="imgBox">
						<div class="img" title="${object[1]}">
							<img src="<%=request.getContextPath()%>/image?type=seed&picId=${object[0]}" onclick="showDialog('seed','${object[0]}')"/>
						</div>
						<div class="info" onclick="showDialog('')">
							<font color="#BA2636">数量</font>&nbsp;<font style="font-weight:bold;color:#FF8000">${object[2] }</font>
						</div>
					</div>
				</s:iterator>
				</div>
			</div>
			<div class="bottomer"></div>
		</div>
	</div>
</body>
</html>