<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="menu">
    <div class="menubar">
        服务管理
    </div>
    <div class="menuitem">
        <ul>
            <li><s:a href="%{#request.contextpath}/myland/land">农场管理</s:a></li>
            <li><s:a href="%{#request.contextpath}/myland/land/new">创建农场</s:a></li>
            <li><s:a href="%{#request.contextpath}/myland/service">管理服务模板</s:a></li>
            <li><s:a href="%{#request.contextpath}/myland/service/new">创建服务模板</s:a></li>
        </ul>
    </div>
</div>