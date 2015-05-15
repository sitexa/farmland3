<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:iterator value="seedList">
	<div class="imgBox">
		<div class="img" title="${seedName}"><img src="<%=request.getContextPath()%>/image?type=seed&picId=${seedId }" onclick="showDialog('seed','${seedId}')"/></div>
		<div class="info" onclick="showDialog('')">
			<font color="#BA2636">花币</font>&nbsp;<font style="font-weight:bold;color:#FF8000">${price }</font>
		</div>
	</div>
</s:iterator>