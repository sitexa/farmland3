<%--
  User: xnpeng
  Date: 2009-5-9
  Time: 10:57:51
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>
        花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/site.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="container">
	<div id="header">
	    <s:include value="../h.jsp"/>
	</div>
	<div id="mainwrapper">
	    <div id="leftbar">
	    	<s:include value="site-menu.jsp"/>
		</div>
	    <div id="centerbar">
	        <div class="box">
	            <div class="title">
	               社区文章
	            </div>
	            <div class="content">
	                <s:iterator value="posts">
	                    <a href="<%=request.getContextPath()%>/post/post/${id}/show"><s:property value="name"/> </a>
	                    <s:property value="creator.realname"/>
	                    <s:property value="category.name"/>
	                    <s:date name="createDate" format="M月d日h时m分"/>
	                    点击次数:<s:property value="vwCnt"/><br/>
	                </s:iterator>
	            </div>
	        </div>
	        <s:actionmessage/>
	        <s:actionerror/>
	    </div>
	    <div id="rightbar"><s:include value="site-right.jsp"/></div>
	</div>
	<div class="clear"></div>
	<div id="footer">
	    <s:include value="../f.jsp"/>
	</div>
</div>
</body>
</html>