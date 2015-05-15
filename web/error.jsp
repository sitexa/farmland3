<%--
  User: xnpeng
  Date: 2009-3-24
  Time: 11:07:28
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>出错了</title>
    <link href="<%=request.getContextPath()%>/style/master.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<p>
    <s:actionerror/>
    <s:actionmessage/>
<p>
    出错了!
</body>
</html>