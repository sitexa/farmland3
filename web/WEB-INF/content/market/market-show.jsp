<%--
  User: xnpeng
  Date: 2009-10-29
  Time: 10:53:23
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.sitexa.framework.config.AppConfig"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>花木兰ICSA农场</title>
	<link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/market/js/market.js"></script>
	<script type="text/javascript">
       onload = function() {
           $("input:submit").click(function() { return testFrom() });
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
			<div class="title">
				<div class="t-text">
					<div class="tag-flowerpot"></div>
			  			${market.itemTitle}
						(<a href="<%=request.getContextPath()%>/land/farm/${market.farm.farmId }/show">${market.farm.farmName}</a>)
				</div>
			</div>
				<div class="content">
					<a
						href="<%=request.getContextPath()%>/user/member/${market.member.memberId}">${market.member.realname}</a>
					<s:date format="M月d日H时m分" name="market.createDate" />
					<s:if
						test="market.member.memberId==profile.memberId||site.governor.memberId==profile.memberId">
						<a
							href="<%=request.getContextPath()%>/market/market/${market.itemId}/edit">编辑</a>
						<a
							href="<%=request.getContextPath()%>/market/market/${market.itemId}/destroy?siteId=${siteId}&itemType=${itemType}&cateId=${cateId}">删除</a>
					</s:if>
				</div>
<!--Begin pictrue box -->
				<s:if test="market.marketPictures.size>0">
					<div class="title">
						<div class="t-text">
							<div class="tag-flowerpot"></div>
							农庄照片
						</div>
					</div>
					<div class="content">
						<div id="box" style="width: 99%; overflow-x: scroll;">
							<table>
								<tr>
									<s:iterator value="market.marketPictures" status="its">
									<td>
										<img src="<%=request.getContextPath()%>/market/market/${id }!getImage?picId=${picId}"
											 alt="点击看大图" onclick="window.open('<%=AppConfig.getProperty("imgurl")%>/k/${id}/${PicUrl}')" />
									</td>
									</s:iterator>
								</tr>
							</table>
						</div>
					</div>
				</s:if>
<!--Begin The contents of the main posts -->
				<div class="content">
					${market.contents}
					<s:if test="market.parent!=null">
						<div class="mainpost">
							主帖:
							<s:a href="%{market.parent.itemId}">${market.parent.itemTitle}</s:a>
						</div>
					</s:if>
				</div>
<!--Begin User Reviews -->
				<s:if test="market.children.size>0">
					<div class="title">
						<div class="t-text">
							<div class="tag-notepad"></div>
							网友评论
						</div>
					</div>
					<div class="content">
					<s:iterator value="market.children" status="index">
						<div>
							<s:if test="!#index.first">
							<div class="line"></div>
							</s:if>
							<s:if test="member!=null">
								<a href="<%=request.getContextPath()%>/user/member/${member.memberId}">${member.realname}</a>
							</s:if>
							<s:else>
								游客
							</s:else>
							<s:date format="M月d日H时m分" name="createDate" />
							<s:if test="market.member.memberId==profile.memberId || member.memberId==profile.memberId || site.governor.memberId==profile.memberId">
								<a href="<%=request.getContextPath()%>/market/market/${itemId}/destroy?parentId=${id}&siteId=${siteId}&itemType=${itemType}&cateId=${cateId}">删除</a>
							</s:if>
						</div>
						${contents}
					</s:iterator>
					</div>
				</s:if>
<!--Begin Review -->
				<div class="title">
					<div class="t-text">
						<div class="tag-notepad"></div>
						评 论
					</div>
				</div>
				<div class="content">
					<div class="box" style="border:1px solid #666;">
						<s:form id="frmMarket" theme="simple" method="post" cssStyle="margin:10px" action="%{#request.contextPath}/market/market?parentId=%{#request.id}&siteId=%{#request.siteId}&itemType=%{#request.itemType}&cateId=%{#request.cateId}">
	                      	项目标题：<s:textfield id="itemTitle" name="market.itemTitle" value="" cssStyle="width:150px" />
							<br />
							<s:textarea id="contents" name="market.contents" value="" cssStyle="width:500px" rows="4" />
							<br />
							<s:submit value="回 复" method="reply" />
						</s:form>
	               	</div>
				</div>
			</div>
		</div>
		<div id="rightbar" style="width:29%">
			<div class="box margin-top">
				<div class="textBox">
			    	<div class="t-title">热门项目</div>
			    	<div class="t-body"></div>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div id="footer">
		<s:include value="../f.jsp" />
	</div>
</div>
</body>
</html>