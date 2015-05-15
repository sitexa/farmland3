<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="box">
	<div class="menu">
		<div class="menubar">加盟管理</div>
	    <div class="menuitem">
	        <ul>
	            <li><a href="<%=request.getContextPath()%>/join/joinland/">加盟农场</a></li>
	            <li><a href="<%=request.getContextPath()%>/join/land/">管理农场</a></li>
	        </ul>
	    </div>
	</div>
	<div class="menu">
		<div class="menubar">农场管理</div>
	    <div class="menuitem">
	        <ul>
				<li><a href="<%=request.getContextPath()%>/join/land/${land.landId}">预览农场资料</a></li>
				<li><a href="<%=request.getContextPath()%>/join/land/${land.landId}/edit">修改农场资料</a></li>
	            <li><a href="<%=request.getContextPath()%>/join/land-picture/?landId=${land.landId}">管理农场图片</a></li>
	            <li><a href="<%=request.getContextPath()%>/join/farm/new?landId=${land.landId}">新建农庄</a></li>
	            <li><a href="<%=request.getContextPath()%>/join/farm/?landId=${land.landId}">管理农庄</a></li>
	            <li><a href="<%=request.getContextPath()%>/join/faction/?landId=${land.landId}">管理农场服务</a></li>
	            <li><a href="<%=request.getContextPath()%>/join/seed/?landId=${land.landId}">管理(种子/种苗)</a></li>
	            <li><a href="<%=request.getContextPath()%>/join/plan/?landId=${land.landId}">管理套餐</a></li>
	        </ul>
	    </div>
	</div>
</div>