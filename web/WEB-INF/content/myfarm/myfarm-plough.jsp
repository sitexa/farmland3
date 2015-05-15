<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>${faction.actionName }</title>
		<link href="<%=request.getContextPath()%>/myfarm/myfarm-tools.css"
			rel="stylesheet" type="text/css" />
		<script
			src='<%=request.getContextPath()%>/dwr/interface/JCropService.js'></script>
		<script src='<%=request.getContextPath()%>/dwr/engine.js'></script>
		<script src='<%=request.getContextPath()%>/dwr/util.js'></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/script/jquery.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/script/tools.js"></script>
		<script type="text/javascript">
	function paymode() {
		var cb = $("#cb_paymode").attr("checked");
		if (cb) {
			$("#paybox").css("display", "block");
			count();
		} else {
			$("#paybox").css("display", "none");
		}
	}
	function testForm() {
		var password = $("#creditPassword").val();
		if ($("#cb_paymode").attr("checked") && isEmpty(password)) {
			alert("请您输入密码！");
			return false;
		}
		return true;
	}
	function count() {
		var count = 0;
		var service_price = $.trim($("#service-price").html());
		var quantity = $.trim($("#farming_quantity").val());
		if (isInt(service_price) && isInt(quantity))
			count += (Number(service_price) * Number(quantity));
		$("#total-fee").html(count);
	}
</script>
	</head>
	<body>
		<div class="window">
			<div class="header">
				${faction.actionName }
			</div>
			<div class="body">

				<s:form action="%{#request.contextPath}/myfarm/myfarm" id="operFrm"
					method="post">
					<s:hidden name="farm.farmId" id="farmId" />
					<s:hidden name="faction.factionId" />

					<%--耕地--%>
					<div class="box">
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
					<div class="box">
						<ul>
							<li>
								<div class="left">
									面积:
								</div>
								<div class="right">
									<s:textfield id="farming_quantity" name="farming.quantity"
										readonly="true" value="%{#request.farm.acreage}" />
									平方米(该服务最低按10平方米收费)
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
									<s:checkbox name="farming.paymode" value="false"
										id="cb_paymode" onclick="return paymode();" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<s:submit value="提交" method="operate"
										onclick="return testForm()" />
								</div>
							</li>
						</ul>
					</div>
					<div id="paybox" class="box" style="display: none">
						<table style="color: #F00; text-align: left; margin-left: 100px">
							<tr>
								<td align="right">
									操作/服务价格:
								</td>
								<td>
									<label class="prices" id="service-price">
										${faction.expense}
									</label>
									(花币)/平方米
								</td>
							</tr>
							<tr>
								<td align="right">
									总价:
								</td>
								<td>
									<label class="prices" id="total-fee"></label>
									(花币)
								</td>
							</tr>
							<tr>
								<td align="right">
									支付密码:
								</td>
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