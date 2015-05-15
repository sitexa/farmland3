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
	                var farmId = $("#farmId").val();
	                $("#seedImg").attr("src", "<%=request.getContextPath()%>/image?type=seed&picId="+ seedId);
					JCropService.getJSONByFarmAndSeed(farmId, seedId,show_crops_status);
				})
			});

			function show_crops_status(data) {
				if (typeof (data.result) != "undefined") {
					$("#seed_remark").html(data.description);
					$("#farming_quantity").val(data.seedNumber);
					count();
				}
			}

			function count() {
				var count = 0;
				var quantity = $.trim($("#farming_quantity").val());
				if (quantity == '0') {
					$("#total_fee").html("?");
				} else {
					var service_price = $.trim($("#service_price").html());
					var seed_price = $.trim($("#seed_price").html());
					if (isDouble(service_price))
						count += (Number(service_price)* (Number(quantity)<10 ? 10 : Number(quantity)));
					$("#total_fee").html(count);
				}
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
			<div class="header">
				${faction.actionName }
			</div>
			<div class="body">

				<s:form action="%{#request.contextPath}/myfarm/myfarm" id="operFrm" method="post">
					<s:hidden name="farm.farmId" id="farmId" />
					<s:hidden name="faction.factionId" />
					<s:hidden name="faction.actionId" />
					<div class="clear"></div>

					<div class="box" style="border-bottom: 1px dashed #ACFF59">
						<div class="left">
							<img
								src="<%=request.getContextPath()%>/image?type=faction&picId=${faction.factionId }" />
						</div>
						<div class="right">
							<div class="description">
								${faction.contents}
							</div>
						</div>
					</div>
					<div class="box" style="border-bottom: 1px dashed #ACFF59">
						<div class="left">
							<s:select id="tool_seedId" list="farmSeeds" listKey="seedId"
								listValue="seedName" name="farming.seed.seedId" headerKey="-1"
								headerValue="" />
							<div class="box-img">
								<img id="seedImg" />
							</div>
						</div>
						<div class="right">
							<div id="seed_remark" class="description"></div>
						</div>
					</div>
					<div class="box">
						<ul>
							<li>
								<div class="left">
									面积:
								</div>
								<div class="right">
									<s:textfield id="farming_quantity" name="farming.quantity" readonly="true" onchange="quantityChange()" />
									平方米(该服务最底按10平方米收费)${farming.quantity }
								</div>
							</li>
							<li>
								<div class="left">
									说明/日志:
								</div>
								<div class="right">
									<s:textarea name="farming.contents" cols="60" rows="4" /><br/>
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
									<label class="prices" id="total_fee">(花币)
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