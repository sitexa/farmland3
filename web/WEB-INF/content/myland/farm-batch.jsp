<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>花木兰ICSA农场--农庄列表</title>
    <link href="<%=request.getContextPath()%>/myland/land.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/tools.js"></script>
    <script type="text/javascript">
        function addtr() {
        	document.getElementById("picDiv").style.display = 'block';
        }

        function deltr(rowId) {
        	document.getElementById("picDiv").style.display = 'none';
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
					农庄列表(${land.landName})
				</div>
			</div>
            <div class="box">
                <s:form theme="simple" id="frmFarm"
                        action="%{#request.contextPath}/myland/farm/%{land.landId}" method="post">
                    <s:hidden name="_method"/>
                    <s:hidden name="farmId"/>
                    <s:hidden id="landId" name="landId" value="%{land.landId}"/>
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
                                    <td><s:textfield size="10" name="farms(%{farmId}).farmId" value="%{farmId}" readonly="true"/></td>
                                    <td><s:textfield size="10" name="farms(%{farmId}).farmNo" value="%{farmNo}"/></td>
                                    <td><s:textfield size="10" name="farms(%{farmId}).acreage" value="%{acreage}"/></td>
                                    <td><s:textfield size="10" name="farms(%{farmId}).rentPrice" value="%{rentPrice}" readonly="true"/></td>
                                    <td><a href="#">${farmName}</a></td>
                                    <td align="right">
										<a href="<%=request.getContextPath()%>/myland/farm/${farmId}/destroy?type=batch&landId=${land.landId }">删除</a>
                                    </td>
                                </tr>
                            </s:iterator>
                        </table>
                    </div>
                    <div id="picDiv" style="display:none;margin:20px auto">
                        <table>
                            <tbody id="ptable">
                            <tr id="row0">
                                <td>编号:<input type="text" name="farmNo" size="10" value=""></td>
                                <td>面积:<input type="text" name="acreage" size="10" value=""></td>
                                <td><input class="formbutton" type=button value="删除" onClick="deltr('row0');"/></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="box" style="float:right;">
                        <input class="formbutton" type="button" value="增加农庄" onClick="addtr();"/>
                        <s:submit cssClass="formbutton" method="updateBatch" value=" 保存 "/>
                    </div>
                </s:form>
                <div class="clear"></div>
            </div>
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