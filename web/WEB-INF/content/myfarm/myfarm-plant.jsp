<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>${faction.actionName }</title>
	<link href="<%=request.getContextPath()%>/myfarm/myfarm-tools.css" rel="stylesheet" type="text/css" />
	<script src='<%=request.getContextPath()%>/dwr/interface/JCropService.js'></script>
	<script src='<%=request.getContextPath()%>/dwr/engine.js'></script>
	<script src='<%=request.getContextPath()%>/dwr/util.js'></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/script/tools.js"></script>
	<script type="text/javascript">
        $("#operFrm").ready(function() {
            $("#tool_seedId").change(function() {
                var seedId = $(this).val();
                if(seedId=="-1"){
                	$("#seedImg").attr("src", "");
                	show_crops_status(null);
                	return;
                }
                var farmId = $("#farmId").val();
                var memberId = $("#memberId").val();
                $("#seedImg").attr("src", "<%=request.getContextPath()%>/image?type=seed&picId="+ seedId);
				JCropService.getSeedInfo(farmId, memberId, seedId, "plant", show_crops_status);
			})
		});

		function show_crops_status(data) {
			if (data) {
				$("#seedNumber").html(data.seedNumber);
				count();
				$("#seed_remark").html(data.description);
			}else{
				$("#seedNumber").html("0");
				$("#seed_remark").html("");			
			}
		}
		
		function logsVisibility(style) {
			$("#logs").css("visibility", style);
		}
	
		function count() {
			var count = 0;
			var quantity = Number($.trim($("#farming_quantity").val()));
			if (quantity <= 0) {
				$("#total_fee").html("?");
			} else {
				var service_price = $.trim($("#service_price").html());
				if(quantity<10) quantity=10;
				if (isDouble(service_price))
					count += (Number(service_price) * quantity);
				$("#total_fee").html(count);
			}
		}
	
		function quantityChange() {
			var seedNumber = $.trim($("#seedNumber").html());
			var noPlantingAcreage = $.trim($("#noPlantingAcreage").html());
			var min = Math.min(Number(noPlantingAcreage),Number(seedNumber));
			
			var quantity = $.trim($("#farming_quantity").val());
			if (quantity == "")
				$("#farming_quantity").val(0);
			if (!isInt(quantity)) {
				$("#farming_quantity").val(min);
				alert("您输入的面积格式有误，请您重新输入。\n注意：面积为正整数。");
			}else if(Number(quantity)>min){
				$("#farming_quantity").val(min);
			}
			count();
		}
		function paymode() {
			var cb = $("#cb_paymode").attr("checked");
			if (cb) {
				$("#paybox").css("display", "block");
			} else {
				$("#paybox").css("display", "none");
			}
		}
	
		function testForm() {
			var seed = $.trim($("#tool_seedId").val());
			if (isEmpty(seed) || seed == "-1") {
				alert("请您选择要播种的种子。");
				return false;
			}
			var quantity = $.trim($("#farming_quantity").val());
			if (!isInt(quantity)) {
				alert("您输入的面积格式有误，请您重新输入。\n注意：面积为整数。");
				return false;
			}
			if (quantity == "0") {
				alert("请您输入面积。");
				return false;
			}
			var password = $("#creditPassword").val();
			if ($("#cb_paymode").attr("checked") && isEmpty(password)) {
				alert("请您输入密码！");
				return false;
			}
			return true;
		}
	</script>
</head>
<body>
	<div class="window">
		<div class="header">${faction.actionName }</div>
		<div class="body">
			<s:form action="%{#request.contextPath}/myfarm/myfarm" id="operFrm"method="post">
				<s:hidden name="farm.farmId" id="farmId" />
				<s:hidden name="faction.factionId" />
				<s:hidden name="faction.actionId" />
				<s:hidden name="member.memberId" id="memberId"></s:hidden>

				<div id="logs" class="logs-box" style="display: block;">
					<div class="logs-header">
						<a href="#" class="close" onclick="logsVisibility('hidden')">关闭</a>${faction.actionName}日志
					</div>
					<div class="logs-content">
						<s:iterator value="farmings" status="its">
							<s:date format="yyyy-MM-dd" name="startTime" />
							${farmer.realname}
							${seed.seedName}
							${faction.actionName}
							${contents}<br />
						</s:iterator>
					</div>
				</div>
				<div class="clear"></div>
				<div class="box">
					<div class="left">
						<img src="<%=request.getContextPath()%>/image?type=faction&picId=${faction.factionId }" />
					</div>
					<div class="right">
						<label class="link">
							<a href="#" onclick="logsVisibility('visible')">${faction.actionName}日志</a>
						</label>
						<div class="description">${faction.contents}</div>
					</div>
				</div>
				<div class="box">
					<div class="left">
						<s:select id="tool_seedId" list="seeds" listKey="seedId" listValue="seedName" 
							name="farming.seed.seedId" headerKey="-1" headerValue="" />
						<div class="box-img">
							<img id="seedImg" />
						</div>
						<font color="#BA2636">数量</font>&nbsp;<label id="seedNumber" style="font-weight:bold;color:#FF8000">0</label>
					</div>
					<div class="right">
						<div id="seed_remark" class="description"></div>
					</div>
				</div>
				<div class="box">
					<ul>
						<li>
							<div class="left">播种面积:</div>
							<div class="right">
								<s:textfield id="farming_quantity" name="farming.quantity" value="1" size="3" onchange="quantityChange()" />
								平方米(该服务最低按10平方米收费)&nbsp;&nbsp;
								<font style="font-size:12px; color:#F00">未播种面积:<label id="noPlantingAcreage">${noPlantingAcreage}</label> 平方米</font>
							</div>
						</li>
						<li>
							<div class="left">
								说明/日志:
							</div>
							<div class="right">
								<s:textarea name="farming.contents" cols="60" rows="4" />
								<br />
								委托农场操作:
								<s:checkbox name="farming.paymode" value="false" id="cb_paymode" onclick="return paymode();" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<s:submit value="提交" method="operate" onclick="return testForm()" />
							</div>
						</li>
					</ul>
				</div>
				<div id="paybox" class="box" style="display: none">
					<table style="color: #F00; text-align: left; margin-left: 100px">
						<tr>
							<td align="right">操作/服务价格:</td>
							<td>
								<label class="prices" id="service_price">${faction.expense}</label>(花币)/平方米
							</td>
						</tr>
						<tr>
							<td align="right">总价:</td>
							<td>
								<label class="prices" id="total_fee"></label>(花币)
							</td>
						</tr>
						<tr>
							<td align="right">支付密码:</td>
							<td>
								<s:password id="creditPassword" name="creditPassword" />
							</td>
						</tr>
					</table>
					<div class="notice">
						请慎重操作，此项服务一但支付，将不可逆转！如有疑问，请联系客服。
					</div>
				</div>
				<div class="bottomer"></div>

			</s:form>
		</div>
	</div>
</body>
</html>