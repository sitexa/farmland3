<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>服务指令管理</title>
    <link href="<%=request.getContextPath()%>/admin/css/admin.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/admin/js/admin.js"></script>
	<style type="text/css">
		tr{ cursor:pointer;}
	</style>
	<script type="text/javascript">
		$(function(){
			$("tbody tr").each(function(){	
				$(this).click(function(){	
					self.location="<%=request.getContextPath()%>/admin/work/farming!farms?landId="+this.id;	
				})
			})
			
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
		function dos(page) {
		    document.getElementById("page.current").value = parseInt(page);
		    document.getElementById("pageFarm").submit();
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
					<li><a href="<%=request.getContextPath()%>/admin/work/work">农场列表</a></li>
					<li>菜单条三</li>
					<li>菜单条四</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="center">
		<div class="bar-center"><span class="table-title">农场列表</span></div>
		<table id="lands" cellpadding="0" cellspacing="1">
		    <thead>
				<th width="50">序号</th>
		        <th width="230">农场名称</th>
		        <th width="100">农场类型</th>
		        <th width="250">农场地址</th>
		        <th width="150">待处理指令数</th>
			</thead>
			<tbody>
		    <s:if test="#request.landList.size>0">
		        <s:iterator value="#request.landList" var="object" status="its">
		            <tr id="${object[0]}">
						<td>${its.index+1}</td>
		                <td>${object[1]}</td>
		                <td>${object[2]}</td>
		                <td>${object[3]}</td>
		                <td>${object[4]}条</td>
		            </tr>
		        </s:iterator>
		    </s:if>
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
		<s:form id="pageFarm" theme="simple" action="%{#request.contextPath}/admin/work/work" method="get" cssStyle="display: none">
			<s:hidden id="page.current" name="page.current" value="1"/>
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