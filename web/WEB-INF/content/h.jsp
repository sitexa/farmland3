<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/script/mouseovertabs.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/script/mouseovertabs.js"></script>
<div class="headbar">
	<div id="login">
	    <s:if test="profile!=null">
	        ${profile.realname}&nbsp;|&nbsp;
            <a href="<%=request.getContextPath()%>/logon!logout">退出</a>&nbsp;|&nbsp;
			<s:if test="profile.type.typeId==6">
				<a href="<%=request.getContextPath()%>/admin/work/work">后台管理</a>&nbsp;|&nbsp;
			</s:if>
	    </s:if>
	    <s:else>
			<a href="<%=request.getContextPath()%>/user/user/new">注册</a>&nbsp;|&nbsp;
	        <a href="<%=request.getContextPath()%>/logon">登录</a>&nbsp;|&nbsp;
	    </s:else>
        <a href="<%=request.getContextPath()%>/site/site">社区之家</a>&nbsp;|&nbsp;
        <a href="<%=request.getContextPath()%>/user/member">会员中心</a>
	</div>
	<div id="navbar" class="tabsmenuclass">
	    <ul>
	        <li><a <s:if test="mn==1">class='active'</s:if> href="<%=request.getContextPath()%>/main?mn=1">首页</a></li>
            <li><a <s:if test="mn==3">class='active'</s:if> href="<%=request.getContextPath()%>/land/land?mn=3">CSA联盟</a></li>
	        <li><a <s:if test="mn==2">class='active'</s:if> href="<%=request.getContextPath()%>/work/work?mn=2">我的农庄</a></li>
	        <li><a <s:if test="mn==4">class='active'</s:if> href="<%=request.getContextPath()%>/myland/land?mn=4">我的农场</a></li>
	        <li><a <s:if test="mn==5">class='active'</s:if> href="<%=request.getContextPath()%>/play/play?mn=5">农场论坛</a></li>
	        <li><a <s:if test="mn==6">class='active'</s:if> href="<%=request.getContextPath()%>/help/help?mn=6">关于农场</a></li>
	    </ul>
        <div class="clear"></div>
	</div>
</div>
<div class="topline"></div>
