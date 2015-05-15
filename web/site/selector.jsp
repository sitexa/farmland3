<%--
  User: xnpeng
  Date: 2009-4-5
  Time: 14:03:52
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><%=request.getRequestURI()%>
    </title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%=request.getRequestURI()%>
<br/>
<%=request.getContextPath()%>
<br/>
<%=request.getRequestURL()%>
<br/>
<s:url id="url" action="%{#request.contextPath}selector.jsp"/>
<s:a href="%{url}">link</s:a>
<p/>
<fieldset style="HEIGHT: 100%">
    <iframe name="SearchFrame" frameborder=0 framespacing=0 style='width:100%;height:100%;' src="SiteEdit.jsp"></iframe>
</fieldset>
</body>
</html>