<%@ page import="com.sitexa.framework.config.AppConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head><title><s:text name="index.title"/></title>
</head>
<body>
<%=request.getRequestURI()%><br/>
<s:text name="welcome_message"/> <br>
getRemoteAddr:<%=request.getRemoteAddr()%> <br>
getRemoteHost:<%=request.getRemoteHost()%> <br>
getRequestURI:<%=request.getRequestURI()%> <br>
getRequestURL:<%=request.getRequestURL()%> <br>
getServerName:<%=request.getServerName()%> <br>
getServerPort:<%=request.getServerPort()%> <br>
getContextPath:<%=request.getContextPath()%> <br>
getLocalAddr:<%=request.getLocalAddr()%> <br>
getLocalName:<%=request.getLocalName()%> <br>
getPathInfo:<%=request.getPathInfo()%> <br>
getPathTranslated:<%=request.getPathTranslated()%> <br>
getServletPath:<%=request.getServletPath()%> <br>
getHeader("HTTP_X_FORWARDED_FOR"):<%=request.getHeader("HTTP_X_FORWARDED_FOR")%> <br>
server:<%String url = request.getRequestURL().toString();
    String ctx = request.getContextPath();
    int pos = url.indexOf(ctx);
    String svr = url.substring(0, pos);
    String root=request.getRequestURL().substring(0,request.getRequestURL().indexOf(request.getContextPath()));
    out.print(svr);%><br>
svr:<%=root%><br>
root:<%=request.getRequestURL().substring(0,request.getRequestURL().indexOf(request.getContextPath()))%>
</body>
</html>