<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>花木兰ICSA农场--管理农庄</title>
    <link href="<%=request.getContextPath()%>/myland/land.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/tools.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/ajaxfileupload.js"></script>
    <script type="text/javascript">
        function dos(page) {
            document.getElementById("page.current").value = parseInt(page);
            document.getElementById("frmSearch").submit();
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
            <s:include value="land-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="t">
                <div class="t-t">
                    <div class="t-m"><a href="<%=request.getContextPath()%>/myland/farm/new?landId=${land.landId}">创建农庄</a></div>
                    农庄列表(${land.landName})
                </div>
            </div>
            <div class="box">
                <table width="100%" border="0" cellspacing="2">
                    <tr>
                        <th>ID</th>
                        <th>编号</th>
                        <th>面积</th>
                        <th>租金</th>
                        <th>农庄名称</th>
                        <th>操作</th>
                    </tr>
                    <s:iterator value="farms" status="its">
                        <tr class='<s:if test="#its.even">even</s:if><s:else>odd</s:else>'>
                            <td>
                                    ${farmId}
                                <s:if test="member!=null"><span style="color:red">●</span></s:if>
                            </td>
                            <td>${farmNo}</td>
                            <td>${acreage}</td>
                            <td>${rentPrice}</td>
                            <td>
                                <s:if test="member!=null">
                                    <a href="<%=request.getContextPath()%>/work/work/${farmId}">${farmName}</a>
                                </s:if>
                            </td>
                            <td align="right">
                                <a href="<%=request.getContextPath()%>/myland/farm/${farmId}">查看</a>
                                <a href="<%=request.getContextPath()%>/myland/farm/${farmId}/edit">修改</a>
                                <a href="<%=request.getContextPath()%>/myland/farm/${farmId}/destroy">删除</a>
                            </td>
                        </tr>
                    </s:iterator>
                </table>
            </div>
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
                        <a href="#" onclick="dos('<s:property value=" page.total"/>');"><s:property
                                value="page.total"/></a>
                    </s:if>
                </s:if>
            </div>
            <form id="frmSearch" style="display:none;" action="<%=request.getContextPath()%>/join/farm"
                  onsubmit="dos(1);"
                  method="get">
                <s:hidden id="page.current" name="page.current" value="1"/>
                <s:hidden name="landId" value="%{land.landId}"/>
                <s:submit cssClass="formbutton" method="index"/>
            </form>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="farm-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>