<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="box">
    <div class="menu">
        <div class="menubar">CSA农场</div>
        <div class="menuitem">
            <ul>
                <li>
                    <a href="<%=request.getContextPath()%>/land/land">农场列表</a>
                </li>
                <s:if test="land!=null">
                    <li>
                        <a href="<%=request.getContextPath()%>/land/land/${land.landId}/farms">农庄列表</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/land/land/${land.landId}/factions">农场服务</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/land/land/${land.landId}/seeds">种子种苗</a>
                    </li>
                </s:if>
            </ul>
        </div>
    </div>
</div>