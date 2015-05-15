<%--
  User: xnpeng
  Date: 2009-5-3
  Time: 23:47:50
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>农庄管理</title>
    <link href="<%=request.getContextPath()%>/admin/css/admin.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/style/window.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath()%>/style/table.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath()%>/style/element.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.easydrag.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/script/window.js"></script>
	<style type="text/css">
		tr{ cursor:pointer;}
	</style>
	<script type="text/javascript">
		var returnMessage = "${returnMessage}";
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
			
			if(returnMessage!=""){
				$("<div></div>").html("没有错误")
								.css({position:"absolute",top:"250px",left:"800px",width:"200px",padding:"5px",background:"green"})
								.appendTo("body")
								.fadeOut(3000);
			}
		})
		
		function delComfire(id){
			$("#id").val(id);
			var baseWindow = new BaseWindow("","取消命令");
			baseWindow.setCont(
				$('<div>取消说明:<br /><textarea id="msg" style="width:490px;height:100px; overflow:auto"/></div>')
			);
			baseWindow.setButtons([{name:"关 闭",fun:function(){del();}}]);		
		}
		function del(){
			$("#message").val($("#msg").val());
			//alert($("#id").val()+"|"+$("#message").val());
			document.getElementById("delForm").submit();		
		}
		function dos(page) {
		    document.getElementById("page.current").value = parseInt(page);
		    document.getElementById("pageForm").submit();
		}
		
	</script>
</head>
<body>
<div class="header">
	<span class="blank6"></span>
	<span class="title">农场后台管理</span>
	<span class="blank6"></span>
</div>
<span class="blank6"></span>
<div class="box">
	<div class="left">
		<div class="menu">
			<div class="title">菜单标题</div>
			<div class="items">
				<ul>
					<li><a href="<%=request.getContextPath()%>/admin/work/work!crops?farmId=${farm.farmId }">农作物管理</a></li>
					<li><a href="<%=request.getContextPath()%>/admin/work/farming?farmId=${farm.farmId }">服务指令管理</a></li>
					<li>菜单条三</li>
					<li>菜单条四</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="center">
		<div class="bar-right"><a href="Javascript:history.back()">后退</a></div>
		<div class="bar-center"><span class="table-title">服务指令列表</span></div>
		<table>
			<thead>
				<th width="80">时间</th>
				<th width="100">指令发起人</th>
				<th width="100">服务类型</th>
				<th width="100">农作物</th>
				<th width="40">面积</th>
				<th width="80">状态</th>
				<th width="150">操作</th>
			</thead>
			<tbody>
			<s:iterator value="farmings">
				<tr>
					<td><s:date name="startTime" format="yyyy-MM-dd"></s:date></td>
					<td><nobr>${farmer.realname }</nobr></td>
					<td>${faction.actionName }</td>
					<td>${seed.seedName }</td>
					<td>${quantity }</td>
					<s:if test="#request.state==1">
						<td><font color="#F00">未处理</font></td>
						<td>
							<a href="<%=request.getContextPath()%>/admin/work/farming/${farmingId}/doFarming">执行指令</a>
							｜
							<a href="javascript:void(0)" onclick="delComfire(${farmingId})">取消指令</a>
						</td>
					</s:if><s:else>
						<td>已处理</td>
						<td></td>
					</s:else>
				</tr>
			</s:iterator>
			</tbody>
		</table>
        <div id="paging">
          <s:if test="page.total!=0">
              	共有${page.total}页结果:
              <s:if test="page.current>6">
                  <a href="#" onclick="dos(1);">1</a>
              </s:if>
              <s:if test="page.current>7">
                  ...
              </s:if>
              <s:bean name="org.apache.struts2.util.Counter" id="counter">
                  <s:param name="first" value="page.current-5"/>
                  <s:param name="last" value="page.current+5"/>
                  <s:iterator status="its">
                      <s:if test="current>1 && current<=page.total+1">
                          <s:if test="#its.index==5">
                              <s:property/>
                          </s:if>
                          <s:else>
                              <a href="#" onclick="dos('<s:property/>');"> <s:property/></a>
                          </s:else>
                      </s:if>
                  </s:iterator>
              </s:bean>
              <s:if test="page.total>page.current+6">
                  ...
              </s:if>
              <s:if test="page.total>page.current+5">
                  <a href="#" onclick="dos('<s:property value="page.total"/>');"><s:property
                          value="page.total"/></a>
              </s:if>
          </s:if>
      	</div>
		<s:form id="pageForm" theme="simple" action="%{#request.contextPath}/admin/work/farming" method="get" cssStyle="display: none">
			<s:hidden id="farmId" name="farmId" value="%{#request.farm.farmId}"/>
			<s:hidden id="page.current" name="page.current" value="1"/>
		</s:form>
		<s:form id="delForm" theme="simple" action="%{#request.contextPath}/admin/work/farming!delete" method="post" cssStyle="display: none">
			<s:hidden id="id" name="id"/>
			<s:hidden id="message" name="message"/>
		</s:form>
	</div>
</div>
<span class="blank6"></span>
<div class="footer">
	<span class="blank6"></span>
	<span>footer</span>
	<span class="blank6"></span>
</div>
</body>
</html>
