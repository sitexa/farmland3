<%--
  User: xnpeng
  Date: 2009-5-4
  Time: 14:27:08
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <s:text name="root.title">
            <s:param>${member.realname}</s:param>
        </s:text>
    </title>
    <link href="<%=request.getContextPath()%>/admin/css/admin.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="container">
    <div id="header">
        <s:include value="../admin-header.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="leftbar" class="leftcol"><s:include value="user-menu.jsp"/></div>
        <div id="centerbar">
            <div class="box">
                <s:actionmessage/>
                <s:actionerror/>
            </div>
        </div>
    </div>
    <div id="footer">
        <s:include value="../footer.jsp"/>
    </div>
</div>
</body>
</html>