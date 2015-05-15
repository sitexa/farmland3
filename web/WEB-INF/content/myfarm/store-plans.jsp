<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:iterator value="goods" var="object">
<div class="imgBox">
	<div class="img" title="${object[1]}"><img src="<%=request.getContextPath()%>/myfarm/img/plan.gif" onclick="showDialog('plan','${object[0]}')"/></div>
	<div class="info" onclick="showDialog('')">
		<font color="#BA2636">数量</font>&nbsp;<font style="font-weight:bold;color:#FF8000">${object[2]}</font>
	</div>
</div>
</s:iterator>