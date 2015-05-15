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
    <title>任务管理</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/admin/js/admin.js"></script>
    <script type="text/javascript">

        $(function() {
            $("tbody tr:odd").each(function() {
                $(this).addClass("odd")
            });
            $("tbody tr").hover(
                    function() {
                        $(this).addClass("over");
                    },
                    function() {
                        $(this).removeClass("over");
                    }
                    )
        });

        function reply(fid) {
            var dialogLeft = (screen.width - 650) / 2;
            var dialogTop = (screen.height - 550) / 2;
            var farmId = $("#farmId").val();
            var path = "<%=request.getContextPath()%>/admin/work/work!reply?farmingId=" + fid;
            window.open(path, "", 'left=' + dialogLeft + ',top=' + dialogTop + ',width=550,height=350,location=yes,resizable=no');
        }
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
        <div id="leftbar" style="width:19%">
            <s:include value="work-menu.jsp"/>
        </div>
        <div id="centerbar" style="width:78%;">
            <table id="farmings" cellpadding="2" cellspacing="2" width="100%">
                <thead style="background-color:#ddeedd;">
                <th nowrap width="60">农庄名称</th>
                <th nowrap width="60">发令人</th>
                <th nowrap>时间</th>
                <th nowrap>指令名称</th>
                <th nowrap>对象</th>
                <th nowrap>面积</th>
                <th nowrap>费用</th>
                <th nowrap>指令说明</th>
                <th nowrap>农场回复</th>
                <th nowrap>状态</th>
                </thead>
                <tbody>
                <s:if test="task.size>0">
                    <s:iterator value="task" status="its">
                        <tr id="${farmingId}">
                            <td>
                                <a href="<%=request.getContextPath()%>/work/work/${farm.farmId}" target="_blank">
                                    <s:property value="farm.farmName"/>
                                </a>
                            </td>
                            <td>
                                <a href="<%=request.getContextPath()%>/user/member/${farmer.memberId}" target="_blank">
                                    <s:property value="farmer.realname"/>
                                </a>
                            </td>
                            <td>
                                <s:date name="startTime" format="MM-dd"/>
                            </td>
                            <td>
                                <s:if test="faction!=null">${faction.actionName}</s:if>
                            </td>
                            <td>
                                <s:if test="plan!=null">${plan.planName}</s:if>
                                <s:elseif test="seed!=null">${seed.seedName}</s:elseif>
                            </td>
                            <td>${quantity}</td>
                            <td>${amount}</td>
                            <td>${contents}</td>
                            <td>${remark}</td>
                            <td>
                                <s:if test="state==1">完成</s:if>
                                <s:else><input type="button" class="button" value="回复"
                                               onclick="reply(${farmingId})"> </s:else>
                            </td>
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
            <s:form id="pageFarm" theme="simple" action="%{#request.contextPath}/admin/work/work!tasks" method="get">
                <s:hidden id="farmId" name="farmId" value="%{farm.farmId}"/>
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