<%--
  User: xnpeng
  Date: 2009-10-29
  Time: 10:53:50
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ page import="com.sitexa.framework.config.AppConfig" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>花木兰ICSA农场</title>
 	<link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/market/js/market.js"></script>
    <script type="text/javascript">
    	var url = "<%=request.getContextPath()%>";
		onload = function(){
			$("input:submit").click(function(){	return testFrom()	});	
		}
    </script>
</head>
<body>
<div id="container">
	<div id="header">
		<s:include value="../h.jsp" />
	</div>
	<div id="mainwrapper">
		<div id="leftbar" style="width:68%;">
			<div class="box">
<!--Begin Review -->
				<div class="title">
					<div class="t-text">
						<div class="tag-notepad"></div>
						编辑信息
					</div>
				</div>
				<div class="content">
					<div class="box" style="border:1px solid #666;">
		                <s:form id="frmMarket" theme="simple" method="post" cssStyle="margin:10px" action="%{#request.contextPath}/market/market">
		                   	 项目标题：<s:textfield id="itemTitle" name="market.itemTitle" cssStyle="width:250px" /><br>
		                    <s:textarea id="contents" name="market.contents" cssStyle="width:500px;" rows="4" /><br>
							<s:hidden name="market.itemId"></s:hidden>
							<input type="hidden" id="siteId" name="siteId" value="${siteId}"/>
							<input type="hidden" id="itemType" name="itemType" value="${itemType}"/>
							<input type="hidden" id="cateId" name="cateId" value="${cateId}"/>
							<s:hidden name="page.current" />
							<s:submit value="保存" method="update"/> (上传图片)
		                </s:form>
					</div>
				</div>
<!--Begin picture list-->			
				<div class="title">
					<div class="t-text">
						<div class="tag-flowerpot"></div>
						图片上传
					</div>
				</div>
				<div class="content">
					<table id = "imgBox">
						<tr>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					<s:if test="market.marketPictures.size>0">
						<s:iterator value="market.marketPictures" status="its">
						<tr>
							<td>
							<img src="<%=request.getContextPath()%>/market/market!getImage?picId=${picId}"	alt="点击看大图"/>
							</td>
							<td>
							<input type="text" size="10" id="${picId}-title" value="${title}"/><br>
							<input type="text" size="30" id="${picId}-description" value="${description}"/>
							</td>
							<td>
							<input type="button" class="formbutton" value="删 除" onClick="delImg(this,${picId})"/>&nbsp;&nbsp;
							<input type="button" class="formbutton" value="修 改" onClick="upDateImg(this,${picId})"/>&nbsp;&nbsp;
							</td>
						</tr>
						</s:iterator>
					</s:if>
					</table>
	<!--Begin picture upload-->
					<img id="loading" src="<%=request.getContextPath()%>/land/loading.gif" style="display:none;" /> 
					标题：<input type='text' size="8" id="title" value="标题"/>
					说明：<input type='text' size="12" id="description" value="说明"/>
					<input id="upload" type="file" size="15" name="upload" class="input" />&nbsp;&nbsp; 
					<button class="formbutton" id="buttonUpload" onclick="return uploadImg(${id});">上传</button> 
				</div>
	        </div>
	    </div>
	    <div id="rightbar" style="width:29%">
			<div class="box margin-top">
				<div class="textBox">
			    	<div class="t-title">right title</div>
			    	<div class="t-body">right content</div>
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