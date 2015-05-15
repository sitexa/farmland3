<%--
  User: xnpeng
  Date: 2009-5-4
  Time: 14:02:00
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <sx:head/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <s:text name="root.title">
            <s:param>${user.username}</s:param>
        </s:text>
    </title>
    <link href="<%=request.getContextPath()%>/admin/css/admin.css" rel="stylesheet" type="text/css"/>
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
        <s:include value="../admin-header.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="leftbar" class="leftcol">
            <s:include value="user-menu.jsp"/>
        </div>
        <div id="centerbar">
            <div class="box">
                <div class="title">编辑信息</div>
                <div class="content">
                    <s:form id="frmEdit" theme="simple"
                            action="%{#request.contextPath}/admin/user/user-admin/%{user.userId}/update" method="post">
                        用&nbsp;&nbsp;户&nbsp;号:<s:textfield id="userId" name="user.userId" readonly="true"/><br/>
                        用&nbsp;&nbsp;户&nbsp;名:<s:textfield id="username" name="user.username" readonly="true"/><br/>
                        电子邮件:<s:textfield id="email" name="user.email" readonly="true"/><br/>
                        邮件确认:<s:checkbox name="user.emailConfirmed"/><br/>
                        注册时间:<s:date name="user.registerDate" format="yy年M月d日H时m分"/><br/>
                        生效日期:<sx:datetimepicker name="user.validDate"/><br/>
                        失效日期:<sx:datetimepicker name="user.expiryDate"/><br/>
                        失败次数:<s:textfield name="user.tryTimes" size="2"/><br/>
                        启用/禁用:<s:checkbox name="user.status"/>(打勾启用/去勾禁用)<br><br>
                        <s:submit value="保存修改"/>
                    </s:form>
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../footer.jsp"/>
    </div>
</div>
</body>
</html>