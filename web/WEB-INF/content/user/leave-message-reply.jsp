<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/index.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath()%>/user/css/leaveMessage.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath()%>/style/element.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/user/js/leaveMessage.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/script/element.js"></script>
    <script type="text/javascript">
    	$(function(){
    		var title = $("#title").val();
    		$("#title").val(title.substring(0,2)=="回复"?title:"回复 "+title);
    	})
    </script>
	<style type="text/css">
		.message{ width:99%; height:200px; border:1px solid #E8E8E8; margin:1px 2px;}
		.sendBox{}
	</style>
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
            <div style="margin-top:17px;">
				<div><s:include value="leave-message-header.jsp" /></div>
				<div style="width:99%;">
					<div class="bar b-b1">
						小提示
					</div>
					<span class="blank6"></span>
					<div class="sendBox">
					<s:form id="lmForm" theme="simple" action="%{#request.contextPath}/user/leave-message">
						<input type="hidden" name="leaveMessage.sender.memberId" value="${profile.memberId}" />
						<input type="hidden" name="leaveMessage.receiver.memberId" value="${leaveMessage.sender.memberId}" />
						<input type="hidden" name="leaveMessage.parent.id" value="${leaveMessage.id}" />
						留言给：${leaveMessage.sender.realname }<br />
						标　题：<s:textfield id="title" name="leaveMessage.title"></s:textfield><br />
						留言内容：<br />
						<s:textarea name="leaveMessage.message" cssClass="message"></s:textarea>
						<s:submit value=" 发 送 " method="saveReply"></s:submit>
					</s:form>
					</div>
				</div>
			</div>
        </div>
        <div id="rightbar" class="rightcol">
           
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>