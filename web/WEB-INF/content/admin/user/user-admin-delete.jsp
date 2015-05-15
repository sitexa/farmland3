<%--
  User: xnpeng
  Date: 2009-5-4
  Time: 7:48:59
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <s:text name="root.title">
            <s:param>${me.username}</s:param>
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
                <div class="title">删除用户</div>
                <div class="box">
                    <div class="content" style="width:30%;float:left;">
                        <s:form theme="simple" id="frmMember"
                                action="%{#request.contextPath}/admin/user/user-admin/%{user.userId}/destroy"
                                method="post">
                            <s:hidden name="user.userId"/>
                            用户号:${user.userId}<br/>
                            用户名:${user.username}<br/>
                            电子邮件:${user.email}<br/>
                            邮件确认:${user.emailConfirmed}<br/>
                            用户状态:${user.status}<br/>
                            注册时间:<s:date name="user.registerDate" format="yy年M月d日H时"/><br/>
                            生效时间:<s:date name="user.validDate" format="yy年M月d日H时"/><br/>
                            过期时间:<s:date name="user.expiryDate" format="yy年M月d日H时"/><br/>
                            <s:submit method="destroy" value="确认删除"/>
                        </s:form>
                        <div class="clear"></div>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../footer.jsp"/>
    </div>
</div>
</body>
</html>