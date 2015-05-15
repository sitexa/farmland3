<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="menu">
    <div class="menubar">
        服务管理
    </div>
    <div class="menuitem">
        <ul>
            <li><a href="#">-----------</a></li>
            <li><s:a href="%{#request.contextpath}/join/land">农场管理</s:a></li>
            <li><s:a href="%{#request.contextpath}/join/land/new">创建农场</s:a></li>
            <li><s:a href="%{#request.contextpath}/join/service">管理服务模板</s:a></li>
            <li><s:a href="%{#request.contextpath}/join/service/new">创建服务模板</s:a></li>
        </ul>
    </div>
</div>