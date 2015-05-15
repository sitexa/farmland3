<%--
  User: xnpeng
  Date: 2009-5-6
  Time: 21:49:58
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/index.css" rel="stylesheet" type="text/css"/>
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
            <div class="box">
                <div class="title2">亲朋好友</div>
                <div class="content">
                    <s:if test="friends.size>0">
                        <s:iterator value="friends">
                            <s:property value="friendGroup.groupName"/>
                            <a href="<%=request.getContextPath()%>/user/member/${fellow.memberId}/show">姓名:<s:property
                                    value="fellow.realname"/></a>
                            <s:property value="status"/><br/>
                        </s:iterator>
                    </s:if>
                    <s:else>
                        您还没有家庭成员.
                    </s:else>
                </div>
            </div>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="control-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>