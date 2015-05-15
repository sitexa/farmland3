<%--
  User: xnpeng
  Date: 2009-5-15
  Time: 8:16:33
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>花木兰ICSA农场 </title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="container">
    <div id="header">
        <s:include value="h.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="leftbar" class="leftcol">
            <div class="box">
                <div class="content">
                </div>
            </div>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
                <div class="title">找回密码</div>
                <div class="content">
                    <s:form action="%{#request.contextPath}/logon!rewind" theme="simple" method="post">
                        <table border="0">
                            <tr>
                                <td>用户名</td>
                                <td><s:textfield name="user.username"/></td>
                            </tr>
                            <tr>
                                <td>电子信箱</td>
                                <td><s:textfield name="user.email"/></td>
                            </tr>
                            <tr>
                                <td>真实姓名</td>
                                <td><s:textfield name="user.realname"/></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><s:submit cssClass="formbutton" value="提交请求"/></td>
                            </tr>
                        </table>
                    </s:form>
                </div>
                <div class="box">
                    <div class="title">提示</div>
                    <div class="content" style="background-color:#ffff99">
                        如果忘记密码,请填写登录用户名,和注册的邮箱,或者真实姓名.系统将会把密码发到您的注册邮箱.请检查邮箱.
                    </div>
                </div>
                <s:actionerror/>
                <s:actionmessage/>
            </div>
            <div class="clear"></div>
        </div>
        <div id="rightbar" class="rightcol">
            <div class="box">
                <div class="title"></div>
                <div class="info">
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="f.jsp"/>
    </div>
</div>
</body>
</html>