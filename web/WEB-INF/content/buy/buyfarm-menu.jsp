<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function() {
        var $menu = $(".menu");
        var menu = '${menu}';
        $menu.find(".select").removeClass("select");
        if (menu != "") {
            $menu.find("li:eq(" + Number(menu) + ")").addClass("select");
        }
    })
</script>
<div class="box">
    <div class="menu">
        <div class="menubar">操作指南</div>
        <div class="menuitem">
            <ul>
                <li>
                    <a href="<%=request.getContextPath()%>/buy/buyfarm">农场列表</a>
                </li>
                <s:if test="land!=null">
                    <li>
                        <a href="<%=request.getContextPath()%>/buy/land/${land.landId}/farm">农庄列表</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/buy/land/${land.landId}/faction">农场服务</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/buy/land/${land.landId}/seed">种子种苗</a>
                    </li>
                </s:if>
                <li>
                    -------------------
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/buy/farm!enterFarms">新入驻农庄</a>
                </li>
            </ul>
        </div>
    </div>
</div>