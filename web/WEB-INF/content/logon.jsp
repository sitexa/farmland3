<%--
  User: xnpeng
  Date: 2009-4-16
  Time: 17:35:03
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="container">
    <div id="header">
        <s:include value="h.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="leftbar" class="leftcol">
            <div class="box" style="color:white;">
                 1
            </div>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
				<div class="title">
					<div class="t-text">
						<div class="tag-notepad"></div>
						登录花木兰
					</div>
				</div>
                <div class="content">
                    <div style="background-color:#ffffcc;float:right;border:1px solid #ccccff;margin-right:150px;padding:5px;">测试帐户：oscar<br>密码：111111</div>
                    <s:form action="%{#request.contextPath}/logon!login" theme="simple">
                        用户名:<s:textfield name="user.username"/><br/>
                        密电码:<s:password name="user.password"/><br/>
                        识别码:<s:textfield name="code"/>
                        <img src="<%=request.getContextPath()%>/image!codeImage" alt="识别码"/><br/>
                        记住我:<s:checkbox name="remember" value="true"/><br/><br/>
                        <s:submit value="登录"/><br/><br/>
                        忘记密码了?<a href="<%=request.getContextPath()%>/logon!findpwd" style="color:#0066CC">找回密码</a><br/>
                        还没有注册?<a href="<%=request.getContextPath()%>/user/user/new" style="color:#0066CC">立即注册</a>
                    </s:form>
                    <s:actionmessage cssStyle="color:blue"/>
                    <s:actionerror cssStyle="color:red"/>
                </div>
            </div>
            <div class="clear"></div>
        </div>
        <div id="rightbar" class="rightcol">
            <div class="box">

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