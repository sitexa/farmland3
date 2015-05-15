<%--
  User: xnpeng
  Date: 2009-4-3
  Time: 17:35:48
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        function doCheck() {
            var oldPwd = document.getElementById("oldPassword");
            var pwd = document.getElementById("password");
            var pwd2 = document.getElementById("password2");
            var msg = "";
            if (oldPwd.value == "") {
                msg += "旧密码为空\n";
            }
            if (pwd.value == "") {
                msg += "新密码为空\n";
            }
            if (pwd.value != pwd2.value) {
                msg += "两次输入新密码不相符\n";
            }
            if (msg != "") {
                alert(msg);
                return false;
            }
        }
    </script>
</head>
<body>
<div id="container">
    <div id="header">
        <s:include value="../h.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="leftbar" class="leftcol">
            <s:include value="control-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
                <div class="title2">修改密码</div>
                <div class="content">
                    <s:form id="frmEdit" theme="simple"
                            action="%{#request.contextPath}/user/user/%{user.userId}/setPassword"
                            onsubmit="return doCheck();">
                        <table class="borderAll">
                            <tr>
                                <td>用户号</td>
                                <td><s:textfield id="userId" name="user.userId" readonly="true"/></td>
                            </tr>
                            <tr>
                                <td>用户名</td>
                                <td><s:textfield id="username" name="user.username" readonly="true"/></td>
                            </tr>
                            <tr>
                                <td>请输入旧密码</td>
                                <td>
                                    <s:password id="oldPassword" name="oldPassword"/>
                                </td>
                            </tr>
                            <tr>
                                <td>设定新密码</td>
                                <td>
                                    <s:password id="Password" name="newPassword"/>
                                </td>
                            </tr>
                            <tr>
                                <td>再输一次</td>
                                <td>
                                    <input type="password" id="password2" name="password2"/>
                                </td>
                            </tr>
                        </table>
                        <s:submit cssClass="formbutton" method="setPassword" value="提交"/>
                    </s:form>
                </div>
            </div>
            <s:actionmessage cssClass="message"/>
            <s:actionerror cssClass="error"/>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="control-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>