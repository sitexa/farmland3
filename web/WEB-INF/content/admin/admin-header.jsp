<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="loginbox">
    <s:if test="profile!=null">
        您好,${profile.realname}! &nbsp; <a href="<%=request.getContextPath()%>/login!logout">退出</a>
    </s:if>
    <s:elseif test="me!=null">
        您好,${me.username}! &nbsp; <a href="<%=request.getContextPath()%>/login!logout">退出</a>
    </s:elseif>
    <s:else>
        <a href="<%=request.getContextPath()%>/login">登录</a>
    </s:else>
</div>
<div id="navbar">
    <ul>
        <li>
            <a href="<%=request.getContextPath()%>/admin/user/user-admin">用户管理</a>
        </li>
    </ul>
</div>
