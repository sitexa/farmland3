<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="box">
	<div class="menu">
	    <div class="menubar">农场管理</div>
	    <div class="menuitem">
	        <ul>
	            <li><a href="<%=request.getContextPath()%>/myland/seed/?landId=${land.landId}">种子管理</a></li>
	            <li><a href="<%=request.getContextPath()%>/myland/seed/new?landId=${land.landId}">新建种子</a></li>
	        </ul>
	    </div>
	</div>
</div>