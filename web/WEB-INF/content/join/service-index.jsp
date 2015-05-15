<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
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
            <s:include value="joinland-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
			<div class="t">
				<div class="t-t">
					服务管理
				</div>
			</div>
            <div class="box">
                <table cellspacing="2">
                    <tr>
                        <th>图片</th>
                        <th>名称</th>
                        <th nowrap>价格</th>
                        <th>服务说明</th>
                        <th nowrap>操作</th>
                    </tr>
                    <s:iterator value="services" status="its">
                        <tr class='<s:if test="#its.even">even</s:if><s:else>odd</s:else>'>
                            <td><img src="<%=request.getContextPath()%>/image?type=service&picId=${svcId}"
                                     title="${svcName}" alt="${svcName}"/></td>
                            <td nowrap>${svcId}<br>${svcName}</td>
                            <td nowrap>${expense}</td>
                            <td>${contents}</td>
                            <td>
                                <a href="<%=request.getContextPath()%>/join/service/${svcId}/show">浏览</a>
                                <a href="<%=request.getContextPath()%>/join/service/${svcId}/edit">编辑</a>
                                <a href="<%=request.getContextPath()%>/join/service/${svcId}/destroy">删除</a>
                            </td>
                        </tr>
                    </s:iterator>
                </table>
            </div>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="joinland-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>