<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="box">
	<div class="menu">
	    <div class="menubar">农场管理</div>
	    <div class="menuitem">
	        <ul>
	            <li><a href="<%=request.getContextPath()%>/myland/joinland/">加盟农场</a></li>
	            <li><a href="<%=request.getContextPath()%>/myland/land/">农场管理</a></li>
	            <li><a href="#">-----------</a></li>
	            <li><a href="<%=request.getContextPath()%>/myland/plan/new?landId=${land.landId}">新建套餐</a></li>
	            <li><a href="<%=request.getContextPath()%>/myland/plan/?landId=${land.landId}">管理套餐</a></li>
	        </ul>
	    </div>
	</div>
</div>