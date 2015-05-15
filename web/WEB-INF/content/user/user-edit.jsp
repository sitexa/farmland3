<%--
  User: xnpeng
  Date: 2009-4-2
  Time: 21:10:44
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/index.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        var emailfilter = /^\w+[\+\.\w-]*@([\w-]+\.)*\w+[\w-]*\.([a-z]{2,4}|\d+)$/i;
        function doCheck() {
            var username = document.getElementById("username");
            var email = document.getElementById("email");
            var msg = "";
            if (username.value == "") {
                msg += "用户名为空\n";
            }
            if (!emailfilter.test(email.value)) {
                msg += "email格式不正确\n";
            }
            if (msg != "") {
                alert(msg);
                return;
            }
            document.getElementById("frmEdit").submit();
        }
        function doEditEmail() {
            document.getElementById("email").readOnly = false;
            document.getElementById("email").focus();
            document.getElementById("email").select();
            document.getElementById("btnEdit").style.display = "none";
            document.getElementById("btnCancel").style.display = "block";
            document.getElementById("submitEdit").style.display = "block";
            document.getElementById("spanEmail").style.display = "block";
        }
        function doCancelEmail() {
            document.getElementById("email").readOnly = true;
            document.getElementById("btnEdit").style.display = "block";
            document.getElementById("btnCancel").style.display = "none";
            document.getElementById("submitEdit").style.display = "none";
            document.getElementById("spanEmail").style.display = "none";
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
                <div class="title">修改用户信息</div>
                <div class="content">
                    <s:form id="frmEdit" theme="simple"
                            action="%{#request.contextPath}/user/user/%{user.userId}/updateEmail" method="post">
                        用&nbsp;&nbsp;户&nbsp;号:<s:textfield id="userId" name="user.userId" readonly="true"/><br/>
                        用&nbsp;&nbsp;户&nbsp;名:<s:textfield id="username" name="user.username" readonly="true"/><br/>
                        电子邮件:<s:textfield id="email" name="user.email" readonly="true"/><br/>
                        <span id="spanEmail"
                              style="color:#999999;display:none">修改电子邮件后,系统将重新发送确认信.在确认之前,用户的功能被暂时禁用.</span>
                        <a href="#" id="submitEdit" style="display:none;width:60px;" onclick="doCheck();">保存修改</a>
                        <a href="#" id="btnCancel" style="display:none;width:60px;"
                           onclick="doCancelEmail();">取消修改</a><br/>
                        <a href="#" id="btnEdit" style="display:block;width:60px;" onclick="doEditEmail();">修改邮箱</a>
                    </s:form>
                </div>
            </div>
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