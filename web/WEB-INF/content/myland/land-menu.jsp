<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="box">
    <div class="menu">
        <div class="menubar">加盟管理</div>
        <div class="menuitem">
            <ul>
                <li><a href="<%=request.getContextPath()%>/myland/land/new">加盟农场</a></li>
                <li><a href="<%=request.getContextPath()%>/myland/land/">管理农场</a></li>
            </ul>
        </div>
    </div>
    <s:if test="land!=null">
        <div class="menu">
            <div class="menubar">管理农场：${land.landName}</div>
            <div class="menuitem">
                <ul>
                    <li><a href="<%=request.getContextPath()%>/myland/land/${land.landId}">预览农场资料</a></li>
                    <li><a href="<%=request.getContextPath()%>/myland/land/${land.landId}/edit">修改农场资料</a></li>
                    <li><a href="<%=request.getContextPath()%>/myland/land-picture/?landId=${land.landId}">管理农场图片</a></li>
                    <li><a href="<%=request.getContextPath()%>/myland/farm/?landId=${land.landId}">管理农庄</a></li>
                    <li style="text-indent:10px"><a href="<%=request.getContextPath()%>/myland/farm/new?landId=${land.landId}">创建农庄</a></li>
                    <li><a href="<%=request.getContextPath()%>/myland/faction/?landId=${land.landId}">管理农场服务</a></li>
                    <li style="text-indent:10px"><a href="<%=request.getContextPath()%>/myland/faction/new?landId=${land.landId}">创建农场服务</a></li>
                    <li><a href="<%=request.getContextPath()%>/myland/seed/?landId=${land.landId}">管理种子种苗</a></li>
                    <li style="text-indent:10px"><a href="<%=request.getContextPath()%>/myland/seed/new?landId=${land.landId}">创建种子种苗</a></li>
                    <li><a href="<%=request.getContextPath()%>/myland/plan/?landId=${land.landId}">管理套餐</a></li>
                    <li style="text-indent:10px"><a href="<%=request.getContextPath()%>/myland/plan/new?landId=${land.landId}">创建套餐</a></li>
                </ul>
            </div>
        </div>
    </s:if>
    <s:if test="farm!=null">
        <div class="menu">
            <div class="menubar">管理农庄：${farm.farmId}</div>
            <div class="menuitem">
                <ul>
                    <li><a href="<%=request.getContextPath()%>/myland/farm/new?landId=${land.landId}">新建农庄</a></li>
                    <li><a href="<%=request.getContextPath()%>/myland/farm!batch?landId=${land.landId}">批处理农庄</a></li>
                    <li><a href="<%=request.getContextPath()%>/myland/farm/${farm.farmId}">查看农庄</a></li>
                    <li><a href="<%=request.getContextPath()%>/myland/farm/${farm.farmId}/edit">修改农庄</a></li>
                    <li><a href="<%=request.getContextPath()%>/myland/farm/${farm.farmId}/destroy">删除农庄</a></li>
                </ul>
            </div>
        </div>
    </s:if>
</div>