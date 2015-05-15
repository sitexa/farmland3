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
	    <div class="menubar">农庄管理</div>
	    <div class="menuitem">
	        <ul>
	            <li><a href="<%=request.getContextPath()%>/join/farm/new?landId=${land.landId}">新建农庄</a></li>
	            <li><a href="<%=request.getContextPath()%>/join/farm/?landId=${land.landId}">管理农庄</a></li>
	            <li><a href="<%=request.getContextPath()%>/join/farm!batch?landId=${land.landId}">批处理农庄</a></li>
	        </ul>
	    </div>
	</div>
</div>