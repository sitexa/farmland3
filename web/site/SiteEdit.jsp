<%--
  Created by IntelliJ IDEA.
  User: xnpeng
  Date: 2009-3-30
  Time: 15:50:45
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><%=request.getRequestURI()%></title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        var top = window.parent;
        function doClose(){
            top.returnValue = "abcd";
            top.close();
        }
    </script>
</head>
<body>
<%=request.getRequestURI()%>
<br/>

<input type="button" value="选择" onclick="doClose();"/>
</body>
</html>