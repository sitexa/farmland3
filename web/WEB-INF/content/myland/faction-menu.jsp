<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="box">
	<div class="menu">
	    <div class="menubar">服务管理</div>
	    <div class="menuitem">
	        <ul>
	            <li><a href="<%=request.getContextPath()%>/myland/faction/?landId=${land.landId}">服务管理</a></li>
	            <li><a href="<%=request.getContextPath()%>/myland/faction/new?landId=${land.landId}">新建服务</a></li>
	        </ul>
	    </div>
	</div>
</div>