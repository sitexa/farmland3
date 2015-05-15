<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.sitexa.framework.config.AppConfig" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/tools.js"></script>
	<script type="text/javascript">
		function checkForm(){
			var msg = "";
			var planName = $.trim($("#planName").val());
			if(planName==""){
				msg = "套餐名称不能为空。\n";
			}
			var price = $.trim($("#price").val());
			if(!isInt(price)){
				msg += "套餐价格有误，应为正整数。\n";
			}
			var description = $.trim($("#description").val());
			if(description==""){
				msg += "套餐描述不能为空。\n";
			}
			if(msg=="") return true;
			alert(msg);
			return false;
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
            <s:include value="plan-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
			<div class="box">
				<div class="t">
					<div class="t-t">
						新建套餐
					</div>
				</div>
                <div class="contents">
					<s:form id="frmPlan" action="%{#request.contextPath}/join/plan" method="post">
						<input type="hidden" name="landId" value="${land.landId }" />
						<span class="blank6"></span>
						套餐名称：<s:textfield id="planName" name="plan.planName"></s:textfield>
						<span class="blank6"></span>
						套餐价格：<s:textfield id="price" name="plan.price"></s:textfield>
						<span class="blank6"></span>
						套餐描述：<s:textfield id="description" name="plan.description" cssStyle="width:400px"></s:textfield>
						<span class="blank6"></span>
						备注：<s:textarea name="plan.remark" cssStyle="width:560px"></s:textarea>
						<span class="blank6"></span>
						<s:submit value="保 存" method="create" cssClass="formbutton" onclick="return checkForm()"/>
					</s:form>
				</div>
			</div>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="plan-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>