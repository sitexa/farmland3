<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/myland/land.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/tools.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/ajaxfileupload.js"></script>
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
                    <div class="t-m"><a href="<%=request.getContextPath()%>/myland/faction/new?landId=${land.landId}">创建服务</a></div>
					农场服务管理
				</div>
			</div>
            <div class="box">
                <table width="100%">
                    <tr>
                        <th>图片</th>
                        <th nowrap>服务名称</th>
                        <th nowrap>价格</th>
                        <th nowrap>材料</th>
                        <th>服务说明</th>
                        <th>状态</th>
                        <th nowrap>操作</th>
                    </tr>
                    <s:iterator value="factions" status="its">
                        <tr class='<s:if test="#its.even">even</s:if><s:else>odd</s:else>'>
                            <td><img src="<%=request.getContextPath()%>/image?type=faction&picId=${factionId}" title="${actionName}"/>
                            </td>
                            <td>${farmingActionId}<br>${actionName}</td>
                            <td>${expense}</td>
                            <td>${materials}</td>
                            <td>${contents}</td>
                            <td>${status}</td>
                            <td>
                                <a href="<%=request.getContextPath()%>/myland/faction/${factionId}/edit">修改</a><br />
                                <a href="<%=request.getContextPath()%>/myland/faction/${factionId}/destroy">删除</a>
                            </td>
                        </tr>
                    </s:iterator>
                </table>
            </div>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="faction-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>