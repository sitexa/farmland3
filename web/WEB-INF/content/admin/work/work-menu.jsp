<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="box">
    <div class="menu">
        <div class="menubar">操作管理</div>
        <div class="menuitem">
            <ul>
                <li><a href="<%=request.getContextPath()%>/admin/work/work">农场列表</a></li>
                <s:if test="land!=null">
                    <li><a href="<%=request.getContextPath()%>/admin/work/work!farms?landId=${land.landId}">农庄列表</a>
                    </li>
                </s:if>
                <s:if test="farm!=null">
                    <li><a href="<%=request.getContextPath()%>/admin/work/work!tasks?farmId=${farm.farmId}">任务列表</a>
                    </li>
                    <li><a href="<%=request.getContextPath()%>/admin/work/crops?farmId=${farm.farmId}">作物列表</a>
                    </li>
                </s:if>
                <s:if test="farm!=null && seed!=null">
                    <li><a href="<%=request.getContextPath()%>/admin/work/work!farming?farmId=${farm.farmId}&seedId=${seed.seedId}">处理操作</a>
                    </li>
                </s:if>
            </ul>
        </div>
    </div>
</div>