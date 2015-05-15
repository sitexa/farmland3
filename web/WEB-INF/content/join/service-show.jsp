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
	                	服务模板资料
	            </div>
	        </div>
            <div class="box">
                <div class="box">
                    <table>
                        <tr>
                            <td width="90">
                                <img src="<%=request.getContextPath()%>/image?type=service&picId=${service.svcId}" title="${service.svcName}"
                                     alt="${service.svcName}"/>
                            </td>
                            <td>
                                <table>
                                    <tr>
                                        <td class="odd">ID</td>
                                        <td>${service.svcId}</td>
                                    </tr>
                                    <tr>
                                        <td class="odd">服务名称</td>
                                        <td>${service.svcName}</td>
                                    </tr>
                                    <tr>
                                        <td class="odd">服务价格</td>
                                        <td>${service.expense}</td>
                                    </tr>
                                    <tr>
                                        <td class="odd">材料</td>
                                        <td>${service.materials}</td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                ${service.contents}
                            </td>
                        </tr>
                    </table>
                </div>
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