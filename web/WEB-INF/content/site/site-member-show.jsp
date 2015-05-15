<%--
  User: xnpeng
  Date: 2009-5-8
  Time: 17:43:42
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/site.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="container">
	<div id="header">
	    <s:include value="../h.jsp"/>
	</div>
	<div id="mainwrapper">
	    <div id="leftbar" class="leftcol">
	    	<s:include value="site-menu.jsp"/>
		</div>
	    <div id="centerbar" class="centercol">
	        <div class="box">
                <div class="title1">社区会员</div>
	            <div class="content">
	                <table class="table1">
						<thead>
		                    <tr>
		                        <th width="20%">姓名</th>
		                        <th width="30%">加入时间</th>
		                        <th width="10%">类型</th>
		                        <th width="10%">状态</th>
		                        <th width="30%">备注</th>
		                    </tr>
						</thead>
						<tbody>
	                    <s:iterator value="siteMembers">
	                        <tr>
	                            <td>
	                                <a href="<%=request.getContextPath()%>/user/member/${member.memberId}/show">
	                                    <s:property value="%{member.realname}"/></a>
	                            </td>
	                            <td>
	                                <s:date name="joinDate" format="yy年M月d日H时"/>
	                            </td>
	                            <td>
	                                <s:property value="%{typeName}"/>
	                            </td>
	                            <td>
	                                <s:property value="%{statusName}"/>
	                            </td>
	                            <td>
	                                <s:property value="%{remark}"/>
	                            </td>
	                        </tr>
	                    </s:iterator>
						</tbody>
	                </table>
	            </div>
	        </div>
	        <s:actionmessage/>
	        <s:actionerror/>
	    </div>
	    <div id="rightbar" class="rightcol" >
	    	<s:include value="site-right.jsp"/>
		</div>
	</div>
	<div class="clear"></div>
	<div id="footer">
	    <s:include value="../f.jsp"/>
	</div>
</div>
</body>
</html>