<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<div id="container">
    <div id="header">
        <s:include value="../h.jsp"/>
    </div>
    <div class="clear"></div>
    <div id="mainwrapper" style="padding-top:10px;">
        <div id="leftbar" class="leftcol">
            <s:include value="control-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <div class="box">
		        <div class="title">
		            <div class="t-text">
		                <div class="tag-flowerpot"></div>
	                	申请成为农场主
		            </div>
		        </div>
                <div class="content">
                    <s:form id="frmNew" name="frmNew" theme="simple" action="%{#request.contextPath}/user/member/%{#request.member.memberId}">
						<br />
						您好！如要成为农场主，请提交申请.<font style="color:#F00;font-size:12px">(您还未提交农场主申请.)</font><br />
						<span class="blank6"></span>
                        <s:submit id="submit1" value="申请成为农场主" method="applyLander"/>
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