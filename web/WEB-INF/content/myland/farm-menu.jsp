<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="box">
	<div class="menu">
	    <div class="menubar">农庄管理</div>
	    <div class="menuitem">
	        <ul>
	            <li><a href="<%=request.getContextPath()%>/myland/farm/new?landId=${land.landId}">新建农庄</a></li>
	            <li><a href="<%=request.getContextPath()%>/myland/farm/?landId=${land.landId}">管理农庄</a></li>
	            <li><a href="<%=request.getContextPath()%>/myland/farm!batch?landId=${land.landId}">批处理农庄</a></li>
	        </ul>
	    </div>
	</div>
</div>