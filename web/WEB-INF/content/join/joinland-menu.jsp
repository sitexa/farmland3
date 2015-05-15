<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="box">
	<div class="menu">
		<div class="menubar">加盟农场</div>
	    <div class="menuitem">
	        <ul>
	            <li><a href="<%=request.getContextPath()%>/join/joinland">加盟农场</a></li>
	            <li><a href="<%=request.getContextPath()%>/join/land/new">创建农场</a></li>
	            <li><a href="<%=request.getContextPath()%>/join/land">管理农场</a></li>
	            <li><a href="<%=request.getContextPath()%>/join/service">管理服务模板</a></li>
	            <li><a href="<%=request.getContextPath()%>/join/service/new">创建服务模板</a></li>
	        </ul>
	    </div>
	</div>
</div>