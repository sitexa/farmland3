<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>花木兰ICSA农场-服务指令管理</title>
		<link href="<%=request.getContextPath()%>/myfarm/myfarm-tools.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/style/table.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
		<script type="text/javascript">
			$(function(){
				$("tbody tr:odd").each(function(){ $(this).addClass("odd")})
				$("tbody tr").hover(
						function(){ 
						$(this).addClass("over");
					},
						function(){
						$(this).removeClass("over");
					}
				)
			})
			function showWindow(){
				var url = "<%=request.getContextPath()%>/myfarm/farming?farmId=${farm.farmId }";
				self.close();
				window.open(url, "", '');
			}
		</script>
	</head>
	<body>
		<div class="window">
			<div class="header">
				服务指令管理
			</div>
			<div class="body">
				<div class="box">
					<table>
						<thead>
							<th width="100">时间</th>
							<th width="100">指令发起人</th>
							<th width="100">服务类型</th>
							<th width="100">农作物</th>
							<th width="40">面积</th>
							<th width="100">状态</th>
						</thead>
						<tbody>
						<s:iterator value="farmings">
							<tr>
								<td><s:date name="startTime" format="yyyy-MM-dd"></s:date></td>
								<td>${farmer.realname }</td>
								<td>${faction.actionName }</td>
								<td>${seed.seedName }</td>
								<td>${quantity }</td>
								<td><s:if test="#request.state==1"><font color="#F00">未处理</font></s:if><s:else>已处理</s:else></td>
							</tr>
						</s:iterator>
						</tbody>
					</table>
					<br />
					<a href="javascript:void(0)" onclick="showWindow()">进入管理页面</a>
					<br /><br />
				</div>
			</div>
		</div>
	</body>
</html>