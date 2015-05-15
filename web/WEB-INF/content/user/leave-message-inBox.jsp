<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

    <title>花木兰-留言</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath()%>/user/css/leaveMessage.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath()%>/style/element.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/user/js/leaveMessage.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/script/element.js"></script>
    <script type="text/javascript">
		var path = "<%=request.getContextPath()%>";
		$(function(){
			selectMenu(${menu});
			$("#messages tbody tr").each(function(){
				//行 over out 样式处理。
				$(this).hover(function(){$(this).addClass("row-over")}, function(){$(this).removeClass("row-over")})
				//行标记点击事件。
				$(this).find("td:lt(1)").each(function(){
					$(this).click(function(){
						var $checkbox = $(this).parent().find(":input[type='checkbox']");
						$checkbox.click();
					})
				})
				//checkbox点击事件。
				$(this).find("td:eq(1) :input[type='checkbox']").click(function(){
					if($(this).attr("checked"))	{
						$(this).parent().parent().addClass("row-checked");
					}else{
						$(this).parent().parent().removeClass("row-checked");
					}
				})
				//留言状态标 记点击事件处理。
				$(this).find("td:eq(2) img").click(function(){
					var $checkbox = $(this).parent().prev().find(":input[type='checkbox']");
					read_ajax(this, $checkbox.val());
				})
				//行，第3列后的单元格点击事件处理。
				$(this).find("td:gt(2)").each(function(){
					$(this).click(function(){
						var id = $(this).parent().find(":input[type='checkbox']").val();
						location.href = "<%=request.getContextPath()%>/user/leave-message/"+id;
					})
				})

			})
			$(".btn").btnFormat();
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
			<s:include value="leave-message-header.jsp" />
			<div style="margin:3px auto;">
				<div class="bar b-b1">
					<font style="font-weight:bold;">收件箱：</font>
					<font style="color:#6D6D6D;">
						共<font style="font-weight:bold;"> ${count} </font>条，
						本页<font style="font-weight:bold;"> <s:property value="leaveMessages.size" /> </font>条
					</font>
				</div>
				<div class="actionbar b-bg">
					<div class="btn mg5" onclick="delMessage()">
						<span>删 除</span>
					</div>
					<div class="btn mg5" onclick="location.reload()">
						<span>刷 新</span>
					</div>
					<div class="clear"></div>					
				</div>
				<div class="messages-box">
					<table id="messages" width="100%" border="0" cellpadding="0" cellspacing="0">
						<thead>
							<tr>
								<th width="10" class="noBorder"></th>
								<th width="20" class="noPadding"><input type="checkbox" name="allItems" onclick="selectAll(this.checked)"/></th>
								<th width="35">类型</th>
								<th width="100">发件人</th>
								<th>主题</th>
								<th width="80" class="noBorder">时间</th>
							</tr>
						</thead>
						<tbody>
						<s:if test="leaveMessages.size>0">
						<form id="lmForm" method="post" action="<%=request.getContextPath()%>/user/leave-message!delete">
							<input type="hidden" name="menu" value="0"/>
							<s:iterator value="leaveMessages">
							<tr>
								<td><label class="rowLabel"></label></td>
								<td class="noPadding"><input type="checkbox" name="ids" value="${id}"/></td>
								<td>&nbsp;
									<s:if test="readTag==0">
										<img src="<%=request.getContextPath()%>/user/img/unRd.gif" title="未读"/>
									</s:if>
								</td>
								<td><s:property value="sender.realname" /></td>
								<td><s:property value="title" /></td>
								<td class="lightgray"><s:date name="sendDate" format="yyyy-MM-dd"/></td>
							</tr>
							</s:iterator>
						</form>
						</s:if>
						</tbody>
					</table>
				</div>
				<div class="bar b-b1">
					<font style="font-weight:bold;">选择：</font>
					<a href="javascript:void(0)" onclick="selectAll(true)">全部</a>　
					<a href="javascript:void(0)" onclick="selectByRead(0)">未读</a>　
					<a href="javascript:void(0)" onclick="selectByRead(1)">已读</a>　
					<a href="javascript:void(0)" onclick="inverse()">反选</a>　
					<a href="javascript:void(0)" onclick="selectAll(false)">不选</a>
				</div>
				<div class="actionbar b-bg">
					<div class="btn mg5" onclick="delMessage()">
						<span>删 除</span>
					</div>
					<div class="btn mg5" onclick="location.reload()">
						<span>刷 新</span>
					</div>
					<div class="clear"></div>					
				</div>
			</div>
			
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>