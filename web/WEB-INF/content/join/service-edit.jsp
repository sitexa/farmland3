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
    <script type="text/javascript">

    </script>
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
                    服务模板编辑
                </div>
            </div>
            <div class="box">
                <div class="box">
                    <s:form id="frmService" action="%{#request.contextPath}/join/service/%{service.svcId}/update"
                            method="post" enctype="multipart/form-data">
                        <table>
                            <tr>
                                <td colspan="2">
                                    <table width="100%">
                                        <tr>
                                            <td class="odd">服务ID</td>
                                            <td>${service.svcId}<s:hidden name="service.svcId"/></td>
                                            <td class="odd">服务名称</td>
                                            <td><s:textfield name="service.svcName"/></td>
                                        </tr>
                                        <tr>
                                            <td class="odd">服务价格</td>
                                            <td><s:textfield name="service.expense"/></td>
                                            <td class="odd">使用材料</td>
                                            <td><s:textfield name="service.materials"/></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <img src="<%=request.getContextPath()%>/image?type=service&picId=${service.svcId}"
                                         title="${service.svcName}"
                                         alt="${service.svcName}"/>
                                </td>
                                <td>
                                    <s:file id="service_img" name="upload" size="15"/>
                                </td>
                            </tr>
                            <tr>
                                <td>服务说明</td>
                                <td>
                                    <s:textarea name="service.contents" cols="60" rows="5"/>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <s:submit value="保存" method="update" cssClass="formbutton"/>
                                </td>
                            </tr>
                        </table>
                    </s:form>
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