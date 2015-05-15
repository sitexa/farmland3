<%--
  User: xnpeng
  Date: 2009-5-4
  Time: 9:34:26
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="box">
    <div class="title">用户管理</div>
    <div class="menu">
        <ul>
            <li>
                <a href="<%=request.getContextPath()%>/admin/user/user-admin">查询</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/admin/user/user-admin/${user.userId}/show">查看用户信息</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/admin/user/user-admin/${user.userId}/edit">修改用户信息</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/admin/user/member-admin/${member.memberId}">查看会员信息</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/admin/user/member-admin/${member.memberId}/edit">修改会员信息</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/admin/user/member-property/${member.memberId}/edit">修改会员属性</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/admin/user/member-picture/${member.memberId}/edit">管理会员图片</a>
            </li>
        </ul>
    </div>
</div>
