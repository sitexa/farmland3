<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="box">
    <div class="menu">
        <div class="menubar">
            农庄管理
        </div>
        <div class="menuitem">
            <ul>
                <li><a href="<%=request.getContextPath()%>/myfarm/farm/${farm.farmId}">农庄资料</a></li>
                <li><a href="<%=request.getContextPath()%>/myfarm/farm/${farm.farmId}/edit">修改资料</a></li>
                <li><a href="<%=request.getContextPath()%>/myfarm/farm-owner/${farm.farmId}/edit">管理庄主</a></li>
                <li><a href="<%=request.getContextPath()%>/myfarm/farm-picture/${farm.farmId}/edit">管理图片</a></li>
                <li><a href="<%=request.getContextPath()%>/myfarm/farm-post/${farm.farmId}/edit">管理文章</a></li>
            </ul>
        </div>
    </div>
</div>