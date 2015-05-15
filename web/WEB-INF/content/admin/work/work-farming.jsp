<%--
  User: xnpeng
  Date: 2009-5-3
  Time: 23:47:50
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>农庄管理</title>
    <link href="<%=request.getContextPath()%>/admin/css/admin.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/admin/js/admin.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/tools.js"></script>
    <script type="text/javascript">

        onload = function() {
            var width = 45;
            $("tbody td").each(function() {
                var txt = $.trim($(this).html());
                var len = $.getStringLength(txt);
                var t = len > width;
                $(this).attr("title", $(this).attr("title") + txt);
                $(this).html($.substring(txt, t ? width - 3 : width) + (t ? "..." : ""));
            });
        };

        $(function() {
            $("tbody tr:odd").each(function() {
                $(this).addClass("odd")
            });
        });

        function dos(page) {
            document.getElementById("page.current").value = parseInt(page);
            document.getElementById("pageFarm").submit();
        }
    </script>
</head>
<body>
<div id="container">
    <div id="header">
        <s:include value="../../h.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="leftbar">
            <s:include value="work-menu.jsp"/>
        </div>
        <div id="centerbar" style="width:700px">
            <table id="farmings" cellpadding="2" cellspacing="2" width="100%">
                <thead>
                <th nowrap>服务名称</th>
                <th nowrap>时间</th>
                <th nowrap>服务说明</th>
                <th nowrap>操作说明</th>
                <th nowrap>操作</th>
                </thead>
                <tbody>
                <s:if test="farmings.size>0">
                    <s:iterator value="farmings" status="its">
                        <tr>
                            <td>${faction.actionName}</td>
                            <td><s:date name="startTime" format="MM-dd"/></td>
                            <td>${contents}</td>
                            <td>${remark}</td>
                            <td><s:if test="state==1">已执行</s:if><s:else> 未执行</s:else></td>
                        </tr>
                    </s:iterator>
                </s:if>
                </tbody>
            </table>
            <div id="paging">
                <s:if test="page.total!=0">
                    共有${page.total}页结果:
                    <s:if test="page.current>6">
                        <a href="#" onclick="dos(1);">1</a>
                    </s:if>
                    <s:if test="page.current>7">
                        ...
                    </s:if>
                    <s:bean name="org.apache.struts2.util.Counter" id="counter">
                        <s:param name="first" value="page.current-5"/>
                        <s:param name="last" value="page.current+5"/>
                        <s:iterator status="its">
                            <s:if test="current>1 && current<=page.total+1">
                                <s:if test="#its.index==5">
                                    <s:property/>
                                </s:if>
                                <s:else>
                                    <a href="#" onclick="dos('<s:property/>');"> <s:property/></a>
                                </s:else>
                            </s:if>
                        </s:iterator>
                    </s:bean>
                    <s:if test="page.total>page.current+6">
                        ...
                    </s:if>
                    <s:if test="page.total>page.current+5">
                        <a href="#" onclick="dos('<s:property value="page.total"/>');"><s:property
                                value="page.total"/></a>
                    </s:if>
                </s:if>
            </div>
            <s:form id="pageFarm" theme="simple" action="%{#request.contextPath}/admin/work/work!farming" method="get"
                    cssStyle="display: none">
                <s:hidden id="farmId" name="farmId" value="%{#request.farmId}"/>
                <s:hidden id="seedId" name="seedId" value="%{#request.seedId}"/>
                <s:hidden id="page.current" name="page.current" value="1"/>
            </s:form>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../../f.jsp"/>
    </div>
</div>
</body>
</html>