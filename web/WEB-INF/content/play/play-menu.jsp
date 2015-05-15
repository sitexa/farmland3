<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="box">
	<div class="menu">
		<div class="menubar">玩转农庄</div>
	    <div class="menuitem">
	        <ul>
	            <li>
	                <a href="<%=request.getContextPath()%>/play/play?postType.typeId=9">农场新闻</a>
	            </li>
	            <li>
	                <a href="<%=request.getContextPath()%>/play/play?postType.typeId=10">农场活动</a>
	            </li>
	            <li>
	                <a href="<%=request.getContextPath()%>/play/play?postType.typeId=11">农场攻略</a>
	            </li>
	            <li>
	                <a href="<%=request.getContextPath()%>/play/play?postType.typeId=110">农场通知</a>
	            </li>
	        </ul>
	    </div>
	    <div class="publish">
	        <a href="<%=request.getContextPath()%>/play/play/new">发表文章=></a>
	    </div>
	</div>
</div>