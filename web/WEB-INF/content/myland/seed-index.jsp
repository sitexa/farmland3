<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/myland/land.css" rel="stylesheet" type="text/css"/>
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
                    <div class="t-m"><a href="<%=request.getContextPath()%>/myland/seed/new?landId=${land.landId}">创建种子种苗</a></div>
                    农场种子/种苗管理
                </div>
            </div>
            <div class="box">
                <table width="100%">
                    <tr>
                        <th>图片</th>
                        <th nowrap>种子名称</th>
                        <th nowrap>价格</th>
                        <th nowrap>播种季节</th>
                        <th nowrap>收获季节</th>
                        <th>种子/种苗说明</th>
                        <th>状态</th>
                        <th nowrap>操作</th>
                    </tr>
                    <s:iterator value="seeds" status="its">
                        <tr class='<s:if test="#its.even">even</s:if><s:else>odd</s:else>'>
                            <td><img src="<%=request.getContextPath()%>/image?type=seed&picId=${seedId}" title="${seedName}"/>
                            </td>
                            <td>${seedId}<br>${seedName}</td>
                            <td>${price}</td>
                            <td>${plantTime}</td>
                            <td>${harvestTime}</td>
                            <td>${description}</td>
                            <td>${status}</td>
                            <td>
                                <a href="<%=request.getContextPath()%>/myland/seed/${seedId}/edit?page.current=${page.current }">修改</a>
                                <a href="<%=request.getContextPath()%>/myland/seed/${seedId}/destroy?page.current=${page.current }">删除</a>
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
                        <a href="#" onclick="dos('<s:property value="page.total"/>');"><s:property
                                value="page.total"/></a>
                    </s:if>
                </s:if>
            </div>
            <form id="frmSearch" style="display:none;" action="<%=request.getContextPath()%>/myland/seed"
                  method="get">
                <s:hidden name="landId" value="%{#request.land.landId}"/>
                <s:hidden id="page.current" name="page.current" value="1"/>
                <s:submit cssClass="formbutton"/>
            </form>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="seed-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>