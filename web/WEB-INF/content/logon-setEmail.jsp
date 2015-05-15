<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>帐号激活</title>
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

            </div>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
				<div class="title">
					<div class="t-text">
						<div class="tag-notepad"></div>
						帐号激活
					</div>
				</div>
                <div class="content">
                    <s:form action="%{#request.contextPath}/logon!sendActivateEmail" theme="simple">
						<s:hidden name="user.userId"></s:hidden>
						<br />
                       	邮箱:<s:textfield name="user.email"/><font style="color:#F00; size:12px">(请输入正确的邮箱地址，以便接收帐号激活邮件)</font><br/>
						<span class="blank6"></span>
                        <s:submit value="发送帐号激活邮件"/><br/><br/>
                    </s:form>
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