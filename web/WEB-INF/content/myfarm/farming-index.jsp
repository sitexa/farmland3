<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>购买农庄</title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
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
		
		function dos(page) {
		    document.getElementById("page.current").value = parseInt(page);
		    document.getElementById("pageFarm").submit();
		}
	</script>

</head>
<body>
<div id="container">
    <div id="header">
        <s:include value="../h.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="leftbar" class="leftcol">
            left
        </div>
        <div id="centerbar" class="centercol">
            <div class="title margin-top">
                <div class="t-text">
                    <div class="tag-flowerpot"></div>
					服务指令管理
                </div>
            </div>
            <div class="box">
				<table>
					<thead>
						<th width="80">时间</th>
						<th width="100">指令发起人</th>
						<th width="100">服务类型</th>
						<th width="100">农作物</th>
						<th width="40">面积</th>
						<th width="100">状态</th>
						<th width="80">操作</th>
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
								<td><a href="<%=request.getContextPath()%>/myfarm/farming/${farmingId }/destroy">取消指令</a></td>
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
				<s:form id="pageFarm" theme="simple" action="%{#request.contextPath}/myfarm/farming" method="get" cssStyle="display: none">
					<s:hidden id="farmId" name="farmId" value="%{#request.farm.farmId}"/>
					<s:hidden id="page.current" name="page.current" value="1"/>
				</s:form>
            </div>
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