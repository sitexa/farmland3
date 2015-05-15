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
				<s:include value="leave-message-header.jsp" />
				<div style="width:99%;">
					<div class="bar b-b1">
						小提示
					</div>
					<div class="actionbar b-bg">
						<s:if test="leaveMessage.inOutTag==0">
						<div class="btn mg5" onclick="location.href = '<%=request.getContextPath()%>/user/leave-message/${leaveMessage.id}/reply'">
							<span>回 复</span>
						</div>
						</s:if>
						<div class="btn mg5" onclick="location.reload()">
							<span>刷 新</span>
						</div>
						<div class="clear"></div>					
					</div>
					<div class="msgbox">
						<div class="msghead">
							<div class="msgtitle">
								<span class="blank6"></span>
								<h2>${leaveMessage.title}</h2>
								<span class="blank6"></span>
							</div>
							<div class="msginfo">
								留言人：${ leaveMessage.sender.realname}<br />
								<span class="blank6"></span>
								时　间：<s:date name="leaveMessage.sendDate" format="yyyy年MM月dd日  hh时ss分"/><br />
								<span class="blank6"></span>
								收件人：${ leaveMessage.receiver.realname}<br />
								<span class="blank6"></span>
							</div>
						</div>
						<div class="msgbody">
							<span class="blank6"></span>
							<div class="message">${ leaveMessage.message }</div>
							<span class="blank6"></span>
							<span class="blank6"></span>
							<span class="blank6"></span>
						</div>
					</div>
					<s:if test="leaveMessage.inOutTag==0">
					<div class="titbar b-t1">快捷回复</div>
					<div class="bodybar">
						<!--<div class="bodytitle">快捷回复</div>-->
						<s:form id="lmForm" theme="simple" action="%{#request.contextPath}/user/leave-message">
							<input type="hidden" name="leaveMessage.sender.memberId" value="${profile.memberId}" />
							<input type="hidden" name="leaveMessage.receiver.memberId" value="${leaveMessage.sender.memberId}" />
							<input type="hidden" name="leaveMessage.parent.id" value="${leaveMessage.id}" />
							<input type="hidden" id="title" name="leaveMessage.title" value="${leaveMessage.title}" />
							<div class="textareabox">
								<s:textarea name="leaveMessage.message" value=""></s:textarea>
								<s:submit value="发送" method="saveReply"></s:submit>
							</div>
						</s:form>
					</div>
					</s:if>
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