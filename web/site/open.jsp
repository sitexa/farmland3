<%--
  User: xnpeng
  Date: 2009-4-5
  Time: 17:56:24
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><%=request.getRequestURI()%></title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        function SelectSite(){
            var url = "/site/selector";
            var feature = "dialogWidth:380px;dialogHeight:230px;Status:0;resizable:0;help:0";
            var result = window.showModalDialog(url, null, feature);
        }
    </script>
</head>
<body>
<%=request.getRequestURI()%>
<p/>
<input type="button" value="open" onclick="SelectSite();"/>
</body>
</html>