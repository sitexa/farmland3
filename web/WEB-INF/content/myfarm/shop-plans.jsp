<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:iterator value="planList">
<div class="imgBox">
	<div class="img" title="${planName}"><img src="<%=request.getContextPath()%>/myfarm/img/plan.gif" onclick="showDialog('plan','${planId}')"/></div>
	<div class="info" onclick="showDialog('')">
		<font color="#BA2636">花币</font>&nbsp;<font style="font-weight:bold;color:#FF8000">${price }</font>
	</div>
</div>
</s:iterator>