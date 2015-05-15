<%--
  User: xnpeng
  Date: 2009-4-2
  Time: 21:10:59
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        var emailfilter = /^\w+[\+\.\w-]*@([\w-]+\.)*\w+[\w-]*\.([a-z]{2,4}|\d+)$/i;
        function doSubmit() {
            var username = document.getElementById("username");
            var pwd = document.getElementById("password");
            var pwd2 = document.getElementById("password2");
            var email = document.getElementById("email");
            var siteId = document.getElementById("siteId");
            var msg = "";
            if (username.value == "") {
                msg += "用户名为空\n";
            }
            if (pwd.value == "" || pwd.length < 6) {
                msg += "密码为空\n";
            }
            if (pwd.value != pwd2.value) {
                msg += "两次输入密码不相符\n";
            }
            if (!emailfilter.test(email.value)) {
                msg += "email格式不正确\n";
            }
            if (siteId.value == "") {
                msg += "没有选择所属区域\n";
            }
            if (msg != "") {
                alert(msg);
                return false;
            }
        }

        function yes() {
            var btn = document.getElementById("submit1");
            if (btn.disabled) {
                btn.disabled = false;
                btn.style.color = "";
            }
            else {
                btn.disabled = true;
                btn.style.color = "#999999";
            }
        }

        function selectSite() {
            var url = "<%=request.getContextPath()%>/site/selector";
            var feature = "dialogWidth:380px;dialogHeight:250px;Status:0;resizable:0;help:0";
            var result = window.showModalDialog(url, null, feature);
            if (result != null && result != "") {
                var arr = result.split(";");
                document.getElementById("siteId").value = arr[0];
                document.getElementById("siteName").value = arr[1];
            }
        }
    </script>
</head>
<body>
<div id="container">
    <div id="header">
        <s:include value="../h.jsp"/>
    </div>
    <div class="clear"></div>
    <div id="mainwrapper" style="padding-top:10px;">
        <div id="leftbar" class="leftcol">
		left
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
		        <div class="title">
		            <div class="t-text">
		                <div class="tag-flowerpot"></div>
	                	用户注册
		            </div>
		        </div>
                <div class="content">
                    <s:form id="frmNew" name="frmNew" theme="simple" action="%{#request.contextPath}/user/user"
                            onsubmit="return doSubmit();">
                        用&nbsp;户&nbsp;名:<s:textfield id="username" name="user.username"/><br/>
                        电子邮件:<s:textfield id="email" name="user.email"/>
                        <a href="http://mail.163.com/" target="_blank" style="text-decoration:underline">
                            没有电子信箱?马上去163注册一个!</a><br/>
                        设置密码:<s:password id="password" name="user.password"/><br/>
                        再输一次:<input type="password" id="password2" name="password2"/><br/>
                        <s:hidden id="siteId" name="site.siteId"/>
                        所属地区:<s:textfield id="siteName" readonly="true" name="site.name"/>
                        <a href="#" onclick="selectSite();" style="text-decoration:underline">点击选择</a><br/>
                        识&nbsp;别&nbsp;码:<s:textfield id="code" name="code"/>
                        <img src="<%=request.getContextPath()%>/image!codeImage" alt="识别码"/><br/><br/>
                        已经阅读并同意网站<a href="#" style="text-decoration:underline">注册协议</a>
                        <input type="checkbox" name="agreement" onclick="yes();"/><br/><br/>
                        <s:submit id="submit1" disabled="true" value="提交" method="create"/>
                    </s:form>
                    <div class="clear"></div>
                </div>
                <div>
                    <s:actionmessage/>
                    <s:actionerror/>
                </div>
            </div>
            <div class="clear"></div>
        </div>
        <div id="rightbar" class="rightcol">
			right
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>