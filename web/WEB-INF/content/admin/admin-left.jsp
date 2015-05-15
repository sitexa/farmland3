<%--
  User: xnpeng
  Date: 2009-5-4
  Time: 8:01:02
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="box">
    <div class="title">社区管理</div>
    <div class="menu">
        <ul>
            <li>
                <a href="<%=request.getContextPath()%>/admin/site/site-admin">社区管理</a>
            </li>
        </ul>
    </div>
</div>
<div class="box">
    <div class="title">用户管理</div>
    <div class="menu">
        <ul>
            <li>
                <a href="<%=request.getContextPath()%>/admin/user/user-admin">用户管理</a>
            </li>
        </ul>
    </div>
</div>
<div class="box">
    <div class="title">内容管理</div>
    <div class="menu">
        <ul>
            <li>
                <a href="<%=request.getContextPath()%>/admin/post/post-admin">内容管理</a>
            </li>
        </ul>
    </div>
</div>
<div class="box">
    <div class="title">关键词</div>
    <div class="menu">
        <ul>
            <li>
                <a href="<%=request.getContextPath()%>/admin/filter">关键词管理</a>
            </li>
        </ul>
    </div>
</div>