<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="content">
	<div id="login" style="float:right">
	    <s:if test="profile!=null">
	        ${profile.realname}&nbsp;&nbsp;|&nbsp;&nbsp;
	        <a href="<%=request.getContextPath()%>/user/member">个人中心</a>&nbsp;&nbsp;|&nbsp;&nbsp;
	        <a href="<%=request.getContextPath()%>/logon!logout">退出</a>
	    </s:if>
	    <s:else>
			<a href="<%=request.getContextPath()%>/user/user/new">注册</a>&nbsp;&nbsp;|&nbsp;&nbsp;
	        <a href="<%=request.getContextPath()%>/logon">登录</a>
	    </s:else>
	</div>
</div>
