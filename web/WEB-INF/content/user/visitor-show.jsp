<%--
  User: xnpeng
  Date: 2009-5-6
  Time: 22:00:04
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>花木兰ICSA农场
    </title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/style/user.css" rel="stylesheet" type="text/css"/>
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
                <div class="title2">最近到访会员</div>
                <div class="content">
					<div class="list">
					<ul>
                    <s:if test="visitors.size>0">
                        <s:iterator value="visitors">
							<li>
                            	<a href="<%=request.getContextPath()%>/user/member/${guest.memberId}/show"><s:property
                                    value="guest.realname"/></a>
                            	到访时间:<s:date name="visitDate" format="yy年M月d日H时m分"/> 来访次数:<s:property value="times"/><br/>
							</li>
                        </s:iterator>
                    </s:if>
                    <s:else>
                       	您还没有访客呢!
                    </s:else>
					</ul>
                	</div>
            	</div>
        	</div>
		</div>
        <div id="rightbar" class="rightcol">
            <s:include value="control-right.jsp"/>
        </div>
    </div>
	<div class="clear"></div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>