<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/buy/buy.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript">
        $(function() {
            $("tbody tr:odd").each(function() {
                $(this).addClass("odd")
            })
        });

        function dos(page) {
            document.getElementById("page.current").value = parseInt(page);
            document.getElementById("pageFrm").submit();
        }
    </script>
</head>
<body>
<div id="container">
    <div id="header">
        <s:include value="../h.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="leftbar" class="leftcol">
            <s:include value="buyfarm-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
                <div class="t">
                    <div class="t-t">
                        农庄列表-【${land.landName}】
                    </div>
                </div>
                <div class="content">
                    <table cellpadding="4" cellspacing="4" width="100%">
                        <thead style="background-color:#ccccff">
                        <th>农庄编号</th>
                        <th>农庄名称</th>
                        <th>农庄面积</th>
                        <th>农庄主</th>
                        <th>操作</th>
                        </thead>
                        <tbody>
                        <s:iterator value="farms">
                            <tr id="${farmId}">
                                <s:if test="member!=null">
                                    <td>
                                        <a href="<%=request.getContextPath()%>/buy/farm/${farmId}">${farmId}</a>
                                    </td>
                                    <td>
                                        <a href="<%=request.getContextPath()%>/work/work/${farmId}">${farmName}</a>
                                    </td>
                                    <td>${acreage}m<sup>2</sup></td>
                                    <td>
                                        <a href="<%=request.getContextPath()%>/user/member/${member.memberId}">${member.realname}</a>
                                    </td>
                                    <td></td>
                                </s:if>
                                <s:else>
                                    <td>
                                        <a href="<%=request.getContextPath()%>/buy/farm/${farmId}">${farmId}</a>
                                    </td>
                                    <td>${farmName}</td>
                                    <td>${acreage}m<sup>2</sup></td>
                                    <td></td>
                                    <td>
                                        <a href="<%=request.getContextPath()%>/buy/farm/${farmId }/buyFarm">购买农庄</a>
                                    </td>
                                </s:else>
                            </tr>
                        </s:iterator>
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
                    <s:form id="pageFrm" theme="simple" action="%{#request.contextPath}/buy/land/%{land.landId}/farm"
                            method="get" cssStyle="display: none">
                        <s:hidden id="page.current" name="page.current" value="1"/>
                    </s:form>

                </div>
            </div>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="buyfarm-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>